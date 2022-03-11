package jesse.blog.home.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jesse.blog.R
import jesse.blog.app.kt.toArcUrl
import jesse.blog.home.model.BlogItem

class BlogItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val sTag = "BlogItemViewHolder"

    private val image: ImageView = view.findViewById(R.id.blog_image)
    private val title: TextView = view.findViewById(R.id.blog_title)
    private val date: TextView = view.findViewById(R.id.blog_date)
    private val next: ImageView = view.findViewById(R.id.blog_next)

    init {
        view.setOnClickListener {
            mBlogItem?.let {
                val url = it.path.toArcUrl()
                Log.d(sTag, "web go $url")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    private var mBlogItem: BlogItem? = null

    fun bind(blogItem: BlogItem?) {
        mBlogItem = blogItem
        blogItem?.let {
            Glide.with(image)
                .load(it.bannerSrc.toArcUrl())
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .into(image)

            title.text = it.title
            date.text = String.format("%s\n%s", it.date, it.tags)

            Glide.with(next)
                .load(R.mipmap.next)
                .into(next)
        }
    }

    companion object {
        fun create(parent: ViewGroup): BlogItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.blog_view_item, parent, false)
            return BlogItemViewHolder(view)
        }
    }
}