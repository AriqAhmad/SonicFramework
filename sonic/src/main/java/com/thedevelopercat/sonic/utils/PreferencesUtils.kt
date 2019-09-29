package com.thedevelopercat.sonic.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.thedevelopercat.sonic.utils.PreferencesKey.*


enum class PreferencesKey {
    PREF_NAME,
    IS_LOGGED_IN,
    ID
}


class PreferencesUtils {

    internal lateinit var sharedPref: SharedPreferences
    internal var editor: SharedPreferences.Editor? = null

    companion object {
        val instance = PreferencesUtils()
        fun initSharedPreferences(context: Context) {
            instance.sharedPref = context.getSharedPreferences(
                PREF_NAME.name, Activity.MODE_PRIVATE
            )
            instance.editor = instance.sharedPref.edit()
        }
    }

    var id: String?
        get() = getData(ID, "")
        set(value) {
            saveData(ID, value)
        }

    var isLoggedIn: Boolean
        get() = getData(IS_LOGGED_IN, false)
        set(status) {
            saveData(IS_LOGGED_IN, status)
        }
}


// Helpers
@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: String?): Boolean {
    editor!!.putString(key.name, value)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: Set<String>): Boolean {
    editor!!.putStringSet(key.name, value)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: Boolean): Boolean {
    editor!!.putBoolean(key.name, value)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: Long): Boolean {
    editor!!.putLong(key.name, value)
    return editor!!.commit()
}


@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: Float): Boolean {
    editor!!.putFloat(key.name, value)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.saveData(key: PreferencesKey, value: Int): Boolean {
    editor!!.putInt(key.name, value)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.removeData(key: PreferencesKey): Boolean {
    editor!!.remove(key.name)
    return editor!!.commit()
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: Boolean): Boolean {
    return sharedPref.getBoolean(key.name, defaultValue)
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: String?): String? {
    return sharedPref.getString(key.name, defaultValue)
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: Set<String>): Set<String>? {
    return sharedPref.getStringSet(key.name, defaultValue)
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: Float): Float {

    return sharedPref.getFloat(key.name, defaultValue)
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: Int): Int {
    return sharedPref.getInt(key.name, defaultValue)
}

@Synchronized
private fun PreferencesUtils.getData(key: PreferencesKey, defaultValue: Long): Long {
    return sharedPref.getLong(key.name, defaultValue)
}

@Synchronized
public fun PreferencesUtils.deleteAllData() {
    editor!!.clear()
    editor!!.commit()
}