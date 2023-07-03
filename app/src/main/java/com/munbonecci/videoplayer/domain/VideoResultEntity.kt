package com.munbonecci.videoplayer.domain

data class VideoResultEntity(
    val id: Int = 0,
    val preview: String = "",
    val name: String = "",
    val video: String = "",
    val videoType: Int = 0,
    val videoCreationDate: String = ""
)