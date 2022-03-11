package jesse.blog.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import jesse.blog.app.converter.ListConverter

@Entity(tableName = "blog_items")
@TypeConverters(ListConverter::class)
data class BlogItem(
    @PrimaryKey
    var hash: Int,
    var title: String,
    val bannerSrc: String,
    val date: String,
    val path: String,
    val categories: List<String>,
    val tags: List<String>,
    val language: String,
    val reqUrl: String
)
