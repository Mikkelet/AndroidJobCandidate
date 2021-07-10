package app.storytel.candidate.com.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.databinding.CommentItemBinding
import app.storytel.candidate.com.models.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>()  {

    private var comments:List<Comment> = emptyList()

    fun setData(list: List<Comment>){
        comments = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view:CommentItemBinding) : RecyclerView.ViewHolder(view.root){
        fun bind(comment: Comment){
            view.apply {
                title.text = comment.name
                description.text = comment.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CommentItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size
}