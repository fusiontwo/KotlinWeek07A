package com.example.week07a.example2

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Welcome : Routes("Welcome")
    object Register : Routes("Register")
}

@Composable
fun LoginNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route = Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Routes.Welcome.route + "?userID={userID}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                    defaultValue = "user"
                }
            )
        ) {
            WelcomeScreen(
                navController,
                it.arguments?.getString("userID")
            )
        }

        composable(route= Routes.Register.route+"?userID={userID}&userpasswd={userPasswd}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                    defaultValue = "user"
                },
                navArgument(name = "userPasswd") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
        Log.d("userid", it.arguments?.getString("userID")!!)
        Register(navController = navController,
            userID = it.arguments?.getString("userID"),
            userPasswd = it.arguments?.getString("userPasswd"))
        }
    }

}