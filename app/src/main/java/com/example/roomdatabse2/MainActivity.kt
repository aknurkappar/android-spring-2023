package com.example.roomdatabse2

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomdatabse2.data.UserViewModel
import com.example.roomdatabse2.ui.theme.RoomDatabse2Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.roomdatabse2.screens.LoginPage
import com.example.roomdatabse2.screens.RegistrationPage
import com.example.roomdatabse2.screens.StartPage
import com.example.roomdatabse2.screens.WelcomePage
import androidx.navigation.compose.*
import com.example.roomdatabse2.data.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabse2Theme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }
}

@Composable
fun App(){
    val context = LocalContext.current
    val myUserViewModel : UserViewModel = viewModel(
        factory = UserViewModel.UserViewModelFactory((context.applicationContext) as Application))

    val usersData = myUserViewModel.readAllData.observeAsState(listOf()).value

//    myUserViewModel.addUser(User("Aknur", "aknur@gmail.com", "456"))
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome-page") {
        composable("welcome-page") {
            WelcomePage(navigateToRegisterNow = { navController.navigate("register-now") },
                navigateToLogIn = { navController.navigate("login") })
        }
        composable("login") {
            LoginPage(
                navigateBack = {
                    navController.popBackStack(
                        "welcome-page",
                        inclusive = false
                    )
                },
                navigateToStartPage = { navController.navigate("start-page") },
                usersData
            )
        }
        composable("register-now") {
            RegistrationPage(
                navigateBack = {
                    navController.popBackStack("welcome-page", inclusive = false)
                },
                navigateToStartPage = { navController.navigate("start-page") },
                usersData, myUserViewModel
            )
        }
        composable("start-page") {
            StartPage(navigateBack = {
                navController.popBackStack("welcome-page", inclusive = false)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomDatabse2Theme {
        App()
    }
}