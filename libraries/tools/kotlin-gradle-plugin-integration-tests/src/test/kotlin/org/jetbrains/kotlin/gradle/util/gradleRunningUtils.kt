/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.util

import java.io.File

class ProcessRunResult(
    private val cmd: List<String>,
    private val workingDir: File,
    val exitCode: Int,
    val output: String,
    val stdErr: String,
) {
    val isSuccessful: Boolean
        get() = exitCode == 0

    override fun toString(): String =
        """
        |Executing process was ${if (isSuccessful) "successful" else "unsuccessful"}
        |    Command: ${cmd.joinToString()}
        |    Working directory: ${workingDir.absolutePath}
        |    Exit code: $exitCode
        """.trimMargin()
}

fun runProcess(
    cmd: List<String>,
    workingDir: File,
    environmentVariables: Map<String, String> = mapOf(),
    redirectErrorStream: Boolean = true,
): ProcessRunResult {
    val builder = ProcessBuilder(cmd)
    builder.environment().putAll(environmentVariables)
    builder.directory(workingDir)
    // redirectErrorStream merges stdout and stderr, so it can be gotten from process.inputStream
    builder.redirectErrorStream(redirectErrorStream)

    val process = builder.start()
    // important to read inputStream, otherwise the process may freeze on some systems
    val sb = StringBuilder()
    process.inputStream!!.bufferedReader().forEachLine {
        println(it)
        sb.append(it).append(System.lineSeparator())
    }
    val stdErr = process.errorStream.bufferedReader().use { it.readText() }
    val exitCode = process.waitFor()

    return ProcessRunResult(cmd, workingDir, exitCode, sb.toString(), stdErr)
}

/**
 * Asserts the result of running a process by calling a set of assertions on the result object.
 * If any of the assertions fail, an [AssertionError] is thrown and the process output information is printed.
 *
 * @param result The result of running a process.
 * @param assertions A lambda expression that performs a set of assertions on it.
 *
 * @throws AssertionError If any of the assertions fail.
 */
fun assertProcessRunResult(result: ProcessRunResult, assertions: ProcessRunResult.() -> Unit) {
    try {
        result.assertions()
    } catch (e: AssertionError) {
        println(
            """
            |Process info:
            |#######################
            |$result
            |#######################
            |
            |Process output:
            |#######################
            |${result.output}
            |#######################
            |
            |Process error output:
            |#######################
            |${result.stdErr}
            |#######################
            |
            """.trimMargin()
        )
        throw e
    }
}
