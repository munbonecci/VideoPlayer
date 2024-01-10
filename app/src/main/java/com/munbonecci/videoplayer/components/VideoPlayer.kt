package com.munbonecci.videoplayer.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.munbonecci.videoplayer.commons.Constants.VIDEO_PLAYER_TAG
import com.munbonecci.videoplayer.domain.VideoResultEntity

@SuppressLint("OpaqueUnitKey")
@ExperimentalAnimationApi
@Composable
fun VideoPlayer(
    video: VideoResultEntity,
    playingIndex: State<Int>,
    onVideoChange: (Int) -> Unit,
    isVideoEnded: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val visible = remember {
        mutableStateOf(true)
    }
    val videoTitle = remember {
        mutableStateOf(video.name)
    }
    val mediaItems = arrayListOf<MediaItem>()
    mediaItems.add(
        MediaItem.Builder().setUri(video.video).setMediaId(video.id.toString()).setTag(video)
            .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(video.name).build())
            .build()
    )
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            this.setMediaItems(mediaItems)
            this.prepare()
            this.addListener(object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    if (player.contentPosition >= 200) visible.value = false
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    onVideoChange(this@apply.currentPeriodIndex)
                    visible.value = true
                    videoTitle.value = mediaItem?.mediaMetadata?.displayTitle.toString()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    if (playbackState == ExoPlayer.STATE_ENDED) {
                        isVideoEnded.invoke(true)
                    }
                }
            })
        }
    }

    exoPlayer.seekTo(playingIndex.value, C.TIME_UNSET)
    exoPlayer.playWhenReady = true

    LocalLifecycleOwner.current.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_START -> {
                    if (exoPlayer.isPlaying.not()) {
                        exoPlayer.play()
                    }
                }

                Lifecycle.Event.ON_STOP -> {
                    exoPlayer.pause()
                }

                else -> {
                    //Nothing
                }
            }
        }
    })

    Column(modifier = modifier.background(Color.Black)) {
        DisposableEffect(
            AndroidView(
                modifier = modifier
                    .testTag(VIDEO_PLAYER_TAG),
                factory = {
                    StyledPlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        setShowNextButton(false)
                        setShowPreviousButton(false)
                        setShowFastForwardButton(false)
                        setShowRewindButton(false)
                    }
                })
        ) {
            onDispose {
                exoPlayer.release()
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ShowVideoPreview() {
    val video = VideoResultEntity(
        1,
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "Bunny",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    )
    val playingIndex = remember {
        mutableIntStateOf(0)
    }

    fun onTrailerChange(index: Int) {
        playingIndex.intValue = index
    }
    VideoPlayer(
        video = video,
        playingIndex = playingIndex,
        onVideoChange = { newIndex -> onTrailerChange(newIndex) },
        isVideoEnded = {}
    )
}