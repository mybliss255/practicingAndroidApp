package com.example.myboardgameapplication.data

import android.app.Activity
import android.util.Log
import com.example.myboardgameapplication.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val tag = "LoginDataSource"

    fun logout() {
        // TODO: revoke authentication
    }
}