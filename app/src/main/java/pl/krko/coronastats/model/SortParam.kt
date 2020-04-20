package pl.krko.coronastats.model

enum class SortParam (val paramString: String) {
    COUNTRY("country"),
    CASES("cases"),
    TODAY_CASES("todayCases"),
    DEATHS("deaths"),
    TODAY_DEATHS("todayDeaths"),
    RECOVERED("recovered"),
    ACTIVE("active"),
    CRITICAL("critical"),
    CASES_PER_MILION("casesPerOneMillion")
}