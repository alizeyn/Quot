package `is`.quot.ui.quote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import `is`.quot.R
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
            if (isPreviewMode()) {
                // Display a placeholder image when in preview mode
                QuoteImage(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.ic_placeholer_author)
                )
            } else {
                val url = quote.imageUrl
                if (url != null) {
                    GlideImage(
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                            .clip(CircleShape),
                        imageModel = url,
                        contentScale = ContentScale.Crop,
                        circularReveal = CircularReveal(duration = 250),
                    )
                } else quote.fallbackImage?.let { painter ->
                    QuoteImage(
                        modifier = modifier,
                        painter = painter
                    )
                }
            }

            Text(
                text = quote.text,
                modifier = modifier,
                color = Color.White,
                lineHeight = 30.sp,
                fontSize = 36.sp
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

@Composable
fun QuoteImage(modifier: Modifier = Modifier, painter: Painter) {

    val colorMatrix = ColorMatrix().apply {
        setToSaturation(0f)
    }

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(colorMatrix)
    )
}

@Composable
fun isPreviewMode(): Boolean = LocalInspectionMode.current

@Preview
@Composable
fun PreviewQuoteView() {
    val quote = Quote(
        text = "No matter what happens now\n" +
            "You shouldn't be afraid\n" +
            "Because I know today has been\n" +
            "The most perfect day I've ever seen",
        author = "Radiohead",
        imageUrl = "https://assets-global.website-files.com/62905d4927c20a36731fd606/629b61f932efec505e3e9267_Thom-Yorke.png",
        categories = emptyList(),
    )
    QuoteView(quote = quote)
}

