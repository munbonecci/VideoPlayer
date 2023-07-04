package com.munbonecci.videoplayer.features.video_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.videoplayer.commons.Error
import com.munbonecci.videoplayer.commons.Resource
import com.munbonecci.videoplayer.features.video_detail.domain.model.VideoUIState
import com.munbonecci.videoplayer.features.video_detail.domain.use_case.GetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoDetailViewModel @Inject constructor(private val getVideoUseCase: GetVideoUseCase) :
    ViewModel() {
    private val _uiVideoState = MutableStateFlow(VideoUIState())
    val uiVideoState: StateFlow<VideoUIState> = _uiVideoState.asStateFlow()

    fun getVideo(videoId: String) {
        getVideoUseCase.invoke(videoId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiVideoState.value =
                        VideoUIState(error = Error(errorMessage = result.message ?: ""))
                }

                is Resource.Loading -> {
                    _uiVideoState.value = VideoUIState(isLoading = true)
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiVideoState.value = VideoUIState(video = data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}