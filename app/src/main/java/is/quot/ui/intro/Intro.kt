package `is`.quot.ui.intro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import `is`.quot.R
import `is`.quot.ui.common.RippleCircleAnimation

@Composable
fun IntroView(
    modifier: Modifier = Modifier,
    onWisdomInjectionClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(modifier = modifier) {

            Box(
                modifier = modifier
                    .align(Alignment.End)
                    .padding(all = 32.dp)
                    .clip(CircleShape)
                    .clickable { onWisdomInjectionClicked() }
            ) {

                RippleCircleAnimation(
                    modifier = modifier
                        .align(Alignment.Center)
                        .alpha(0.2f),
                    size = 300.dp,
                )

                WisdomInjectionLottieAnimation(
                    modifier = modifier
                        .align(Alignment.Center),
                    lottieResId = R.raw.lottie_inject,
                )
            }

            Text(
                modifier = modifier,
                text = stringResource(id = R.string.intro_message),
                color = Color.White,
                fontSize = 64.sp,
                lineHeight = (64 - 8).sp,
                fontWeight = FontWeight.Black,
            )
        }
    }
}

@Composable
fun WisdomInjectionLottieAnimation(modifier: Modifier = Modifier, lottieResId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResId))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    LottieAnimation(
        modifier = modifier.size(128.dp),
        composition = composition,
        progress = progress,
    )
}