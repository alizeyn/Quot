package `is`.quot.domain.repository

import `is`.quot.data.model.toQuote
import `is`.quot.data.remote.QuoteApi
import `is`.quot.domain.model.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi,
    private val ioDispatcher: CoroutineDispatcher,
) : QuoteRepository {


    override suspend fun getQuote(): Quote = withContext(ioDispatcher) {
        quoteApi.getQuote().toQuote()
    }
}
