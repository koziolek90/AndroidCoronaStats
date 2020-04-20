package pl.krko.coronastats.networking

import io.reactivex.Observable
import pl.krko.coronastats.model.Corona
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RequestsApi {

    @GET("countries")
    fun getAll(): Observable<List<Corona>>

    @GET("countries/Poland")
    fun getPoland(): Observable<Corona>

    @GET("countries")
    fun getSortedBy(@Query("sort") sortParameter: String): Observable<List<Corona>>

}