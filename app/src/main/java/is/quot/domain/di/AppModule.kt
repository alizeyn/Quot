package `is`.quot.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `is`.quot.BuildConfig
import `is`.quot.data.remote.QuoteApi
import `is`.quot.domain.repository.QuoteRepository
import `is`.quot.domain.repository.QuoteRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://quote.is/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        api: QuoteApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): QuoteRepository {
        return QuoteRepositoryImpl(api, ioDispatcher)
    }
}
