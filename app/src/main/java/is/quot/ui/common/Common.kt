package `is`.quot.ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RippleCircleAnimation(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    size: Dp = 100.dp,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Restart
        ),
        label = "",
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val rippleRadius = size.toPx() * animatedProgress
            // Fade out as the ripple expands
            val alpha = 1f - animatedProgress
            drawCircle(
                color = color.copy(alpha = alpha),
                radius = rippleRadius
            )
        }
    }
}