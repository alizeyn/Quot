package `is`.quot.ui.intro

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import `is`.quot.R
import `is`.quot.ui.QuoteViewModel
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun IntroView(
    modifier: Modifier = Modifier,
    viewModel: QuoteViewModel,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Row(
            modifier = modifier
                .wrapContentHeight()
                .align(Alignment.BottomStart)
        ) {
            Column(modifier = modifier) {

                Box(
                    modifier = modifier
                        .align(Alignment.End)
                ) {
                    RippleLoadingAnimation(
                        modifier = modifier.align(Alignment.Center),
                        circleColor = Color.White,
                        size = 300.dp,
                    )

                    GifImage(
                        modifier = modifier
                            .align(Alignment.Center)
                            .clickable {
                                viewModel.getQuote()
                            },
                        size = 200.dp,
                        gif = R.drawable.inject,
                        grayscale = true,
                    )
                }

                Text(
                    modifier = modifier,
                    text = "Get your daily dose of wisdom",
                    color = Color.White,
                    fontSize = 64.sp,
                    lineHeight = (64 - 8).sp,
                    fontWeight = FontWeight.Black,
                )
            }
        }
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    @DrawableRes gif: Int,
    size: Dp,
    grayscale: Boolean = false,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.White)
            .border(4.dp, Color.White, CircleShape)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context)
                    .data(data = gif)
                    .apply {
                        size(Size.ORIGINAL)
                    }
                    .build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .align(Alignment.Center)
                .scale(0.7f)
                .background(Color.White),
            colorFilter = if (grayscale) ColorFilter.tint(Color.Black) else null,
        )
    }

}

@Composable
fun RippleLoadingAnimation(
    modifier: Modifier,
    circleColor: Color = Color.Magenta,
    animationDelay: Int = 1500,
    size: Dp,
) {

    val circles = listOf(
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            // Use coroutine delay to sync animations
            // divide the animation delay by number of circles
            delay(timeMillis = (animationDelay / 3L) * (index + 1))

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    Box(
        modifier = modifier
            .size(size = size)
            .background(color = Color.Transparent)
    ) {
        circles.forEachIndexed { _, animatable ->
            Box(
                modifier = modifier
                    .scale(scale = animatable.value)
                    .size(size = size)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            ) {
            }
        }
    }
}