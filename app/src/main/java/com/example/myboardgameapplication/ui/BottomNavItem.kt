package com.example.myboardgameapplication.ui

import com.example.myboardgameapplication.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_login_48dp,"home")
    object MyNetwork: BottomNavItem("My Network",R.drawable.ic_login_48dp,"my_network")
    object AddPost: BottomNavItem("Post",R.drawable.ic_login_48dp,"add_post")
    object Notification: BottomNavItem("Notification",R.drawable.ic_login_48dp,"notification")
    object Jobs: BottomNavItem("Jobs",R.drawable.ic_login_48dp,"jobs")

    object Collection : BottomNavItem("Collection", R.drawable.ic_login_48dp, "collection")
    object Event : BottomNavItem("Event", R.drawable.ic_login_48dp, "event")
    object Friend : BottomNavItem("Friend", R.drawable.ic_login_48dp, "friend")
}