package `is`.quot.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.quot.domain.model.Quote
import `is`.quot.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface QuoteState {

    object Idle : QuoteState
    data class Success(val quote: Quote) : QuoteState

    object Loading : QuoteState

    data class Error(val message: String) : QuoteState
}

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
) : ViewModel() {

    private val _quoteState = MutableStateFlow<QuoteState>(QuoteState.Idle)
    val quoteState: StateFlow<QuoteState> get() = _quoteState

    init {
        getQuote()
    }

    private fun getQuote() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _quoteState.value = QuoteState.Loading
            val quote = quoteRepository.getQuote()
            _quoteState.value = QuoteState.Success(quote)
        } catch (e: Exception) {
            e.printStackTrace()
            _quoteState.value = QuoteState.Error(e.message.toString())
        }

    }

}