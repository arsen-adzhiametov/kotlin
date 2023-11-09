package org.jetbrains.kotlinx.dataframe

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.*

fun box(): String {
    val df = DataFrame.readCSVDefault("https://raw.githubusercontent.com/Kotlin/dataframe/master/data/jetbrains_repositories.csv")

    df.stargazers_count.print()

    df.filter { stargazers_count > 50 }.print()

    df.add("hello") { 42 }.hello

//    df
//        .add("hello") { 42 }
//        .add("hello1") { hello + 3}

    df.sortByDesc { stargazers_count }.print(rowsLimit = 10)
    println(df.count { stargazers_count > 50 })
    println(df.count { stargazers_count == 0 })
    return "OK"
}
