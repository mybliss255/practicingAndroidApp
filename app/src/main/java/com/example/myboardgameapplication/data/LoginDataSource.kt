package com.example.myboardgameapplication.data

import com.example.myboardgameapplication.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            if(username.equals("takoyaki") && password.equals("123123")){
                val user = LoggedInUser("001","takoyaki")
                return Result.Success(user)
            }

            return Result.Fail()

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}