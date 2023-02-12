package com.example.chess2

import android.widget.ImageView

class DeadPool {
    companion object{
        var deadPoolHealth = 200
        fun updateDpHealth(image: ImageView){
            when(deadPoolHealth){
                180 -> image.setImageResource(R.drawable.hp80)
                160 -> image.setImageResource(R.drawable.hp80)
                80 -> image.setImageResource(R.drawable.hp40)
                40 -> image.setImageResource(R.drawable.hp20)
                0 -> image.setImageResource(R.drawable.hp0)
            }
        }
    }

}