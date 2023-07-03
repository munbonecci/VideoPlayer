package com.munbonecci.videoplayer.features

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.components.VideoPlayer
import com.munbonecci.videoplayer.domain.VideoEntity
import com.munbonecci.videoplayer.ui.theme.VideoPlayerTheme
import com.munbonecci.videoplayer.ui.theme.dimen_0dp
import com.munbonecci.videoplayer.ui.theme.dimen_16dp
import com.munbonecci.videoplayer.ui.theme.dimen_200dp
import com.munbonecci.videoplayer.ui.theme.dimen_8dp

@Composable
fun VideoScreen(viewModel: VideoViewModel = hiltViewModel()) {
    val item by viewModel.uiVideosState.collectAsState()
    val videos = item.videos
    val playingIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.layoutId(VIDEO_LAZY_COLUMN_ID)
        ) {
            itemsIndexed(videos) { index, video ->
                playingIndex.value = index
                VideoItem(video, onItemClick = { data ->
                    Log.d("Video pressed: ", data.name)
                }, playingIndex)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VideoItem(
    video: VideoEntity,
    onItemClick: (VideoEntity) -> Unit,
    playingIndex: MutableState<Int>
) {
    fun onTrailerChange(index: Int) {
        playingIndex.value = index
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimen_16dp)
            .clickable {
                onItemClick.invoke(video)
            }
            .background(color = Color.DarkGray)
            .layoutId(VIDEO_COLUMN_ID),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        VideoPlayer(
            video = video.videoResultEntity,
            playingIndex = playingIndex,
            onVideoChange = { newIndex -> onTrailerChange(newIndex) },
            isVideoEnded = {},
            modifier = Modifier
                .fillMaxWidth()
                .size(width = dimen_0dp, height = dimen_200dp)
        )
        Text(
            modifier = Modifier
                .layoutId(NAME_ID)
                .padding(dimen_8dp),
            text = video.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier
                .layoutId(DESCRIPTION_ID)
                .padding(dimen_8dp),
            text = video.description,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}

const val NAME_ID = "video_name_txt"
const val DESCRIPTION_ID = "video_description_id"
const val VIDEO_COLUMN_ID = "video_column"
const val VIDEO_LAZY_COLUMN_ID = "video_lazy_column"

@ExperimentalAnimationApi
@Preview(name = Constants.LIGHT_MODE)
@Preview(name = Constants.DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowVideoPreview() {
    VideoPlayerTheme {
        Surface {
            VideoScreen()
        }
    }
}