package app.storytel.candidate.com.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V: LifecycleOwner, B: ViewBinding> : AppCompatActivity() {

    abstract var binder: B
    abstract val presenter:BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        presenter.takeView(this as V)

    }
}