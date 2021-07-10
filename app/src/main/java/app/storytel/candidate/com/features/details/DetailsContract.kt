package app.storytel.candidate.com.features.details

import androidx.lifecycle.LifecycleOwner
import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.utils.base.BasePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.lang.Exception

object DetailsContract {
    interface View: LifecycleOwner{
        fun onCommentsLoaded(comment: List<Comment>)
        fun onError(e:Exception)
    }
    interface Presenter:BasePresenter<View> {
        fun getComments(id:Int)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DetailsModule{
        @Binds
        abstract fun provideDetailsPresenter(presenter:DetailsPresenter):Presenter
    }
}