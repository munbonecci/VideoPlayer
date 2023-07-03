package com.munbonecci.videoplayer.features

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.domain.VideoEntity
import com.munbonecci.videoplayer.ui.theme.VideoPlayerTheme
import com.munbonecci.videoplayer.ui.theme.dimen_16dp
import com.munbonecci.videoplayer.ui.theme.dimen_5dp

@Composable
fun VideoScreen(viewModel: VideoViewModel = hiltViewModel()) {
    val item by viewModel.uiVideosState.collectAsState()
    val videos = item.videos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = dimen_16dp, end = dimen_16dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(dimen_5dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            items(videos) { video ->
                VideoItem(video, onItemClick = { data ->
                    Log.d("Skill pressed: ", data.name)
                })
            }
        }
    }
}

@Composable
fun VideoItem(video: VideoEntity, onItemClick: (VideoEntity) -> Unit) {

}

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