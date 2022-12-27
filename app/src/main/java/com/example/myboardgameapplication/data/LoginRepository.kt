package com.example.myboardgameapplication.data

import android.app.Activity
import android.widget.EditText
import com.example.myboardgameapplication.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // ユーザーのクレジット関係のデータをローカルで保持するならば暗号化されるのが望ましい
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}