package com.thedevelopercat.sonic.exceptions

open class SonicException : Exception {

    internal constructor() : super() {}
    internal constructor(message: String) : super(message) {}

    override fun toString(): String {
        return message ?: super.toString()
    }
}
