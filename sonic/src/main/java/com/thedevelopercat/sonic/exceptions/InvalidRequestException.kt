package com.thedevelopercat.sonic.exceptions

class InvalidRequestException: SonicException {

    internal constructor() : super("Invalid Request") {}

    internal constructor(message: String) : super(message) {}

}
