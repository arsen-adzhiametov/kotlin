/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.bc

import org.jetbrains.kotlin.cli.common.arguments.cliArgument
import org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments as MovedK2NativeCompilerArguments

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("K2NativeCompilerArguments", "org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments")
)
typealias K2NativeCompilerArguments = MovedK2NativeCompilerArguments

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("STATIC_FRAMEWORK_FLAG", "org.jetbrains.kotlin.cli.common.arguments.STATIC_FRAMEWORK_FLAG")
)
val STATIC_FRAMEWORK_FLAG = MovedK2NativeCompilerArguments::staticFramework.cliArgument

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("INCLUDE_ARG", "org.jetbrains.kotlin.cli.common.arguments.INCLUDE_ARG")
)
val INCLUDE_ARG = MovedK2NativeCompilerArguments::includes.cliArgument

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("CACHED_LIBRARY", "org.jetbrains.kotlin.cli.common.arguments.CACHED_LIBRARY")
)
val CACHED_LIBRARY = MovedK2NativeCompilerArguments::cachedLibraries.cliArgument

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("ADD_CACHE", "org.jetbrains.kotlin.cli.common.arguments.ADD_CACHE")
)
val ADD_CACHE = MovedK2NativeCompilerArguments::libraryToAddToCache.cliArgument

@Deprecated(
        "Moved to new 'org.jetbrains.kotlin.cli.common.arguments' package",
        ReplaceWith("SHORT_MODULE_NAME_ARG", "org.jetbrains.kotlin.cli.common.arguments.SHORT_MODULE_NAME_ARG")
)
val SHORT_MODULE_NAME_ARG = MovedK2NativeCompilerArguments::shortModuleName.cliArgument
