package app.storytel.candidate.com.utils.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

abstract class BasePresenterImpl<V: LifecycleOwner> : LifecycleObserver, BasePresenter<V> {

    private var view: WeakReference<V>? = null


    fun getView():V? = view?.get()

    override fun takeView(view: V) {
        this.view = WeakReference(view)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun dropView(){
        view = null
    }

}