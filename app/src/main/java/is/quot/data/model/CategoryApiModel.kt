package `is`.quot.data.model

import `is`.quot.Category

data class CategoryApiModel(
    val name: String,
)

fun CategoryApiModel.toCategory() = Category(
    name = this.name
)