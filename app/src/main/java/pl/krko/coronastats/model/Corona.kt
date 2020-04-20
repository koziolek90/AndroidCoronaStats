package pl.krko.coronastats.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Corona (

    @JsonProperty("country")
    val country: String,

    @JsonProperty("cases")
    val cases: Int,

    @JsonProperty("todayCases")
    val todayCases: Int,

    @JsonProperty("deaths")
    val deaths: Int,

    @JsonProperty("todayDeaths")
    val todayDeaths: Int,

    @JsonProperty("recovered")
    val recovered: Int,

    @JsonProperty("active")
    val active: Int,

    @JsonProperty("critical")
    val critical: Int,

    @JsonProperty("casesPerOneMillion")
    val casesPerOneMillion: Int,

    @JsonProperty("deathsPerOneMillion")
    val deathsPerOneMillion: Int,

    @JsonProperty("tests")
    val tests: Int,

    @JsonProperty("testsPerOneMillion")
    val testsPerOneMillion: Int

)