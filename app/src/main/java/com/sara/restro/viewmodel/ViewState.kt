package com.sara.restro.viewmodel

import androidx.lifecycle.LiveData
import com.sara.restro.model.Beer

data class ViewState(val productList: LiveData<List<Beer>>)



