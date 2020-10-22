package com.me.testapplication.di.services.user

import com.me.testapplication.di.services.api.models.UserResponse
import com.me.testapplication.di.services.user.models.User
import com.me.testapplication.utils.preferences.KEY_USER_IS_AUTH
import com.me.testapplication.utils.preferences.KEY_USER_PHONE
import com.me.testapplication.utils.preferences.getPrefs
import com.me.testapplication.utils.preferences.getPrefsEditor

class UserManagerService {

    var isAuth: Boolean
        get() = getPrefs().getBoolean(KEY_USER_IS_AUTH, false)
        set(isAuth) {
            getPrefsEditor().putBoolean(KEY_USER_IS_AUTH, isAuth).apply()
        }

    var phone: String
        get() = getPrefs().getString(KEY_USER_PHONE, "+1 234 567 890")!!
        set(phone) {
            getPrefsEditor().putString(KEY_USER_PHONE, phone).apply()
        }

    var user: User? = null

    fun clear() {
        phone = ""
        isAuth = false
        user = null
    }

    fun saveUserInfo(userResponse: UserResponse) {
        user = User(userResponse)
    }
}