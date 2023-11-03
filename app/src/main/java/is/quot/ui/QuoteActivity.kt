package `is`.quot.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import `is`.quot.domain.model.Quote
import `is`.quot.ui.intro.IntroView
import `is`.quot.ui.loading.LoadingView
import `is`.quot.ui.quote.ErrorView
import `is`.quot.ui.quote.QuoteView
import `is`.quot.ui.theme.QuotTheme

@AndroidEntryPoint
class QuoteActivity : ComponentActivity() {

    private val viewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuotTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    // Make the app full screen
                    rememberSystemUiController().apply {
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
fun MainView(modifier: Modifier = Modifier, viewModel: QuoteViewModel) {
    val quoteState: QuoteState by viewModel.quoteState.collectAsStateWithLifecycle()

    Crossfade(targetState = quoteState, animationSpec = tween(durationMillis = 1000), label = "") {
        when (it) {
            is QuoteState.Initial -> IntroView(modifier = modifier) { viewModel.getQuote() }
            is QuoteState.Loading -> LoadingView(modifier = modifier)
            is QuoteState.Success -> QuoteView(modifier = modifier, quote = it.quote)
            is QuoteState.Error -> ErrorView(modifier = modifier)
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
            QuoteView(quote)
        }
    }
}