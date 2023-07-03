package com.munbonecci.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.munbonecci.videoplayer.R

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: Int) {
    object Video :
        NavigationItem(
            route = VIDEO_ROUTE,
            icon = Icons.Default.Home,
            title = R.string.video_category
        )

    object VideoDetail :
        NavigationItem(
            route = "$VIDEO_DETAIL_ROUTE/{videoId}/{videoName}",
            icon = Icons.Default.Menu,
            title = R.string.video_detail_category
        )

    companion object {
        const val VIDEO_ROUTE = "video"
        const val VIDEO_DETAIL_ROUTE = "video_detail"
    }
}