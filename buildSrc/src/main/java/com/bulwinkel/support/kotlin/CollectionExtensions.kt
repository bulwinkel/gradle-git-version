package com.bulwinkel.support.kotlin

infix fun <T> Collection<T>.orIfEmpty(default: Collection<T>) : Collection<T> {
    return if (isNotEmpty()) this else default
}

inline infix fun <T> Collection<T>.orIfEmpty(defaultSupplier: () -> Collection<T>) : Collection<T> {
    return if (isNotEmpty()) this else defaultSupplier()
}

fun <T> List<T>.firstOr(default: T): T {
    return if (isEmpty()) default else this[0]
}

inline fun <T> Iterable<T>.firstOr(defaultValue: T, predicate: (T) -> Boolean): T {
    for (element in this) if (predicate(element)) return element
    return defaultValue
}