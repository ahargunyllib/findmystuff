package com.ahargunyllib.internraion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.features.presentation.screen.auth.login.LoginScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.register.RegisterScreen
import com.ahargunyllib.internraion.features.presentation.screen.home.HomeScreen
import com.ahargunyllib.internraion.features.presentation.screen.maps.MapsScreen
import com.ahargunyllib.internraion.features.presentation.screen.privacy_policy.PrivacyPolicyScreen
import com.ahargunyllib.internraion.features.presentation.screen.report.ReportScreen
import com.ahargunyllib.internraion.features.presentation.screen.success_sign_up.SuccessSignUpScreen
import com.ahargunyllib.internraion.features.presentation.screen.welcome.WelcomeScreen
import com.ahargunyllib.internraion.util.Routes

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.WELCOME) {

        composable(Routes.WELCOME) {
            WelcomeScreen(navController = navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(Routes.SUCCESS_SIGN_UP){
            SuccessSignUpScreen(navController = navController)
        }
        composable(Routes.LOGIN){
            LoginScreen(navController = navController)
        }

        composable(Routes.PRIVACY_POLICY){
            PrivacyPolicyScreen(navController = navController)
        }

        composable(Routes.MAPS){
            MapsScreen(navController = navController)
        }

        composable(Routes.HOME){
            HomeScreen(navController = navController)
        }

        composable(Routes.REPORT){
            ReportScreen(navController = navController)
        }




    }
}