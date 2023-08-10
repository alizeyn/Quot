package `is`.quot.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import `is`.quot.domain.model.Quote

@JsonClass(generateAdapter = true)
data class QuoteApiModel(
    @Json(name = "quote")
    val text: String,

    @Json(name = "author")
    val author: String,

    @Json(name = "imageUrl")
    val imageUrl: String?,

    // TODO fix on the backend side
    // @Json(name = "categories")
    // val categories: List<CategoryApiModel>
)


fun QuoteApiModel.toQuote() = Quote(
    text = text,
    author = author,
    imageUrl = imageUrl,
//    categories = categories.map { it.toCategory() },
    categories = emptyList()
)