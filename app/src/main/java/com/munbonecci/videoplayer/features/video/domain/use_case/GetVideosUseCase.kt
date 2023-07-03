package com.munbonecci.videoplayer.features.video.domain.use_case

import android.content.Context
import com.munbonecci.videoplayer.commons.Resource
import com.munbonecci.videoplayer.domain.VideoEntity
import com.munbonecci.videoplayer.domain.VideoResultEntity
import com.munbonecci.videoplayer.features.video.domain.use_case.VideoConstants.ERROR_CODE_SERVICE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(private val context: Context) {
    operator fun invoke(): Flow<Resource<List<VideoEntity>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(getVideosFakeData))
    }.catch {
        emit(Resource.Error(ERROR_CODE_SERVICE))
    }
}

object VideoConstants {
    const val ERROR_CODE_SERVICE = "-9"
}

val getVideosFakeData: List<VideoEntity>
    get() = listOf(
        VideoEntity(
            name = "1",
            description = "This Video is used for Testing, the content is not mine and free to use",
            extraInfo = "#extra @test, #extra @test, #extra @test, #extra @test, #extra @test, #extra @test",
            videoResultEntity = VideoResultEntity(
                1,
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "Bunny",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        ),
        VideoEntity(
            name = "2",
            description = "Video for Test 2",
            extraInfo = "#extra @test2",
            videoResultEntity = VideoResultEntity(
                2,
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "Bunny 2",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        ),
        VideoEntity(
            name = "3",
            description = "Video for Test 3",
            extraInfo = "#extra @test3",
            videoResultEntity = VideoResultEntity(
                3,
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "Bunny 3",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        ),
        VideoEntity(
            name = "4",
            description = "Video for Test 4",
            extraInfo = "#extra @test4",
            videoResultEntity = VideoResultEntity(
                3,
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "Bunny 4",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        )
    )