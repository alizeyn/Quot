package `is`.quot.ui.quote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.compose_loading.DoubleBounce

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.BottomStart,
    ) {
        Column(modifier = modifier) {
            DoubleBounce(
                modifier = modifier
                    .padding(start = 64.dp, bottom = 64.dp),
                color = Color.White,
                size = DpSize(128.dp, 128.dp)
            )
            Text(
                text = "Loading years of wisdom",
                modifier = modifier,
                color = Color.White,
                lineHeight = 30.sp,
                fontSize = 36.sp
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Loading()
}