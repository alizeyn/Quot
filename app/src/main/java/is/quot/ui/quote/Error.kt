package `is`.quot.ui.quote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `is`.quot.R
import `is`.quot.domain.model.Quote

@Composable
fun ErrorView(modifier: Modifier = Modifier) {

    val errorQuote = Quote(
        text = "If you don't get what you want, you suffer; even when you get exactly what you want, you still suffer …",
        author = "Socrates",
        fallbackImage = painterResource(id = R.drawable.img_author_socrates),
        categories = emptyList(),
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {

        Column(
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text(
                text = "Error",
                modifier = modifier.align(Alignment.End),
                color = Color.White,
                fontSize = 64.sp
            )
            Text(
                text = "Couldn't get your wisdom dose",
                modifier = modifier,
                color = Color.LightGray,
                fontSize = 21.sp,
                textAlign = TextAlign.Start
            )
        }

        QuoteView(quote = errorQuote)
    }
}

@Composable
@Preview
fun ErrorViewPreview() {
    ErrorView()
}
