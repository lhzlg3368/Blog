package jesse.blog.home.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jesse.blog.home.model.BlogItem

@Dao
interface BlogItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blogItems: List<BlogItem>)

    @Query("select * from blog_items where reqUrl = :reqUrl order by date desc")
    fun blogItemsByPath(reqUrl: String): PagingSource<Int, BlogItem>

    @Query("delete from blog_items")
    suspend fun clearBlogItems()
}