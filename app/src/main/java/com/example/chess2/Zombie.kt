package com.example.chess2

import android.widget.ImageView
import androidx.core.view.isVisible

class Zombie {
    companion object{
        val zombieMoveStatus: Array<Boolean> = arrayOf(true, true, false)
        var zombieUnitStatus = 1


        fun updateHealth(unit: Int, image: ImageView){
            when(unit){
                80 -> image.setImageResource(R.drawable.hp80)
                60 -> image.setImageResource(R.drawable.hp80)
                40 -> image.setImageResource(R.drawable.hp40)
                20 -> image.setImageResource(R.drawable.hp20)
                0 -> image.setImageResource(R.drawable.hp0)
            }
        }
        fun updateDepth(image: ImageView){
            if(activeZombie == 0){
                image.isVisible = false
            }
        }

        var activeZombie: Int? = null
        var zoombie1 = 100
        var zoombie2 = 100
        var zoombie3 = 100
    }

}