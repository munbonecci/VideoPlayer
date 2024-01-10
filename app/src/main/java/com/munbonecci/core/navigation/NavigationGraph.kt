package com.munbonecci.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.munbonecci.videoplayer.features.video.VideoScreen
import com.munbonecci.videoplayer.features.video_detail.VideoDetailScreen

/**
 * Composable function representing the navigation graph of the application using Jetpack Compose.
 * @param navController The NavController responsible for handling navigation between composable.
 */
@Composable
fun NavigationGraph(navController: NavHostController) {
    // Define the navigation graph using NavHost
    NavHost(navController, startDestination = NavigationItem.Video.route) {
        // Composable for the main video listing screen
        composable(NavigationItem.Video.route) {
            // Handle back button press when on the main video screen
            BackHandler(true) {}
            // Display the main video screen
            VideoScreen(onVideoPressed = {
                // Navigate to the video detail screen when a video is pressed
                navController.navigate("${NavigationItem.VideoDetail.route}/${it.videoResultEntity.id}")
            })
        }

        // Composable for the video detail screen with dynamic videoId argument
        composable(
            "${NavigationItem.VideoDetail.route}/{videoId}",
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType }
            )
        ) {
            // Display the video detail screen and handle back button press
            VideoDetailScreen(onBackButtonClicked = {
                // Navigate back to the main video screen
                navController.navigate(NavigationItem.Video.route)
            }, it.arguments?.getString("videoId"))
        }
    }
}