package com.shivamjsr18.blogdisplay.services.apiService

import com.shivamjsr18.blogdisplay.model.apiModel.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("wp-json/wp/v2/posts")
    suspend fun getBlogs(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ) : ApiResponse

}