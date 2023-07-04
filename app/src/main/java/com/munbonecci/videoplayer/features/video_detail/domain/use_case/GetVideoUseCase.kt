package com.munbonecci.videoplayer.features.video_detail.domain.use_case

import android.content.Context
import com.munbonecci.videoplayer.commons.Resource
import com.munbonecci.videoplayer.domain.VideoEntity
import com.munbonecci.videoplayer.features.video.domain.use_case.getVideosFakeData
import com.munbonecci.videoplayer.features.video_detail.domain.use_case.VideoConstants.ERROR_CODE_SERVICE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val context: Context) {
    operator fun invoke(videoId: String): Flow<Resource<VideoEntity?>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(getSelectedVideo(videoId = videoId)))
    }.catch {
        emit(Resource.Error(ERROR_CODE_SERVICE))
    }
}

object VideoConstants {
    const val ERROR_CODE_SERVICE = "-9"
}

fun getSelectedVideo(videoId: String): VideoEntity? {
    return getVideosFakeData.find { it.videoResultEntity.id == videoId.toInt() }
}