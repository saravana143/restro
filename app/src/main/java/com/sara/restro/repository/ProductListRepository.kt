package com.sara.restro.repository


import com.sara.restro.api.Api
import android.util.Log
import com.sara.restro.model.Beer

class ProductListRepository: Repository {

    companion object {
        const val PAGE_SIZE = 24
        private const val PAGE = 1
    }

    private var jsonApi: Api = Service.createService(Api::class.java)

    override suspend fun loadProducts(): List<Beer> {

        try {
            val response = jsonApi.getBeersList(PAGE, PAGE_SIZE)
            when {
                response.isSuccessful -> {
                    return response.body() ?: emptyList()
                }
            }
        } catch (exception: Exception) {
            Log.e("repository->Beerlist", "2" + exception.message)
        }

        return emptyList()
    }

}