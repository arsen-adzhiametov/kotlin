// RUN_PIPELINE_TILL: FRONTEND
interface One<T>
fun <E, R> foo(): E where E : Number, E : One<R>, R : One<E> = null!!

interface Entity<T>
abstract class SecuredEntity<out E>(val entity: E) where E : Entity<Int>, E : SecurityCodeAware<*,*>
interface SecurityCodeAware<out E, R : SecuredEntity<E>> where E : Entity<Int>, E : SecurityCodeAware<E, R>
fun <E, R : SecuredEntity<E>> SecurityCodeAware<E, R>.secured() : R where E : Entity<Int>, E : SecurityCodeAware<E, R> = <!RETURN_TYPE_MISMATCH!>when(this) {
    <!USELESS_IS_CHECK!>is Order<!> -> SecuredOrder(this)
    else -> null!!
}<!>
class Order : Entity<Int>
class SecuredOrder(order: Order) : <!INAPPLICABLE_CANDIDATE!>SecuredEntity<<!UPPER_BOUND_VIOLATED!>Order<!>><!>(order)
fun main() {
    val securedOrder = Order().<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>secured<!>()
}
