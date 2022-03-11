package jesse.blog.home.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import jesse.blog.home.api.BlogService
import jesse.blog.home.api.convertToBlogItem
import jesse.blog.home.db.BlogItemDatabase
import jesse.blog.home.model.BlogItem

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator(
    private val path: String,
    private val service: BlogService,
    private val database: BlogItemDatabase
) : RemoteMediator<Int, BlogItem>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BlogItem>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                "posts.json"
            }
            LoadType.PREPEND -> {
                "posts.json"
            }
            LoadType.APPEND -> {
                "posts.json1"
            }
        }
        val apiReqUrl = page
        return try {
            val apiResponse = service.getPosts(apiReqUrl)
            val blogItems = apiResponse?.map { matter -> matter.convertToBlogItem(path) }
            blogItems?.let { list ->
                database.withTransaction {
                    Log.d("BlogRemoteMediator", "len=${list.size}")
                    database.blogItemsDao().insertAll(list)
                }
            }
            val endOfPaginationReached = endOfPaginationReached(apiResponse)
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun endOfPaginationReached(apiReqUrl: Any?): Boolean {
        return true
    }
}