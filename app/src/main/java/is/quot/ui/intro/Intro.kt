package `is`.quot.ui.intro

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import `is`.quot.R
import `is`.quot.ui.QuoteViewModel
import kotlinx.coroutines.delay

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

                    WisdomInjectionLottieAnimation(
                        modifier = modifier
                            .align(Alignment.Center)
                            .clickable { viewModel.getQuote() },
                        lottieResId = R.raw.lottie_inject,
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
fun WisdomInjectionLottieAnimation(modifier: Modifier = Modifier, lottieResId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_inject))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    LottieAnimation(
        modifier = modifier.size(128.dp),
        composition = composition,
        progress = progress,
    )
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