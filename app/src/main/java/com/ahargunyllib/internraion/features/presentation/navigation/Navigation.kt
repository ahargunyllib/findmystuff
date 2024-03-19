package com.ahargunyllib.internraion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahargunyllib.internraion.features.presentation.screen.auth.SuccessSignUpScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.WelcomeScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.login.LoginScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.privacy_policy.PrivacyPolicyScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.register.RegisterScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.splash.SplashScreen
import com.ahargunyllib.internraion.features.presentation.screen.chat_list.ChatListScreen
import com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room.ChatRoomScreen

import com.ahargunyllib.internraion.features.presentation.screen.coming_soon.ComingSoonScreen
import com.ahargunyllib.internraion.features.presentation.screen.home.HomeScreen

import com.ahargunyllib.internraion.features.presentation.screen.maps.MapsScreen
import com.ahargunyllib.internraion.features.presentation.screen.notification.NotificationScreen

import com.ahargunyllib.internraion.features.presentation.screen.profile.ProfileScreen
import com.ahargunyllib.internraion.features.presentation.screen.report.ReportScreen
import com.ahargunyllib.internraion.features.presentation.screen.report.location_picker.LocationPickerScreen
import com.ahargunyllib.internraion.features.presentation.screen.report.report_detail.ReportDetailScreen

import com.ahargunyllib.internraion.features.presentation.screen.status.StatusScreen

import com.ahargunyllib.internraion.utils.Routes

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {

        composable(Routes.SPLASH_SCREEN){
            SplashScreen(navController = navController)
        }

        composable(Routes.WELCOME) {
            WelcomeScreen(navController = navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(Routes.SUCCESS_SIGN_UP) {
            SuccessSignUpScreen(navController = navController)
        }
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.PRIVACY_POLICY) {
            PrivacyPolicyScreen(navController = navController)
        }

        composable(Routes.MAPS) {
            MapsScreen(navController = navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        
        composable(Routes.COMING_SOON){
            ComingSoonScreen(navController = navController)
        }

        composable(
            route = "${Routes.REPORT}/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.FloatType
                },
                navArgument("longitude") {
                    type = NavType.FloatType
                },
            )
        ) {
            val latitude = it.arguments?.getFloat("latitude") ?: 0.0
            val longitude = it.arguments?.getFloat("longitude") ?: 0.0
            ReportScreen(navController = navController, latitude = latitude.toDouble(), longitude = longitude.toDouble())
        }
        composable(Routes.LOCATION_PICKER) {
            LocationPickerScreen(navController = navController)
        }

        composable(
            route = "${Routes.REPORT_DETAIL}/{reportId}",
            arguments = listOf(
                navArgument("reportId") {
                    type = NavType.IntType
                })
        ) {
            val reportId = it.arguments?.getInt("reportId") ?: 0
            ReportDetailScreen(navController = navController, reportId = reportId)
        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = "${Routes.CHAT_ROOM}/{chatRoomId}",
            arguments = listOf(
                navArgument("chatRoomId") {
                    type = NavType.StringType
                })

        ) {
            val chatRoomId = it.arguments?.getString("chatRoomId") ?: ""
            ChatRoomScreen(navController = navController, chatRoomId = chatRoomId)
        }

        composable(Routes.CHAT_LIST) {
            ChatListScreen(navController = navController)
        }

        composable(Routes.NOTIFICATION){
            NotificationScreen(navController = navController)
        }
        
        composable(Routes.STATUS){
            StatusScreen(navController = navController)
        }
    }
}
