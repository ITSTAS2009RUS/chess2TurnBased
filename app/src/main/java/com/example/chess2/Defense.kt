package com.example.chess2

class Defense {
    companion object{
        var defenseStatusZombie: Boolean = false
        var defenseStatusDeadPool: Boolean = false

    }
    fun deadpoolDefence(){
        defenseStatusDeadPool = true
    }
}