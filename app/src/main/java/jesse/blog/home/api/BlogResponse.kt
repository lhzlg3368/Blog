package jesse.blog.home.api

import com.google.gson.annotations.SerializedName
import jesse.blog.home.model.BlogItem

data class BlogResponse(
    @SerializedName("frontmatter")
    val frontmatter: Frontmatter
)

data class Frontmatter(
    @SerializedName("banner")
    val banner: Banner,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("date")
    val date: String,
    @SerializedName("draft")
    val draft: Any?,
    @SerializedName("language")
    val language: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("title")
    val title: String
)

data class Banner(
    @SerializedName("childImageSharp")
    val childImageSharp: ChildImageSharp
)

data class ChildImageSharp(
    @SerializedName("fixed")
    val fixed: Fixed
)

data class Fixed(
    @SerializedName("src")
    val src: String
)

fun BlogResponse.convertToBlogItem(reqUrl: String): BlogItem {
    return BlogItem(
        hashCode(),
        frontmatter.title,
        frontmatter.banner.childImageSharp.fixed.src,
        frontmatter.date,
        frontmatter.path,
        frontmatter.categories,
        frontmatter.tags,
        frontmatter.language,
        reqUrl
    )
}