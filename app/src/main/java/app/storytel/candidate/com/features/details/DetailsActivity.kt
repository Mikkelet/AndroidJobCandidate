package app.storytel.candidate.com.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.storytel.candidate.com.utils.view.ErrorDialog
import app.storytel.candidate.com.databinding.ActivityDetailsBinding
import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.utils.base.BaseActivity
import app.storytel.candidate.com.utils.glide.GlideUtils.glideUrl
import app.storytel.candidate.com.utils.view.ViewUtils.gone
import app.storytel.candidate.com.utils.view.ViewUtils.visible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : BaseActivity<DetailsContract.View,ActivityDetailsBinding>(), DetailsContract.View, ErrorDialog.Callback {

    override lateinit var binder:ActivityDetailsBinding
    private lateinit var post:Post
    private lateinit var photo:Photo
    private val commentsAdapter = CommentAdapter()

    @Inject
    override lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        post = Gson().fromJson(intent?.extras?.getString(EXTRA_POST),Post::class.java)
        photo = Gson().fromJson(intent?.extras?.getString(EXTRA_PHOTO),Photo::class.java)
        Timber.d("qqq intent extras post=$post")
        setupView()
        presenter.getComments(post.id)
    }

    private fun setupView(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binder.apply {
            setSupportActionBar(toolbar)
            collapsingToolbar.title = post.title
            details.text = post.body
            Glide.with(this@DetailsActivity).load(glideUrl(photo.url)).into(backdrop)
            commentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@DetailsActivity,LinearLayoutManager.VERTICAL,false)
                adapter = commentsAdapter
            }
        }
    }



    override fun onCommentsLoaded(comment: List<Comment>) {
        commentsAdapter.setData(comment)
        binder.spinner.gone()
        binder.commentsRecyclerView.visible()
    }

    override fun onError(e: Exception) {
        val dialog = ErrorDialog(e,this)
        binder.spinner.gone()
        dialog.show(supportFragmentManager,this::class.java.toString())
    }

    override fun onRetryClicked() {
        presenter.getComments(post.id)
        binder.spinner.visible()
    }

    companion object{
        private const val EXTRA_POST = "extra_post"
        private const val EXTRA_PHOTO = "extra_photo"
        fun createIntent(context: Context,post: Post,photo: Photo):Intent{
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra(EXTRA_POST,Gson().toJson(post))
            intent.putExtra(EXTRA_PHOTO,Gson().toJson(photo))
            return intent
        }
    }
}
