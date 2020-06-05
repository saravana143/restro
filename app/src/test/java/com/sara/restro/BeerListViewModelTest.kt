package com.sara.restro


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sara.restro.model.Beer
import com.sara.restro.viewmodel.BeerListViewModel
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class BeerListViewModelTest {

    @Mock
    var beerPagedList: LiveData<PagedList<Beer>>? = null

    @Mock
    lateinit var beerListViewModel : BeerListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.beerListViewModel = createViewModel()
    }

    @Test
    fun `given viewModel emits value when start view model then display that value`() {
        //given
        assertNull(this.beerListViewModel.beerPagedList?.value)

        //when

        //then
    }

    @Test
    fun `given viewModel emits error when start view model then show error`() {

    }

    @After
    fun tearDown() {
        //clean up test resource
    }
    private fun createViewModel(
    ) = BeerListViewModel()

    companion object {
        const val ID = "id"
        val beerList = Beer(
            id = ID,
            name = "beerName"
        )
    }
}