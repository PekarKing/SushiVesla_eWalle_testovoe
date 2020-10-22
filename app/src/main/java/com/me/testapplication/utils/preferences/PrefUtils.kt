package com.me.testapplication.utils.preferences

import android.content.Context
import android.content.SharedPreferences
import com.me.testapplication.di.DIManager

fun getPrefs(): SharedPreferences = DIManager.appComponent.context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)

fun getPrefsEditor(): SharedPreferences.Editor = getPrefs().edit()