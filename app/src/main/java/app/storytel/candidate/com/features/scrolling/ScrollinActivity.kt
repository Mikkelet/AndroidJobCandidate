package app.storytel.candidate.com.features.scrolling

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.storytel.candidate.com.utils.view.ErrorDialog
import app.storytel.candidate.com.databinding.ActivityScrollingBinding
import app.storytel.candidate.com.features.details.DetailsActivity
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.models.PostAndImages
import app.storytel.candidate.com.utils.base.BaseActivity
import app.storytel.candidate.com.utils.view.ViewUtils.gone
import app.storytel.candidate.com.utils.view.ViewUtils.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class ScrollinActivity : BaseActivity<ScrollingContract.View, ActivityScrollingBinding>(), ScrollingContract.View, PostAdapter.Callback, ErrorDialog.Callback {

    private lateinit var mPostAdapter: PostAdapter

    override lateinit var binder:ActivityScrollingBinding

    @Inject
    override lateinit var presenter: ScrollingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivityScrollingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setupView()
        presenter.getData()
    }

    private fun setupView(){

        setSupportActionBar(binder.toolbar)
        mPostAdapter = PostAdapter(Glide.with(this), this)

        binder.content.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ScrollinActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mPostAdapter
        }
    }

    override fun onBodyClick(post: Post, photo: Photo) {
        Timber.d("qqq on post clicked: $post, $photo")
        startActivity(DetailsActivity.createIntent(this,post,photo))
    }

    override fun onDataLoaded(data: PostAndImages) {
        mPostAdapter.setData(data)
        binder.content.recyclerView.visible()
        binder.progressBar.gone()
    }

    override fun onError(e: Exception) {
        val dialog = ErrorDialog(e,this)
        binder.progressBar.gone()
        dialog.show(supportFragmentManager,this::class.java.toString())
    }

    override fun onRetryClicked() {
        binder.progressBar.visible()
        presenter.getData()
    }


}