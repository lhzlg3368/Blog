package jesse.blog.home.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import jesse.blog.home.model.BlogItem

class BlogAdapter : PagingDataAdapter<BlogItem, BlogItemViewHolder>(BLOG_ITEM_COMPARATOR) {

    override fun onBindViewHolder(holder: BlogItemViewHolder, position: Int) {
        val blogItem = getItem(position)
        holder.bind(blogItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogItemViewHolder {
        return BlogItemViewHolder.create(parent)
    }

    companion object {
        private val BLOG_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<BlogItem>() {
            override fun areItemsTheSame(oldItem: BlogItem, newItem: BlogItem): Boolean {
                return oldItem.hash == newItem.hash
            }

            override fun areContentsTheSame(oldItem: BlogItem, newItem: BlogItem): Boolean =
                oldItem == newItem
        }
    }
}