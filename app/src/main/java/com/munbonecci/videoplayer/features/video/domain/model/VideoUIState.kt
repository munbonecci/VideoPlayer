package com.munbonecci.videoplayer.features.video.domain.model

import com.munbonecci.videoplayer.commons.Error
import com.munbonecci.videoplayer.domain.VideoEntity

data class VideoUIState(
    val isLoading: Boolean = false,
    val videos: List<VideoEntity> = listOf(),
    val error: Error = Error()
)