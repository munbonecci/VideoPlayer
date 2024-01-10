package com.munbonecci.core.navigation

/**
 * Sealed class representing different navigation items in the application.
 * Each object in the sealed class corresponds to a specific route in the navigation graph.
 * @property route The route associated with the navigation item.
 */
sealed class NavigationItem(var route: String) {
    /**
     * Represents the main video listing screen.
     */
    object Video :
        NavigationItem(
            route = VIDEO_ROUTE
        )

    /**
     * Represents the detailed view of a video.
     * videoId The unique identifier of the video.
     * videoName The name or title of the video.
     */
    object VideoDetail :
        NavigationItem(
            route = "$VIDEO_DETAIL_ROUTE/{videoId}/{videoName}"
        )

    companion object {
        // Constants representing route names
        const val VIDEO_ROUTE = "video"
        const val VIDEO_DETAIL_ROUTE = "video_detail"
    }
}