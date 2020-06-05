package com.sara.restro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.restro.model.Beer
import com.sara.restro.repository.ProductListRepository
import com.sara.restro.repository.Repository
import kotlinx.coroutines.launch

class ProductListViewModel() : ViewModel() {

    var beerList: MutableLiveData<List<Beer>> = MutableLiveData()
    private val repository : Repository = ProductListRepository()

    init {
        loadProducts()
    }

    fun getBeerList(): LiveData<List<Beer>> {
        return beerList
    }

    private fun loadProducts() {
        viewModelScope.launch {
                beerList.value = repository.loadProducts()
        }
    }
}