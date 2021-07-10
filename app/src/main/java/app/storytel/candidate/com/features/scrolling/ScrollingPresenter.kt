package app.storytel.candidate.com.features.scrolling

import androidx.lifecycle.lifecycleScope
import app.storytel.candidate.com.features.details.DetailsContract
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.PostAndImages
import app.storytel.candidate.com.utils.base.BasePresenter
import app.storytel.candidate.com.utils.base.BasePresenterImpl
import app.storytel.candidate.com.utils.networking.ApiService
import app.storytel.candidate.com.utils.networking.NetworkUtils
import app.storytel.candidate.com.utils.networking.NetworkUtils.future
import app.storytel.candidate.com.utils.networking.NetworkUtils.onError
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ScrollingPresenter @Inject constructor(
        private val apiService: ApiService
) : BasePresenterImpl<ScrollingContract.View>(), ScrollingContract.Presenter {


    override fun getData() {
        getView()?.lifecycleScope?.launch {
            val postAndImages = future {
                val posts = apiService.getPosts()
                val photos = apiService.getPhotos()
                PostAndImages(posts,photos)
            }.onError { onError(it) }.data ?: return@launch
            getView()?.onDataLoaded(postAndImages)
        }
    }

    private fun onError(e:Exception){
        getView()?.onError(e)
    }
}