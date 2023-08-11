package `is`.quot.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class Quote(
    val text: String,
    val author: String,
    val imageUrl: String? = null,
    val fallbackImage: Painter? = null,
    val categories: List<Category>
)

data class Category(
    val name: String,
)