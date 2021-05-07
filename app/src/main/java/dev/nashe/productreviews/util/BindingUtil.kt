package dev.nashe.productreviews.util

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingUtil {

    private fun setImageFromUrl(imageView: ImageView?, url: String) {
        if (imageView != null) {
            Glide.with(imageView).load(url).into(imageView)
        }
    }

    @BindingAdapter("src", "placeholder")
    fun setImageFromUrl(imageView: ImageView?, url: String, drawable: Drawable?) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        if (drawable == null) {
            setImageFromUrl(imageView, url)
            return
        }
        val options = RequestOptions().placeholder(drawable)
            .error(drawable)
            .centerInside()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

        if (imageView != null) {
            if (url.endsWith(".gif")) {
                Glide.with(imageView).asGif().load(url).apply(options).into(imageView)
            } else {
                Glide.with(imageView).load(url).apply(options).into(imageView)
            }
        }
    }
}
