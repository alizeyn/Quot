package `is`.quot.domain.model

data class Quote(
    val text: String,
    val author: String,
    val imageUrl: String?,
    val categories: List<Category>
)

data class Category(
    val name: String,
)