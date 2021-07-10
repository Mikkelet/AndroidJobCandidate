package app.storytel.candidate.com.utils.base

import androidx.lifecycle.LifecycleOwner


interface BasePresenter<in V : LifecycleOwner> {
    fun takeView(view: V)

    fun dropView()
}