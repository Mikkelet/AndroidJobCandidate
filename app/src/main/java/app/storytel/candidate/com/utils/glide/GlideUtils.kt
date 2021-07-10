package app.storytel.candidate.com.utils.glide

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideUtils {
    fun glideUrl(url:String):GlideUrl = GlideUrl(url, LazyHeaders.Builder()
            .addHeader("User-Agent", "android")
            .build())
}