package com.munbonecci.videoplayer.commons.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

/**
 * Helper class for creating an icon resource either from a drawable resource ID or an ImageVector.
 *
 * @param resID The optional resource ID of a drawable.
 * @param imageVector The optional ImageVector for the icon.
 */
class IconResource private constructor(
    @DrawableRes private val resID: Int?,
    private val imageVector: ImageVector?
) {

    /**
     * Converts the IconResource to a Painter, either using the provided drawable resource ID or
     * the ImageVector.
     *
     * @return A Painter representing the icon.
     */
    @Composable
    fun asPainterResource(): Painter {
        // Check if a drawable resource ID is provided
        resID?.let {
            return painterResource(id = resID)
        }

        // If no drawable resource ID is provided, use the ImageVector
        return rememberVectorPainter(image = imageVector!!)
    }

    companion object {
        /**
         * Creates an IconResource from an ImageVector.
         *
         * @param imageVector The ImageVector for the icon.
         * @return An instance of IconResource.
         */
        fun fromImageVector(imageVector: ImageVector?): IconResource {
            return IconResource(null, imageVector)
        }
    }
}
