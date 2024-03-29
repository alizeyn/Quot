package `is`.quot.data.remote

import `is`.quot.data.model.QuoteApiModel
import retrofit2.http.GET

interface QuoteApi {

    @GET("v1/quote")
    suspend fun getQuote(): QuoteApiModel
}