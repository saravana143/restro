package com.sara.restro.viewmodel

import BeerListDataSource.Companion.PAGE_SIZE
import com.sara.restro.repository.BeerListDataSourceFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.sara.restro.model.Beer

open class BeerListViewModel : ViewModel() {
    var beerPagedList: LiveData<PagedList<Beer>>? = null
    private var beerLiveDataSource: LiveData<PageKeyedDataSource<Int, Beer>>? = null

    init {
        val beerDataSourceFactory = BeerListDataSourceFactory(viewModelScope)
        beerLiveDataSource = beerDataSourceFactory.getPostLiveDataSource()

        val config: PagedList.Config = (PagedList.Config.Builder())
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()

        val configPop: PagedList.Config = (PagedList.Config.Builder())
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()

        beerPagedList = LivePagedListBuilder(beerDataSourceFactory, config).build()
    }
}