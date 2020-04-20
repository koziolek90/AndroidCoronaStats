package pl.krko.coronastats.ui.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import pl.krko.coronastats.model.Corona
import pl.krko.coronastats.model.SortParam
import pl.krko.coronastats.networking.RequestsClient
import pl.krko.coronastats.ui.mainactivity.list.CoronaAdapter

class MainActivityViewModel : ViewModel(), MainActivityActions {

    val refreshingVisibility: MutableLiveData<Boolean> = MutableLiveData(true)
    val allCoronaObjects: MutableLiveData<List<Corona>> = MutableLiveData()

    val openCoronaDetails = MutableLiveData<Corona>()
    val showToastAction = MutableLiveData<String>()

    var adapter: CoronaAdapter = CoronaAdapter(ArrayList(), this::onAdapterItemCLick)

    init {
        getAllData()
    }

    override fun getAllData() {
        RequestsClient.getSortedBy(SortParam.CASES,
            Consumer { response ->
                response?.let { list ->
                    val mutableList = mutableListOf<Corona>()
                    mutableList.addAll(list)

                    val poland = mutableList.find { it.country == "Poland" }

                    poland?.let {
                        val polPos = mutableList.indexOf(poland)

                        mutableList.removeAt(polPos)
                        mutableList.add(0, poland)
                    }

                    adapter = CoronaAdapter(mutableList, this::onAdapterItemCLick)
                    allCoronaObjects.postValue(mutableList)
                    refreshingVisibility.postValue(false)
                }
            },
            Consumer { error ->
                error.printStackTrace()
                showToastAction.postValue("Data downloading error")
            }
        )
    }

    private fun onAdapterItemCLick(corona: Corona) {
        openCoronaDetails.postValue(corona)
    }

}