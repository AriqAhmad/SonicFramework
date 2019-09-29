package com.thedevelopercat.sonic.exceptions

class ParseException: SonicException {

    internal constructor() : super("Error parsing") {}

    internal constructor(message: String) : super(message) {}

}
