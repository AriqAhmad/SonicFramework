package com.thedevelopercat.sonic.exceptions

class NoNetworkException : SonicException {

    internal constructor() : super("No network") {}

    internal constructor(message: String) : super(message) {}
}
