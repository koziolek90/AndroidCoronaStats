package pl.krko.coronastats.ui.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import pl.krko.coronastats.model.DataProcess
import pl.krko.coronastats.model.objects.Corona
import pl.krko.coronastats.model.enums.SortParam
import pl.krko.coronastats.networking.RequestsClient
import pl.krko.coronastats.ui.mainactivity.list.CoronaAdapter

class MainActivityViewModel : ViewModel(), MainActivityActions {

    val refreshingVisibility: MutableLiveData<Boolean> = MutableLiveData(true)
    val allCoronaObjects: MutableLiveData<List<Corona>> = MutableLiveData()

    val openCoronaDetails = MutableLiveData<Corona>()
    val showToastAction = MutableLiveData<String>()

    var adapter: CoronaAdapter = CoronaAdapter(mutableListOf(), this::onAdapterItemLongClick, this::onAdapterItemClick)

    init {
        adapter.data.clear()
        refreshingVisibility.postValue(true)

        getSummaryData()
    }

    //Get world statistics
    override fun getSummaryData() {
        RequestsClient.getWorldStats(
            Consumer { response ->
                response?.let {
                    response.country = "World"
                    adapter.data.add(response)
                    adapter.notifyDataSetChanged()
                    getCountriesData()
                }
            },
            Consumer {
                getCountriesData()
                handleDownloadError(it)
            }
        )
    }

    //Get all countries data
    override fun getCountriesData() {
        RequestsClient.getCountriesSortedBy(
            SortParam.CASES,
            Consumer { response ->
                response?.let { list ->
                    val dataList = DataProcess.moveCountryToFirstPosition("Poland", list)

                    adapter.data.addAll(dataList)
                    adapter.notifyDataSetChanged()

                    allCoronaObjects.postValue(dataList)
                    refreshingVisibility.postValue(false)
                }
            },
            Consumer { handleDownloadError(it) }
        )
    }


    private fun handleDownloadError(error: Throwable) {
        error.printStackTrace()
        showToastAction.postValue("Data downloading error")
    }

    private fun onAdapterItemClick(corona: Corona) {
        openCoronaDetails.postValue(corona)
    }

    private fun onAdapterItemLongClick(corona: Corona) {
        openCoronaDetails.postValue(corona)
    }

}