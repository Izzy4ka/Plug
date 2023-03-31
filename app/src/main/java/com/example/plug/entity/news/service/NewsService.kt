package com.example.plug.entity.news.service

import com.example.plug.entity.news.entity.NewsEntity

object NewsService {

    private val news = mutableListOf<NewsEntity>()

    init {
        initNews()
    }

    private fun initNews() {
        with(news) {
            add(
                NewsEntity(
                    0,
                    "https://e0.365dm.com/23/03/1600x900/skysports-maren-mjelde-chelsea_6105019.jpg?20230330225749",
                    "Chelsea knocked out holders Lyon on penalties to reach the Women's Champions League" +
                            " semi-finals after Maren Mjelde scored a controversial" +
                            " spot-kick in the 128th minute to level the tie on aggregate.",
                    "The hosts had looked to be heading out when substitute Sara Dabritz" +
                            " fired Lyon into a 2-0 lead on the night" +
                            " in the second half of extra-time, " +
                            "adding to Vanessa Gilles' strike in normal time" +
                            " and overturning a one-goal deficit from the first leg." +
                            "But deep into injury time, Lauren James went down" +
                            " in the area under a challenge from Vicki Becho" +
                            " and after being instructed by the VAR" +
                            " to look at the pitchside monitor" +
                            ", referee Ivana Martincic pointed to the spot. " +
                            "Substitute Mjelde kept her cool to smash the penalty" +
                            " into the top left corner and send the tie to a shootout."
                )
            )
            add(
                NewsEntity(
                    1,
                    "https://e0.365dm.com/22/10/1600x900/skysports-erling-haaland-virgil-van-dijk_5932944.jpg?20221016173458",
                    "Paul Merson says" +
                            ": Must-win game for Man City vs Liverpool | 'Arsenal" +
                            " give me Leicester vibes'",
                    "Sky Sports' Paul Merson: " +
                            "\"This is a cup final for Manchester City. " +
                            "They have to win this game. " +
                            "It's a nightmare game for both Man City and Liverpool" +
                            " to have so soon after the international break. " +
                            "But the pressure is on Man City. They cannot afford to lose. " +
                            "They cannot even afford to draw\""
                )
            )
            add(
                NewsEntity(
                    2,
                    "https://e0.365dm.com/23/03/1600x900/skysports-antonio-conte-richarlison_6104768.jpg?20230330183011",
                    "Richarlison: Tottenham forward says he was not 'mutiny leader' behind Antonio Conte's Spurs exit",
                    "Richarlison was accused by a journalist from Argentinian broadcaster TyC Sports of giving an ultimatum to Tottenham alongside club team-mate Cristian Romero over Conte's future; " +
                            "\"I wasn't a mutiny leader against him, " +
                            "it was quite the opposite,\" the Brazilian said on Twitter"
                )
            )
            add(
                NewsEntity(
                    3,
                    "https://e0.365dm.com/23/03/768x432/skysports-zoe-aldcroft-womens-rugby-union_6104487.jpg?20230330143608",
                    "Women's Six Nations 2023: Zoe Aldcroft replaces Sarah Hunter as Delaney Burns makes debut for England against Italy",
                    "Marlie Packer has been named as England captain for the remainder of the Six Nations; England continue their Six Nations campaign when they face Italy at Franklin's Gardens in Northampton on Sunday, with kick-off at 3pm"
                )
            )
        }


    }

    fun getNew(id: Int): NewsEntity = news[id]


    fun getNews() = news
}