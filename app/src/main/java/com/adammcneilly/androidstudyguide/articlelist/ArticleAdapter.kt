package com.adammcneilly.androidstudyguide.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.databinding.ListItemArticleBinding
import com.adammcneilly.androidstudyguide.models.Article

class ArticleAdapter(
    private val clickListener: ArticleClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bindArticle(article)
    }

    class ArticleViewHolder(
        private val binding: ListItemArticleBinding,
        private val clickListener: ArticleClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var article: Article? = null

        init {
            binding.root.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            this.article = article
            binding.articleTitle.text = article.htmlTitle.getSpanned()
            binding.articleAuthor.text = itemView.context.getString(
                R.string.by_author,
                article.authorName
            )
        }

        override fun onClick(v: View?) {
            article?.let(clickListener::onArticleClicked)
        }
    }
}
