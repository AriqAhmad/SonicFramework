package com.thedevelopercat.sonic.exceptions

class IllegalFragmentTypeException : SonicException {

    internal constructor() : super("Invalid fragment type") {}

    internal constructor(message: String) : super(message) {}
}
