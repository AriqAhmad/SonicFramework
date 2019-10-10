package com.thedevelopercat.sonic.rest

open class SonicRequestHeaderManager {

    protected val headers = HashMap<String, String>()

    fun getHeaderMap(): HashMap<String, String> {
        return headers
    }

    fun addOrUpdateHeader(key: String, value: String){
        headers[key] = value
    }

    fun removeHeaderForKey(key: String){
        headers.remove(key)
    }

    fun containsValue(value: String): Boolean {
        return getAllValues().contains(value)
    }

    fun containsKey(key: String): Boolean {
        return getAllKeys().contains(key)
    }

    fun clearHeader(){
        headers.clear()
    }

    fun getAllKeys(): MutableSet<String> {
        return headers.keys
    }

    fun getAllValues(): MutableCollection<String> {
        return headers.values
    }

    fun isEmpty(): Boolean {
        return headers.keys.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }
}