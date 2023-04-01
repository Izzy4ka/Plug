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
            add(
                NewsEntity(
                    4,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/tottenham-hotspur-director-football-fabio-806416240.jpg?w=670",
                    "FABI-OUT Tottenham’s managing director Fabio Paratici STEPS DOWN as he takes immediate leave of absence following worldwide ban",
                    "TOTTENHAM'S managing director of football Fabio Paratici is to take an immediate leave of absence after having his 30-month ban extended worldwide.\n" +
                            "\n" +
                            "The Italian, 50, was suspended by the Italian Football Federation (FIGC) for his role in the alleged financial irregularities scandal at Juventus." +
                            "Initially it had only referred to working in Italy but on Wednesday Fifa enforced the ban globally.\n" +
                            "\n" +
                            "Spurs were seeking clarification over whether he could still work for the club up until his appeal against it on April 19.\n" +
                            "\n" +
                            "But it has now been decided the former Juve director will step down from his role until that appeal.\n" +
                            "\n" +
                            "A Spurs statement read: \"This week - 29 March 2023 - the Fifa Disciplinary Committee announced a decision to extend the FIGC sanctions, relating to Fabio Paratici, worldwide. "
                )
            )
            add(
                NewsEntity(
                    5,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/newcastle-uniteds-bruno-guimaraes-wearing-785779051-2.jpg?w=620",
                    "HOWE BOUT THAT Newcastle stars to give Man Utd a guard of honour for winning the Carabao Cup when they meet in huge Prem clash",
                    "NEWCASTLE are planning to give Manchester United a guard of honour tomorrow recognising their Carabao Cup triumph.\n" +
                            "\n" +
                            "February’s loss in the Wembley final means Toon fans are still without a domestic trophy since 1955." +
                            "That has not stopped Newcastle giving the green light to a grand gesture to the Red Devils when they visit St James’ Park.\n" +
                            "\n" +
                            "The idea was proposed by Toon supporters group Trophies Don’t Matter, which boasts Newcastle having the best fans in the world is more important than silverware.\n" +
                            "\n" +
                            "TDM spokesman Lipra Olof said: “It is only right we salute our friends from Manchester. They were deserved winners.\n" +
                            "\n" +
                            "“We had a lovely day at Wembley and took over Trafalgar Square before the match, which is the most important thing.”"
                )
            )
            add(
                NewsEntity(
                    6,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/harry-kane-tottenham-hotspur-premier-802192145.jpg?w=670",
                    "RED MIST Man Utd in Harry Kane blow after Daniel Levy ‘made vow following transfer exit he still regrets’",
                    "Click to share on Twitter (Opens in new window)\n" +
                            "Click to share on Facebook (Opens in new window)\n" +
                            "MANCHESTER UNITED face another obstacle to securing Harry Kane after Daniel Levy reportedly made a vow over his transfer policy.\n" +
                            "\n" +
                            "The future of the Tottenham striker is set to be one of the biggest stories of the summer as Spurs try to cling on to their greatest asset." +
                            "But United, desperate for a forward, are expected to go all in for the 29-year-old goal machine.\n" +
                            "\n" +
                            "However the club could face an unexpected challenge to convince chairman Levy to sell up - and it all goes back to the actions of their rivals Manchester City.\n" +
                            "\n" +
                            "The Cityzens bought Kyle Walker from Spurs for £45m in 2017 with Levy, the Premier League's longest-serving chairman, sanctioning the sale.\n" +
                            "\n" +
                            "But the Mail claim that Levy has \"never forgiven himself\" over the deal and has \"vowed\" to not make the same mistake again."
                )
            )
            add(
                NewsEntity(
                    7,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/HB-SPORT-PREVIEW-TEN-HAG-RASHFORD-NEW.jpg?w=620",
                    "STRIKE OUT Erik ten Hag names 5 Man Utd players he wants to help ease scoring burden on Rashford… and goal-shy Weghorst ISN’T one",
                    "ERIK TEN HAG has called on five Manchester United players to grab more goals - but excluded striker Wout Weghorst.\n" +
                            "\n" +
                            "The Red Devils are targeting a place in the top four and more silverware to add to the Carabao Cup as the season draws to a close." +
                            "But they are becoming noticeably reliant on Marcus Rashford for goals, to the concern of their manager.\n" +
                            "\n" +
                            "The England striker has hit the net 27 times in all competitions, putting him 17 goals clear of his next closest teammate.\n" +
                            "\n" +
                            "He also has 25 more goals than January loanee signing Weghorst, who has struggled in front of goal across his 18 Ma Utd appearances.\n" +
                            "\n" +
                            "But the star was absent as ten Hag reeled off the players he wanted to see worry opposition goalkeepers more during his pre-match press conference."
                )
            )
            add(
                NewsEntity(
                    8,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/crop-21899073.jpg?w=620",
                    "NUTCRACKER FEAT Football star rushed to hospital for surgery after being kicked in the privates during training but can still have kids",
                    "A DANISH footballer was rushed to hospital for emergency surgery after he cracked a testicle - but can still have more kids.\n" +
                            "\n" +
                            "Lasse Nielsen, 35, took a horror kick downstairs from Malmo team-mate Hugo Bolin in training." +
                            "And the gruesome blow from the defender earlier in March required him to go under the knife that evening to repair the damage.\n" +
                            "\n" +
                            "Incredibly, though, just two weeks later Nielsen is back in full training and ready and raring to go again - just in time for the start of the new Allsvenskan season in Sweden.\n" +
                            "\n" +
                            "Nielsen, who played against Chelsea in the Champions League last season, said: \"The blow felt like it usually does when you get hit in the crotch. \n" +
                            "\n" +
                            "\"But the pain didn't go away after four or five minutes - that was what I was not used to."
                )
            )
            add(
                NewsEntity(
                    9,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/RF-COMBO-FORBES-1.jpg?w=620",
                    "TECH SAVVY I was rejected by Crystal Palace and made homeless before becoming a \$1billion CEO of a huge tech company",
                    "WHEN Dean Forbes missed out on a professional football career, he could have been forgiven for thinking his days of making millions would be over.\n" +
                            "\n" +
                            "The hard-to-take snub coincided with a difficult time in the future tech CEO's life, when he was made homeless at 17 when he was also the primary carer for his disabled mum." +
                            "Debt-ridden because he tried to keep up with his free-spending football mates, Forbes took on an entry-level sales job at Motorola call centre.\n" +
                            "\n" +
                            "In 20-plus years, he has worked his way up the corporate ladder to lead a major software firm.\n" +
                            "\n" +
                            "Last year, Forterro - who Forbes is CEO of - were bought out for a staggering \$1billion (£823million).\n" +
                            "\n" +
                            "While, his own net worth is believed to be around £40million."
                )
            )
            add(
                NewsEntity(
                    10,
                    "https://www.thesun.co.uk/wp-content/uploads/2023/03/head-coach-thomas-tuchel-fc-806157589-1.jpg?crop=470px%2C855px%2C2520px%2C1681px&resize=620%2C413",
                    "'BIGGEST MISTAKE' Fans slam Chelsea’s ‘shabby’ treatment of Thomas Tuchel as he reveals brutal details of his sacking",
                    "FANS have slammed Chelsea’s “shabby” treatment of Thomas Tuchel after he opened up on his Blues sacking.\n" +
                            "\n" +
                            "The German coach takes charge of Bayern Munich tomorrow against his former club Borussia Dortmund after signing a deal until 2025 with the Bundesliga giants." +
                            "Tuchel, 49, guided Chelsea to Champions League glory in 2021, and also enjoyed domestic success with Borussia Dortmund and Paris Saint-Germain.\n" +
                            "\n" +
                            "He has been out of work since being sacked by new Chelsea owner Todd Boehly in September - a decision which infuriated supporters at the time as he was replaced by Graham Potter.\n" +
                            "\n" +
                            "Now he has revealed the true extent of how brutal his departure was from the West London club after losing 1-0 to Dinamo Zagreb in the Champions League group stages.\n" +
                            "\n" +
                            "Tuchel, who also guided the Blues to UEFA Super Cup and Club World Cup success as well as reaching the FA Cup and Carabao Cup finals last season, said: “It was a shock."
                )
            )
        }


    }

    fun getNew(id: Int): NewsEntity = news[id]


    fun getNews() = news
}