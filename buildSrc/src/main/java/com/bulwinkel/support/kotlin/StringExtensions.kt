package com.bulwinkel.support.kotlin

inline infix fun String.orIfEmpty(defaultSupplier: () -> String) : String {
    return if (isNotEmpty()) this else defaultSupplier()
}