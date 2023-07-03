package com.munbonecci.videoplayer.features

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.ui.theme.VideoPlayerTheme

@Composable
fun VideoScreen() {

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