package `is`.quot.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.quot.domain.model.Quote
import `is`.quot.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


sealed interface QuoteState {

    object Initial : QuoteState
    data class Success(val quote: Quote) : QuoteState
    object Loading : QuoteState
    data class Error(val message: String) : QuoteState
}

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
) : ViewModel() {

    private val _quoteState = MutableStateFlow<QuoteState>(QuoteState.Initial)
    val quoteState: StateFlow<QuoteState> get() = _quoteState

    val isLoading
        get() = _quoteState.value is QuoteState.Loading

    fun getQuote() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _quoteState.value = QuoteState.Loading
            val quote = quoteRepository.getQuote()
            delay(2.seconds)
            _quoteState.value = QuoteState.Success(quote)
        } catch (e: Exception) {
            e.printStackTrace()
            // Before switching to Error state, delay for 2 seconds while in Loading state
            delay(2.seconds)
            _quoteState.value = QuoteState.Error(e.message.toString())
        }

    }

}