import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sara.restro.api.Api
import com.sara.restro.model.Beer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BeerListDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Beer>() {

    companion object {
        const val PAGE_SIZE = 24
        private const val PAGE = 1
    }

    private var jsonApi: Api = Service.createService(Api::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Beer>
    ) {

        scope.launch {
            try {
                val response = jsonApi.getBeersList(PAGE, PAGE_SIZE)
                when {
                    response.isSuccessful -> {
                        callback.onResult(response.body() ?: emptyList(), null, PAGE + 1)
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->Beerlist", "2" + exception.message)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {
        scope.launch {
            try {
                val response = jsonApi.getBeersList(PAGE, PAGE_SIZE)
                when {
                    response.isSuccessful -> {
                        val key: Int?
                        if (response.body()?.isNotEmpty() == true) key = params.key + 1
                        else key = null
                        callback.onResult(response.body() ?: emptyList(), key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->BeerList", "2." + exception.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {
        scope.launch {
            try {
                val response = jsonApi.getBeersList(PAGE, PAGE_SIZE)
                val key: Int? = if (params.key > 1) params.key - 1
                else null
                when {
                    response.isSuccessful -> {
                        callback.onResult(response.body() ?: emptyList(), key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->BeerList", "2" + exception.message)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}