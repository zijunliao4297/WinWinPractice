package idv.john.winwinmediapractice.retrofits

import idv.john.winwinmediapractice.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository {

    fun getPosts(): Single<List<PostEntity>> {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java).getPosts()
    }
}