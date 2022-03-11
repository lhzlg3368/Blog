package jesse.blog.app.kt

import jesse.blog.BuildConfig

/**
 * https://arcblockio.cn + 'String'
 */
fun String.toArcUrl(): String {
    if (!this.startsWith("/")) {
        throw IllegalAccessException("'$this' start must be '/'. ")
    }
    return "${BuildConfig.API_SERVER_URL}$this"
}