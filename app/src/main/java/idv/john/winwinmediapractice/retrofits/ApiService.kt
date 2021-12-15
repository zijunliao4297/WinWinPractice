package idv.john.winwinmediapractice.retrofits

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("android.json")
    fun getPosts(): Single<List<PostEntity>>
}