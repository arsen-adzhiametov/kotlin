// WITH_STDLIB
// LANGUAGE: +InstantiationOfAnnotationClasses

// JVM_ABI_K1_K2_DIFF: K2 serializes annotation parameter default values (KT-59526).

package test

import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertTrue as assert

annotation class One<T>()

annotation class Two<K, V>(val s: String)
annotation class Nesting(val a1: One<Int> = One(), val a2: Two<String, List<String>> = Two("two"))

annotation class WithKClass<K: Any>(val k: KClass<K>)

fun box(): String {
    val a = One<String>()
    assert(a.toString().endsWith("test.One()"))
    val t = Two<String, Int>("two")
    assertEquals("two", t.s)
    val n = Nesting()
    assertEquals("two", n.a2.s)
    val wk = WithKClass(String::class)
    assert(String::class == wk.k)

    // type parameters don't affect equals
    assert(Two<String, Int>("two") == Two<Int, String>("two"))
    assert(WithKClass(Int::class) != WithKClass(String::class))
    return "OK"
}
