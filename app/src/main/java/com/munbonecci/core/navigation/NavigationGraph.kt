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

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Video.route) {
        composable(NavigationItem.Video.route) {
            BackHandler(true) {}
            VideoScreen(onVideoPressed = {
                navController.navigate("${NavigationItem.VideoDetail.route}/${it.videoResultEntity.id}")
            })
        }
        composable(
            "${NavigationItem.VideoDetail.route}/{videoId}",
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType }
            )
        ) {
            VideoDetailScreen(onBackButtonClicked = {
                navController.navigate(NavigationItem.Video.route)
            }, it.arguments?.getString("videoId"))
        }
    }
}