package com.munbonecci.videoplayer.features.video_detail

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.commons.Constants.EXTRA_INFO_LENGTH
import com.munbonecci.videoplayer.commons.utils.IconResource
import com.munbonecci.videoplayer.components.CircleIconButton
import com.munbonecci.videoplayer.components.ExpandableText
import com.munbonecci.videoplayer.components.IconButtonWithText
import com.munbonecci.videoplayer.components.TextMarqueeWithFadeEdge
import com.munbonecci.videoplayer.components.VideoPlayer
import com.munbonecci.videoplayer.domain.VideoEntity
import com.munbonecci.videoplayer.ui.theme.VideoPlayerTheme
import com.munbonecci.videoplayer.ui.theme.dimen_100dp
import com.munbonecci.videoplayer.ui.theme.dimen_16dp
import com.munbonecci.videoplayer.ui.theme.dimen_2dp
import com.munbonecci.videoplayer.ui.theme.dimen_50dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VideoDetailScreen(onBackButtonClicked: () -> Unit, videoId: String?) {
    var isFavoriteSelected by remember { mutableStateOf(false) }
    val playingIndex = remember { mutableStateOf(0) }
    val item = VideoEntity()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        VideoPlayer(
            video = item.videoResultEntity,
            playingIndex = playingIndex,
            onVideoChange = { newIndex -> playingIndex.value = newIndex },
            isVideoEnded = {},
        )
        Box(
            modifier = Modifier
                .padding(dimen_2dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimen_16dp, bottom = dimen_50dp, end = dimen_16dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                CircleIconButton(
                    layoutId = PROFILE_ID,
                    icon = IconResource.fromImageVector(Icons.Filled.Person).asPainterResource(),
                    iconColor = Color.Black
                ) {

                }
                IconButtonWithText(
                    layoutId = FAVORITE_ID,
                    icon = IconResource.fromImageVector(Icons.Filled.Favorite)
                        .asPainterResource(),
                    tintColor = if (isFavoriteSelected) Color.Red else Color.White
                ) {
                    isFavoriteSelected = !isFavoriteSelected
                }
                IconButtonWithText(
                    layoutId = SHARE_ID,
                    icon = IconResource.fromImageVector(Icons.Filled.Share).asPainterResource()
                ) {

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimen_16dp, bottom = dimen_50dp, end = dimen_100dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.layoutId(NAME_ID),
                    text = item.videoResultEntity.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                ExpandableText(
                    modifier = Modifier.layoutId(DESCRIPTION_ID),
                    text = item.description,
                    textColor = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                if (item.extraInfo.length > EXTRA_INFO_LENGTH) {
                    TextMarqueeWithFadeEdge(text = item.extraInfo, layoutId = EXTRA_INFO_ID)
                } else {
                    Text(
                        modifier = Modifier.layoutId(EXTRA_INFO_ID),
                        text = item.extraInfo,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

const val PROFILE_ID = "Profile"
const val FAVORITE_ID = "Favorite"
const val SHARE_ID = "Share"
const val NAME_ID = "name"
const val DESCRIPTION_ID = "description"
const val EXTRA_INFO_ID = "extra_info"

@ExperimentalAnimationApi
@Preview(name = Constants.LIGHT_MODE)
@Preview(name = Constants.DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowVideoPreview() {
    VideoPlayerTheme {
        Surface {
            VideoDetailScreen(
                onBackButtonClicked = {},
                ""
            )
        }
    }
}