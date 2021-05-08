package dev.nashe.productreviews.util

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.nashe.productreviews.R
import java.text.NumberFormat
import java.util.*

class BindingUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageFromUrl(imageView: ImageView?, url: String?) {
            if (TextUtils.isEmpty(url)) {
                return
            }

            val options = RequestOptions()
                .error(R.drawable.ic_search)
                .centerInside()
                .placeholder(R.drawable.ic_search)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

            if (imageView != null) {
                if (url?.endsWith(".gif") == true) {
                    Glide.with(imageView).asGif().load(url).apply(options).into(imageView)
                } else {
                    Glide.with(imageView).load(url).apply(options).into(imageView)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("textCap")
        fun setTextToSentenceCase(textView: TextView, text : String?) {
            textView.text = text?.capitalize(Locale.ROOT)
        }

        @JvmStatic
        @BindingAdapter("price")
        fun formatPrice(textView: TextView, text: Double?) {
            val formatter = NumberFormat.getCurrencyInstance(Locale.US)
            textView.text = formatter.format(text)
        }

    }

}
