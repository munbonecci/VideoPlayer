package com.munbonecci.videoplayer.features.video_detail.domain.model

import com.munbonecci.videoplayer.commons.Error
import com.munbonecci.videoplayer.domain.VideoEntity

data class VideoUIState(
    val isLoading: Boolean = false,
    val video: VideoEntity = VideoEntity(),
    val error: Error = Error()
)