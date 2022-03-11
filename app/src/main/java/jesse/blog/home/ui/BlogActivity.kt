package jesse.blog.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import jesse.blog.app.util.StatusBarHelper
import jesse.blog.databinding.ActivityBlogBinding
import jesse.blog.home.Injection
import jesse.blog.home.model.BlogItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarHelper.setStatusBarLightMode(this)
        val binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this, this)
        )[BlogViewModel::class.java]

        binding.bindState(viewModel.pagingDataFlow)
    }

    private fun ActivityBlogBinding.bindState(pagingData: Flow<PagingData<BlogItem>>) {
        val adapter = BlogAdapter()
        rvBlogs.adapter = adapter
        bindList(adapter, pagingData)
    }

    private fun ActivityBlogBinding.bindList(
        adapter: BlogAdapter,
        pagingData: Flow<PagingData<BlogItem>>
    ) {
        retryButton.setOnClickListener { adapter.retry() }
        lifecycleScope.launch {
            pagingData.collectLatest(adapter::submitData)
        }
        lifecycleScope.launch {
            adapter.addLoadStateListener { loadState ->

                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

                emptyList.isVisible = isListEmpty
                rvBlogs.isVisible =
                    loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                retryButton.isVisible =
                    loadState.mediator?.refresh is LoadState.Error && adapter.itemCount == 0
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                Log.d(
                    "BlogActivity",
                    "emptyList.isVisible=$isListEmpty, rvBlogs.isVisible=${rvBlogs.isVisible}" +
                            ", progressBar.isVisible=${progressBar.isVisible}" +
                            ", retryButton.isVisible=${retryButton.isVisible}" +
                            ", errorState=${errorState}"
                )

                errorState?.let {
                    Toast.makeText(
                        this@BlogActivity,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}