package com.example.chess2

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Attack(view: View, context: Context) {
   val view = view
    val context = context

    fun deadpoolAttackNull(number: Int, unit: Int){
        if(!Defense.defenseStatusZombie){
            when(Zombie.zombieUnitStatus){
                1 -> Zombie.zoombie1 = Zombie.zoombie1 - 0
                2 -> Zombie.zoombie2 = Zombie.zoombie2 - 0
                3 -> Zombie.zoombie3 = Zombie.zoombie3 - 0

            }
            Defense.defenseStatusDeadPool = false
            val result: Snackbar = Snackbar.make(view, GameData.createAttackText(number, 0), Snackbar.LENGTH_LONG)
            result.show()
        }
        else{
            val result: Snackbar = Snackbar.make(view, GameData.defenseResponce, Snackbar.LENGTH_LONG)
            result.show()
            Defense.defenseStatusZombie = false
            Log.e("gp", "Атаковать не получилось")
        }

   }

    fun deadpoolAttackTwenty(number: Int, unit: Int){
        if(!Defense.defenseStatusZombie){
            when(Zombie.zombieUnitStatus){
                1 -> Zombie.zoombie1 = Zombie.zoombie1 - 20
                2 -> Zombie.zoombie2 = Zombie.zoombie2 - 20
                3 -> Zombie.zoombie3 = Zombie.zoombie3 - 20

            }
            Defense.defenseStatusDeadPool = false
            val result: Snackbar = Snackbar.make(view, GameData.createAttackText(number, 20), Snackbar.LENGTH_LONG)
            result.show()
        }
        else{
            val result: Snackbar = Snackbar.make(view, GameData.defenseResponce, Snackbar.LENGTH_LONG)
            result.show()
            Defense.defenseStatusZombie = false
            Log.e("gp", "Атаковать не получилось")
        }

    }

    fun deadpoolAttackFourteen(number: Int, unit: Int){
        if(!Defense.defenseStatusZombie){
            when(Zombie.zombieUnitStatus){
                1 -> Zombie.zoombie1 = Zombie.zoombie1 - 40
                2 -> Zombie.zoombie2 = Zombie.zoombie2 - 40
                3 -> Zombie.zoombie3 = Zombie.zoombie3 - 40

            }
            Defense.defenseStatusDeadPool = false
            val result: Snackbar = Snackbar.make(view, GameData.createAttackText(number, 40), Snackbar.LENGTH_LONG)
            result.show()
            Log.e("gp", Zombie.zoombie1.toString())
        }
        else{
            val result: Snackbar = Snackbar.make(view, GameData.defenseResponce, Snackbar.LENGTH_LONG)
            result.show()
            Defense.defenseStatusZombie = false
            Log.e("gp", "Атаковать не получилось")
        }
    }
}