package com.example.plug.news.service

import com.example.plug.news.entity.NewsEntity

class NewsService private constructor() {

    companion object {
        private val newsService: NewsService? = null

        fun getInstance(): NewsService {
            return newsService ?: NewsService()
        }
    }

    private val news = mutableListOf<NewsEntity>()

    init {
        initNews()
    }

    private fun initNews() {
        for (i in 1..20) {
            news.add(
                NewsEntity(
                    i,
                    "https://gzt-akray.by/wp-content/uploads/2020/10/novosti-sporta-1068x601.jpg",
                    "Четыре клуба КХЛ заинтересованы в подписании нападающего минского «Динамо» Звягина",
                    "Как стало известно «СЭ», четыре клуба КХЛ заинтересованы в подписании нападающего минского «Динамо» Степана Звягина.\n" +
                            "\n" +
                            "В нынешнем сезоне 18-летний форвард дебютировал в КХЛ и забил один гол в 11 матчах регулярного чемпионата.\n" +
                            "\n" +
                            "Также Звягин в мае 2022 года дебютировал за сборную Белоруссии по ходу турнира в Санкт-Петербурге."
                )
            )
        }
    }

    fun getNews() = news
}