package com.munbonecci.videoplayer.domain

data class VideoEntity(
    val name: String = "",
    val description: String = "",
    val extraInfo: String = "",
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val profileImage: String = "",
    val contentDescription: String = "",
    val videoResultEntity: VideoResultEntity = VideoResultEntity()
) {
    override fun toString(): String = name
}