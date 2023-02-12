package com.example.chess2


class GameData {
    companion object {
        // результаты удачной атаки
        val atackresultsuccess: Array<Int> = arrayOf(20, 0, 40, 20, 20, 40)
        val atackresultZombie: Array<Int> = arrayOf(20, 20, 0)
        // Ход (true - deadpool, false - zombie)
        var move: Boolean = true
        // Атака текст
        fun createAttackText(number: Int, hp: Int): String {
            val AttackText: String = "Вы атаковали зомби $number, ваша атака отняла $hp hp"
            return AttackText
        }

        val defenseResponce: String = "Зомби смог отбить атаку"

        val deadPoolMoveText = "Ход Дедпула"
        val ZombieMoveText = "Ход Зомби"
        val hpText = " hp"

        val AdUnitId = "demo-interstitial-yandex"
    }



    }

