package pl.krko.coronastats.networking

import io.reactivex.Observable
import pl.krko.coronastats.model.objects.Corona
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RequestsApi {

    @GET("all")
    fun getWorldStats(): Observable<Corona>

    @GET("countries/Poland")
    fun getPoland(): Observable<Corona>

    @GET("countries")
    fun getCountriesSortedBy(@Query("sort") sortParameter: String): Observable<List<Corona>>

}