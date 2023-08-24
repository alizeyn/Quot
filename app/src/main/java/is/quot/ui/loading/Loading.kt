package `is`.quot.ui.loading

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `is`.quot.R
import `is`.quot.ui.common.RippleCircleAnimation


@Composable
fun LoadingView(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
    ) {
        Column(modifier = modifier) {

            Box(
                modifier = modifier
                    .align(Alignment.End)
                    .padding(all = 32.dp)
                    .clip(CircleShape)
            ) {

                RippleCircleAnimation(
                    modifier = modifier
                        .align(Alignment.Center)
                        .alpha(0.2f),
                    size = 300.dp,
                )
            }

            Text(
                modifier = modifier,
                text = stringResource(id = R.string.loading_message),
                color = Color.White,
                fontSize = 64.sp,
                lineHeight = (64 - 8).sp,
                fontWeight = FontWeight.Black,
            )
        }
    }
}

@Composable
@Preview
fun LoadingViewPreview() {
    LoadingView()
}