package com.munbonecci.videoplayer.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.videoplayer.commons.Error
import com.munbonecci.videoplayer.commons.Resource
import com.munbonecci.videoplayer.features.video.domain.model.VideoUIState
import com.munbonecci.videoplayer.features.video.domain.use_case.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val getVideosUseCase: GetVideosUseCase) :
    ViewModel() {
    private val _uiVideosState = MutableStateFlow(VideoUIState())
    val uiVideosState: StateFlow<VideoUIState> = _uiVideosState.asStateFlow()

    init {
        getVideos()
    }

    private fun getVideos() {
        getVideosUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiVideosState.value =
                        VideoUIState(error = Error(errorMessage = result.message ?: ""))
                }

                is Resource.Loading -> {
                    _uiVideosState.value = VideoUIState(isLoading = true)
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiVideosState.value = VideoUIState(videos = data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}