package com.weynard02.capstonegamelistapp.utils

import com.weynard02.core.domain.model.Game

object DataDummy {
    fun generateDummyGame(): List<Game> {
        val gameList = ArrayList<Game>()
        for (i in 0..10) {
            val game = Game(
                i,
                "Alex $i",
                "2022-02-02",
                "",
                4.5,
                10,
                false
            )
            gameList.add(game)
        }
        return gameList
    }
}