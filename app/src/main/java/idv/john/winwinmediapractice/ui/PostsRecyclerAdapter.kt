package idv.john.winwinmediapractice.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idv.john.winwinmediapractice.Constants.TAG
import idv.john.winwinmediapractice.databinding.LayoutPostItemType1Binding
import idv.john.winwinmediapractice.databinding.LayoutPostItemType2Binding
import idv.john.winwinmediapractice.retrofits.PostEntity
import kotlin.math.min

class PostsRecyclerAdapter : RecyclerView.Adapter<PostsRecyclerAdapter.PostsViewHolder>() {
    private var mPostEntities = ArrayList<PostEntity>()

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var mBinding1: LayoutPostItemType1Binding
        private lateinit var mBinding2: LayoutPostItemType2Binding

        constructor(binding1: LayoutPostItemType1Binding) : this(binding1.root) {
            this.mBinding1 = binding1
        }

        constructor(binding: LayoutPostItemType2Binding) : this(binding.root) {
            mBinding2 = binding
        }

        fun bind(entity: PostEntity?) {
            entity?.apply {
                when (type) {
                    1 -> {
                        mBinding1.entity = entity
                        mBinding1.executePendingBindings()
                        if (user.imageUrl != "") Glide.with(mBinding1.root).load(user.imageUrl).into(mBinding1.postUserImage)
                        if (imageUrls[0] != "") Glide.with(mBinding1.root).load(imageUrls[0]).into(mBinding1.postImage)
                    }
                    2 -> {
                        mBinding2.entity = entity
                        mBinding2.executePendingBindings()
                        if (user.imageUrl != "") Glide.with(mBinding2.root).load(user.imageUrl)
                            .into(mBinding2.postUserImage)
                        val views = arrayOf(
                            mBinding2.postImage1,
                            mBinding2.postImage2,
                            mBinding2.postImage3
                        )
                        val size = min(imageUrls.size, views.size)
                        for (i in 0 until size)
                            if (imageUrls[i] != "") Glide.with(mBinding2.root).load(imageUrls[i]).into(views[i])
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        when (viewType) {
            1 -> PostsViewHolder(LayoutPostItemType1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
            // TODO: Use '2' to inflate ViewBinding and 'else' to handle error
            else -> PostsViewHolder(LayoutPostItemType2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(mPostEntities[position])
    }

    override fun getItemViewType(position: Int): Int {
        return mPostEntities[position].type
    }

    override fun getItemCount(): Int = mPostEntities.size

    fun setPostEntities(entities: List<PostEntity>) {
        mPostEntities.clear()
        mPostEntities = ArrayList(entities)
        notifyDataSetChanged()
    }
}