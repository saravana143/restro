package com.sara.restro.repository

import com.sara.restro.model.Beer

interface Repository {
    suspend fun loadProducts() : List<Beer>
}