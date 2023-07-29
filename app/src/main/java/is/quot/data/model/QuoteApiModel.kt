package `is`.quot.data.model

import `is`.quot.Quote

data class QuoteApiModel(
    val text: String,
    val author: String,
    val imageUrl: String?,
    val categories: List<CategoryApiModel>
)

fun QuoteApiModel.toQuote() = Quote(
    text = text,
    author = author,
    imageUrl = imageUrl,
    categories = categories.map { it.toCategory() },
)