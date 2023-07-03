package com.munbonecci.videoplayer.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.munbonecci.videoplayer.commons.Constants
import com.munbonecci.videoplayer.commons.utils.IconResource

@Composable
fun IconButtonWithText(
    layoutId: String = "",
    icon: Painter,
    tintColor: Color = Color.White,
    text: String = "",
    textColor: Color = Color.White,
    contentDesc: String = "",
    fontSize: TextUnit = 11.sp,
    onButtonPressed: () -> Unit
) {
    IconButton(
        modifier = Modifier.layoutId(layoutId),
        onClick = { onButtonPressed.invoke() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                tint = tintColor,
                contentDescription = contentDesc,
            )
            if (text.isNotEmpty()) {
                Text(
                    text = text,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize
                )
            }
        }
    }
}

@Preview(name = Constants.LIGHT_MODE)
@Preview(name = Constants.DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowIconButtonPreview() {
    IconButtonWithText(
        onButtonPressed = {},
        icon = IconResource.fromImageVector(Icons.Filled.Person).asPainterResource()
    )
}