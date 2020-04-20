package pl.krko.coronastats.networking

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import pl.krko.coronastats.model.objects.Corona
import pl.krko.coronastats.model.enums.SortParam

internal object RequestsClient {
    private val requestsApi: RequestsApi = NetworkClient.requestsApi


    @SuppressLint("CheckResult")
    fun getWorldStats(
        successConsumer: Consumer<Corona>,
        errorConsumer: Consumer<Throwable>
    ) {
        requestsApi.getWorldStats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(successConsumer, errorConsumer)
    }

    @SuppressLint("CheckResult")
    fun getPoland(
        successConsumer: Consumer<Corona>,
        errorConsumer: Consumer<Throwable>
    ) {
        requestsApi.getPoland()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(successConsumer, errorConsumer)
    }

    @SuppressLint("CheckResult")
    fun getCountriesSortedBy(
        sortedParam: SortParam,
        successConsumer: Consumer<List<Corona>>,
        errorConsumer: Consumer<Throwable>
    ) {
        requestsApi.getCountriesSortedBy(sortedParam.paramString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(successConsumer, errorConsumer)
    }
}