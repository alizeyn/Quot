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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.quot.is/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val apiKeyInterceptor = Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization","Bearer ${BuildConfig.API_KEY}")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
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
