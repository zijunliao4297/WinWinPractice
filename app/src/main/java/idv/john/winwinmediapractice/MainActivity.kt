package idv.john.winwinmediapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import idv.john.winwinmediapractice.databinding.ActivityMainBinding
import idv.john.winwinmediapractice.retrofits.PostRepository
import idv.john.winwinmediapractice.ui.PostsRecyclerAdapter
import idv.john.winwinmediapractice.ui.PostsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private val mPostsRecyclerAdapter = PostsRecyclerAdapter()
    private val mPostsViewModel by viewModel<PostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        mActivityMainBinding.postsRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).apply {
                setDrawable(AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)!!) // TODO: Ignore null check
            }
            )
            adapter = mPostsRecyclerAdapter
        }
        setContentView(mActivityMainBinding.root)
        mPostsViewModel.posts.observe(this) {
            mPostsRecyclerAdapter.setPostEntities(it)
        }
    }
}