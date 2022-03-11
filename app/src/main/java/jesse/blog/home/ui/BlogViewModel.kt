package jesse.blog.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import jesse.blog.home.data.BlogRepository
import jesse.blog.home.model.BlogItem
import kotlinx.coroutines.flow.*

class BlogViewModel(
    private val repository: BlogRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<BlogItem>>

    init {
        val reqUrl: String = savedStateHandle[LAST_REQ_URL] ?: DEFAULT_REQ_URL
        val actionStateFlow = MutableSharedFlow<UiAction>()
        pagingDataFlow = actionStateFlow
            .filterIsInstance<UiAction.Fetch>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Fetch(reqUrl)) }
            .flatMapLatest { fetchPosts(it) }
            .cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        savedStateHandle[LAST_REQ_URL] = DEFAULT_REQ_URL
    }

    private fun fetchPosts(action: UiAction.Fetch): Flow<PagingData<BlogItem>> {
        return repository.getPosts(action.reqUrl)
    }

}

sealed class UiAction {
    data class Fetch(val reqUrl: String) : UiAction()
}

private const val LAST_REQ_URL = "last_req_url"
private const val DEFAULT_REQ_URL = "posts.json"