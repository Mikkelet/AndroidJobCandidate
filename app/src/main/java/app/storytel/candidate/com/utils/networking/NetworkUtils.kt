package app.storytel.candidate.com.utils.networking

import timber.log.Timber
import java.lang.Exception

object NetworkUtils {
    inline fun <T> future(callback: () -> T): FutureResult<T> {
        return try {
            val result = callback()
            Timber.d("qqq result=$result")
            FutureResult(result, null)
        } catch (e: Exception) {
            Timber.e(e)
            FutureResult(null, e)
        }
    }

    fun <T> FutureResult<T>.onSuccess(callback:(data:T)->Unit):FutureResult<T>{
        if(data != null) callback(data)
        return this
    }
    fun <T> FutureResult<T>.onError(callback:(error:Exception)->Unit):FutureResult<T>{
        if(error != null) callback(error)
        return this
    }

}

data class FutureResult<T>(
    val data:T?,
    val error: Exception?)