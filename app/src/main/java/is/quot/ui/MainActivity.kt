package `is`.quot.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import `is`.quot.domain.model.Quote
import `is`.quot.ui.intro.IntroView
import `is`.quot.ui.quote.QuoteView
import `is`.quot.ui.theme.QuotTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuotTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {

                    val systemUiController: SystemUiController = rememberSystemUiController()
                    systemUiController.apply {
                        isStatusBarVisible = false
                        isNavigationBarVisible = false
                        isSystemBarsVisible = false
                    }

                    MainView(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainView(viewModel: QuoteViewModel) {
    val quoteStatus: QuoteState by viewModel.quoteState.collectAsState()

    Crossfade(targetState = quoteStatus, label = "") { currentStatus ->
        when (currentStatus) {
            is QuoteState.Initial -> {
                IntroView(viewModel = viewModel)
            }
            is QuoteState.Loading -> {
                // Show loading state
            }
            is QuoteState.Success -> {
                val quote = currentStatus.quote
                QuoteView(quote = quote)
            }
            is QuoteState.Error -> {
                // Show error state
            }
        }
    }
}

@Composable
fun Quote(quote: Quote, modifier: Modifier = Modifier) {
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


@Preview(showBackground = true, device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun QuotePreview() {
    val quote = Quote(
        text = "No matter what happens now\n" +
            "You shouldn't be afraid\n" +
            "Because I know today has been\n" +
            "The most perfect day I've ever seen",
        author = "Radiohead",
        imageUrl = "https://assets-global.website-files.com/62905d4927c20a36731fd606/629b61f932efec505e3e9267_Thom-Yorke.png",
        categories = emptyList(),
    )
    QuotTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Quote(quote)
        }
    }
}