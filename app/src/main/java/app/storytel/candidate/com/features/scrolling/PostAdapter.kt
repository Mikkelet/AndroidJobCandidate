package app.storytel.candidate.com.features.scrolling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.databinding.PostItemBinding
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.models.PostAndImages
import app.storytel.candidate.com.utils.glide.GlideUtils.glideUrl
import com.bumptech.glide.RequestManager
import java.util.*

class PostAdapter(private val requestManager: RequestManager, private val callback: Callback) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface Callback {
        fun onBodyClick(post: Post,photo: Photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binder = PostItemBinding.inflate(inflater)
        return PostViewHolder(binder)
    }

    private var mData: PostAndImages = PostAndImages(emptyList(), emptyList())

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = mData.mPosts[position]
        val photoSize = mData.mPhotos.size
        val index = Random().nextInt(photoSize)
        val photo = mData.mPhotos[index]
        holder.bind(post, photo)
    }

    fun setData(data: PostAndImages) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mData.mPosts.size


    inner class PostViewHolder(val view: PostItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(post:Post, photo: Photo) {
            view.apply {
                title.text = post.title
                body.text = post.body
                body.setOnClickListener { callback.onBodyClick(post,photo) }
                requestManager.load(glideUrl(photo.thumbnailUrl)).into(image)
            }
        }
    }


}

