package `is`.quot.domain.repository

import `is`.quot.domain.model.Quote

interface QuoteRepository {

    suspend fun getQuote(): Quote
}
