package app.storytel.candidate.com.features.scrolling

import androidx.lifecycle.LifecycleOwner
import app.storytel.candidate.com.models.PostAndImages
import app.storytel.candidate.com.utils.base.BasePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.lang.Exception

object ScrollingContract {

    interface View: LifecycleOwner {
        fun onDataLoaded(data:PostAndImages)
        fun onError(e: Exception)

    }
    interface Presenter:BasePresenter<View>{
        fun getData()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class ScrollingModule{

        @Binds
        abstract fun  providePresenter(presenter: ScrollingPresenter): Presenter
    }
}