package com.thedevelopercat.sonic.exceptions

class IllegalFragmentTypeException : SonicException {

    constructor() : super("Invalid fragment type") {}

    public constructor(message: String) : super(message) {}
}
