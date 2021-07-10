package app.storytel.candidate.com.utils.networking

import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/photos")
    suspend fun getPhotos():List<Photo>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") id:String):List<Comment>

}