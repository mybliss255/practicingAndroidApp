package com.example.myboardgameapplication.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val displayName: String
        //UIで使用可能なほかのデータを追加可能
)