package idv.john.winwinmediapractice.retrofits

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import idv.john.winwinmediapractice.Constants
import idv.john.winwinmediapractice.Constants.TAG
import idv.john.winwinmediapractice.ui.ColorTagSpan

data class PostEntity(
    val title: String,
    val content: String,
    val favoriteCount: Int,
    val likeCount: Int,
    val commentCount: Int,
    val unlockCount: Int,
    val type: Int,
    val imageUrls: List<String>,
    val tags: List<String>,
    val user: User,
    val creationDate: String
) {

    fun getSpannedTags(): SpannableStringBuilder {
        var start = 0
        var end = 0
        val ssb = SpannableStringBuilder()
        for (i in tags.indices) {
            try {
                end = start + tags[i].length
                ssb.append(tags[i])
                ssb.setSpan(ColorTagSpan(Constants.TAG_BACKGROUND_COLORS[i], Constants.TAG_COLORS[i]), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                ssb.append(" ")
                start = end + 1
            } catch (e: IndexOutOfBoundsException) {
                Log.d(TAG, e.toString())
            }
        }
        ssb.substring(0, ssb.length - 1)
        return ssb
    }
}

data class User(
    val id: Int,
    val nickName: String,
    val imageUrl: String
)
