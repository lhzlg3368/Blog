package jesse.blog.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jesse.blog.home.api.BlogService
import jesse.blog.home.db.BlogItemDatabase
import jesse.blog.home.model.BlogItem
import kotlinx.coroutines.flow.Flow

class BlogRepository(
    private val service: BlogService,
    private val database: BlogItemDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPosts(reqUrl: String): Flow<PagingData<BlogItem>> {
        val pagingSourceFactory = { database.blogItemsDao().blogItemsByPath(reqUrl) }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = BlogRemoteMediator(reqUrl, service, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}