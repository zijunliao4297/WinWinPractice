package idv.john.winwinmediapractice.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import idv.john.winwinmediapractice.Constants
import idv.john.winwinmediapractice.Constants.TAG
import idv.john.winwinmediapractice.retrofits.PostEntity
import idv.john.winwinmediapractice.retrofits.PostRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PostsViewModel(repository: PostRepository, application: Application) : AndroidViewModel(application) {
    val posts = MutableLiveData<List<PostEntity>>()

    init {
        repository
            .getPosts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    posts.postValue(list)
                },
                {
                    Log.d(TAG, it.toString())
                }
            )
    }
}