package jesse.blog.home

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import jesse.blog.home.api.BlogService
import jesse.blog.home.data.BlogRepository
import jesse.blog.home.db.BlogItemDatabase
import jesse.blog.home.ui.ViewModelFactory

object Injection {

    private fun provideBlogRepository(context: Context): BlogRepository {
        return BlogRepository(BlogService.create(), BlogItemDatabase.getInstance(context))
    }

    fun provideViewModelFactory(
        context: Context,
        owner: SavedStateRegistryOwner
    ): ViewModelFactory {
        return ViewModelFactory(owner, provideBlogRepository(context))
    }

}