package app.storytel.candidate.com.utils.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.FragmentErrorBinding

class ErrorDialog(private val error: Exception, private val callback: Callback) : DialogFragment(R.layout.fragment_error) {
    interface Callback{
        fun onRetryClicked()
    }
    private lateinit var binder:FragmentErrorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binder = FragmentErrorBinding.inflate(inflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Full screen
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)

        super.onViewCreated(view, savedInstanceState)
        binder.apply {
            errorMessage.text = error.localizedMessage
            buttonTry.setOnClickListener {
                dismissAllowingStateLoss()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        callback.onRetryClicked()
        super.onDismiss(dialog)
    }
}