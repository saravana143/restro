package com.sara.restro.repository

import BeerListDataSource
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.sara.restro.model.Beer
import kotlinx.coroutines.CoroutineScope

class BeerListDataSourceFactory(private val scope: CoroutineScope) :
    DataSource.Factory<Int, Beer>() {

    private val postLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, Beer>> =
        MutableLiveData()

    override fun create(): DataSource<Int, Beer> {
        val postDataSource = BeerListDataSource(scope)
        postLiveDataSource.postValue(postDataSource)
        return postDataSource
    }

    fun getPostLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Beer>> {
        return postLiveDataSource
    }
}