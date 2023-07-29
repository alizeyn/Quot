package `is`.quot.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import `is`.quot.domain.model.Category

@JsonClass(generateAdapter = true)
data class CategoryApiModel(
    @Json(name = "name")
    val name: String
)

fun CategoryApiModel.toCategory() = Category(
    name = this.name
)