package com.sara.restro.api
import com.sara.restro.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/v2/beers")
    suspend fun getBeersList(
        @Query("page") currentPage: Int,
        @Query("per_page") pageSize: Int
    ): Response<List<Beer>>
}