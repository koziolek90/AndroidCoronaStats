package pl.krko.coronastats.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsActivityViewModel: ViewModel() {

    var country: String? = null
    set(value) {
        field = value
        initLoading()
    }

    val refreshingVisibility: MutableLiveData<Boolean> = MutableLiveData(true)

    private fun initLoading() {
        //todo - download historic data
    }
}