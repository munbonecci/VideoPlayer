package com.munbonecci.videoplayer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.munbonecci.videoplayer.ui.theme.dimen_0dp
import com.munbonecci.videoplayer.ui.theme.dimen_64dp

/**
 * Composable that displays a horizontally scrolling marquee text with faded edges.
 *
 * @param text The text to be displayed in the marquee.
 * @param color The color of the text.
 * @param fontSize The size of the text.
 * @param fontWeight The weight of the text.
 * @param layoutId The optional identifier for the layout.
 * @param edgeWidth edge width.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextMarqueeWithFadeEdge(
    text: String = "",
    color: Color = Color.White,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    layoutId: String = "",
    edgeWidth: Dp = dimen_64dp
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = Modifier
            // Set a maximum width for the marquee
            .widthIn(max = edgeWidth * 4)
            // Apply offscreen compositing strategy for better performance
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            // Draw faded edges on both sides
            .drawWithContent {
                drawContent()
                drawFadedEdge(leftEdge = true, edgeWidth)
                drawFadedEdge(leftEdge = false, edgeWidth)
            }
            // Apply basic marquee animation with specified iterations and spacing
            .basicMarquee(
                iterations = Int.MAX_VALUE,
                spacing = MarqueeSpacing(dimen_0dp)
            )
            // Add padding to prevent text from reaching the edges
            .padding(start = edgeWidth)
            // Set layout identifier if provided
            .layoutId(layoutId)
    )
}

/**
 * Extension function for [ContentDrawScope] to draw faded edges on either side of the content.
 *
 * @param leftEdge True if drawing the left edge, false for the right edge.
 */
fun ContentDrawScope.drawFadedEdge(leftEdge: Boolean, edgeWidth: Dp) {
    // Calculate edge width in pixels
    val edgeWidthPx = edgeWidth.toPx()

    // Draw a rectangle with horizontal gradient for faded effect
    drawRect(
        topLeft = Offset(if (leftEdge) 0f else size.width - edgeWidthPx, 0f),
        size = Size(edgeWidthPx, size.height),
        brush = Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startX = if (leftEdge) 0f else size.width,
            endX = if (leftEdge) edgeWidthPx else size.width - edgeWidthPx
        ),
        // Use BlendMode.DstIn for fading effect
        blendMode = BlendMode.DstIn
    )
}