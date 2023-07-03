package com.munbonecci.videoplayer.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.munbonecci.videoplayer.R
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.commons.utils.IconResource
import com.munbonecci.videoplayer.ui.theme.dimen_5dp


@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    icon: Painter,
    iconColor: Color = Color.White,
    backGroundColor: Color = MaterialTheme.colorScheme.surface,
    contentDescription: String = stringResource(id = R.string.circle_icon_button),
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.layoutId(layoutId),
        onClick = { onClick.invoke() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = backGroundColor,
                    shape = CircleShape
                )
        ) {
            Icon(
                icon,
                contentDescription = contentDescription,
                modifier = Modifier.padding(dimen_5dp),
                tint = iconColor
            )
        }
    }
}


@Composable
@Preview(name = Constants.LIGHT_MODE)
@Preview(name = Constants.DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewCircleIconButton() {
    CircleIconButton(
        icon = IconResource.fromImageVector(Icons.Filled.Person).asPainterResource()
    ) {}
}