package `is`.quot.ui.quote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import `is`.quot.domain.model.Quote

@Composable
fun QuoteView(quote: Quote, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.BottomStart),
        ) {
            quote.imageUrl?.let {
                GlideImage(
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                        .clip(CircleShape),
                    imageModel = it,
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 250),
                )
            }
            Text(
                text = quote.text,
                modifier = modifier,
                color = Color.White,
                lineHeight = 32.sp,
                fontSize = 32.sp
            )
            Text(
                text = " - ${quote.author}",
                modifier = modifier,
                color = Color.LightGray,
                fontSize = 14.sp,
            )
        }
    }
}
