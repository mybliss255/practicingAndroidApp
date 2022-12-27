package com.example.myboardgameapplication.ui.login

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.myboardgameapplication.data.LoginRepository

import com.example.myboardgameapplication.R
import com.example.myboardgameapplication.data.model.LoggedInUser
import com.google.firebase.auth.*
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    val tag:String = "LOGINVIEWMODEL"

    fun login(username: String, password: String,auth: FirebaseAuth,activity: Activity) {
        Log.d(tag,"login initiated")
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity) { task ->

                    if (task.isSuccessful) {
                        Log.d(tag, "login success")
                        val authedUser = auth.currentUser
                        val loggedin =
                            LoggedInUser(authedUser!!.uid, authedUser.email.toString())
                        _loginResult.value =
                            LoginResult(success = LoggedInUserView(displayName = authedUser.email.toString()))
                        loginRepository.setLoggedInUser(loggedin)
                    } else {

                        Log.d(tag, "login exception")
                        Log.d(tag, task.exception?.message.toString())
                        when (task.exception) {
                            //そのようなメールアドレスが存在しない場合
                            is FirebaseAuthInvalidUserException -> {
                                _loginResult.value =
                                    LoginResult(error = R.string.signin_InvalidUser)
                            }
                            //パスワードが間違っている場合
                            is FirebaseAuthInvalidCredentialsException -> {
                                _loginResult.value =
                                    LoginResult(error = R.string.signin_wrongPassword)
                            }
                            else -> {
                                _loginResult.value = LoginResult(error = R.string.signin_failed)
                            }
                        }
                    }
                }
        }
    }

    fun signup(username: String, password: String,auth: FirebaseAuth, activity: Activity) {
        Log.d(tag,"signup initiated")
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity) { task ->

                    if (task.isSuccessful) {
                        Log.d(tag, "signup success")
                        val authedUser = auth.currentUser
                        val loggedin = LoggedInUser(authedUser!!.uid, authedUser.email.toString())
                        _loginResult.value =
                            LoginResult(success = LoggedInUserView(displayName = authedUser.email.toString()))
                        loginRepository.setLoggedInUser(loggedin)
                    } else {

                        Log.d(tag, "signup exception")
                        Log.d(tag, task.exception?.message.toString())
                        when (task.exception) {
                            //パスワードが弱すぎる
                            is FirebaseAuthWeakPasswordException -> {
                                _loginResult.value =
                                    LoginResult(error = R.string.signup_weakPassword)
                            }
                            //メールアドレスの形式が不正
                            is FirebaseAuthInvalidCredentialsException -> {
                                _loginResult.value =
                                    LoginResult(error = R.string.signup_malformedAddress)
                            }
                            //既に使用されているメールアドレス
                            is FirebaseAuthUserCollisionException -> {
                                _loginResult.value =
                                    LoginResult(error = R.string.signup_addressInUse)
                            }
                            else -> {
                                _loginResult.value = LoginResult(error = R.string.signup_failed)
                            }
                        }
                    }
                }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // ユーザー名（メールアドレス）のバリデーションチェック
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // パスワードのバリデーションチェック
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}