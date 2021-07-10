package app.storytel.candidate.com.features.details

import androidx.lifecycle.lifecycleScope
import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.utils.base.BasePresenterImpl
import app.storytel.candidate.com.utils.networking.ApiService
import app.storytel.candidate.com.utils.networking.NetworkUtils.future
import app.storytel.candidate.com.utils.networking.NetworkUtils.onError
import app.storytel.candidate.com.utils.networking.NetworkUtils.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
        private val apiService: ApiService
) : BasePresenterImpl<DetailsContract.View>(),DetailsContract.Presenter {

    override fun getComments(id: Int) {
        getView()?.lifecycleScope?.launch {
            future { apiService.getComments("$id") }
                    .onSuccess { onData(it) }
                    .onError { onError(it) }

        }
    }

    private fun onData(comments:List<Comment>) {
        if (comments.size > 3)
            getView()?.onCommentsLoaded(comments.take(3))
        else getView()?.onCommentsLoaded(comments)
    }

    private fun onError(e:Exception){
        getView()?.onError(e)
    }


}