package com.example.chess2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.chess2.DeadPool.Companion.deadPoolHealth
import com.google.android.material.snackbar.Snackbar
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    var attackManager: Attack? = null
    var defenseManager: Defense? = null
    var parentLayout: View? = null
    var activeHealth: ImageView? = null
    var yandexAd: YandexAd? = null
    var gameSound: MediaPlayer? = null
    var resumeStatus: Boolean? = null

    override fun onResume() {
        super.onResume()
        resumeStatus = true
        Log.e("gp", "пееееееееееееееееееенис урал ")

    }
    suspend fun pauseMusic(){
        while(true){
            delay(1)
            Log.e("gp", resumeStatus.toString())
            if(resumeStatus!!){
                break
            }
            if(!resumeStatus!!){
                gameSound!!.pause()
            }

        }
    }
    override fun onStop() {
        super.onStop()
        Log.e("gp", "пееееееееееееееееееенис")
        resumeStatus = false
        lifecycleScope.launch(){
            pauseMusic()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parentLayout = findViewById(R.id.constraintLayout)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        lifecycleScope.launch(){
            game()
        }

    }



    @SuppressLint("SetTextI18n")
    private suspend fun game(){
        val mPlayer = MediaPlayer.create(this, R.raw.kick)
        val zombieVoice = MediaPlayer.create(this, R.raw.zombie)
        gameSound = MediaPlayer.create(this, R.raw.gamesound)
        val winSound = MediaPlayer.create(this, R.raw.win)
        gameSound!!.start()
        while (true){
            delay(1)
            gameSound!!.start()
            attack.isEnabled = true
            defense.isEnabled = true
            imageView3434.setImageResource(R.drawable.win)
            imageView3434.isVisible = false
            imageView337.isVisible = false
            deadpool.setImageResource(R.drawable.deadpool)

            val ConnectionManager = ConnectionManager()
            val connectStatus = ConnectionManager.isOnline(this)

            if(!connectStatus){
                attack.isEnabled = false
                defense.isEnabled = false
                imageView337.isVisible = true
                imageView3434.setImageResource(R.drawable.dissconnect)
                imageView3434.isVisible = true
            }
            when(Zombie.zombieUnitStatus){
                1 -> imageView14.setImageResource(R.drawable.zombie)
                2 -> imageView19.setImageResource(R.drawable.zombie)
                3 -> imageView20.setImageResource(R.drawable.zombie)
            }

            if(Zombie.zoombie1 <= 0){
                imageView26.setImageResource(R.drawable.hp0)
                imageView14.setImageResource(R.drawable.dead_zombie)
            }
            if(Zombie.zoombie2 <= 0){
                imageView27.setImageResource(R.drawable.hp0)
                imageView19.setImageResource(R.drawable.dead_zombie)
            }
            if(Zombie.zoombie3 <= 0){
                imageView28.setImageResource(R.drawable.hp0)
                imageView20.setImageResource(R.drawable.dead_zombie)
            }
            if(Zombie.zoombie3 <=0 && Zombie.zoombie2 <=0 && Zombie.zoombie1 == 990){
                zHpStatus3.text = "0" + GameData.hpText
                gameSound!!.stop()
                winSound.start()

                imageView337.isVisible = true
                imageView3434.isVisible = true

                delay(3000)
                val menuIntent = Intent(this, Menu::class.java)
                val AdRequest = AdRequest.Builder().build()

                yandexAd = YandexAd(this, GameData.AdUnitId, AdRequest)
                yandexAd?.createAd()
                yandexAd?.showAd()
                MobileAds.enableDebugErrorIndicator(true)
                startActivity(menuIntent)

                Zombie.zoombie1 = 100
                Zombie.zoombie2 = 100
                Zombie.zoombie3 = 100
                deadPoolHealth = 200
                GameData.move = true
                break
            }
            if(deadPoolHealth <= 0){
                deadpool.setImageResource(R.drawable.deaddeadpool)
                break
            }

            if(Zombie.zoombie3 != 0 && Zombie.zoombie2 != 0 && Zombie.zoombie1 > 0 && Zombie.zoombie1 != 990) {
                Zombie.activeZombie = Zombie.zoombie1
                Zombie.zoombie1 = Zombie.activeZombie!!
                activeHealth = imageView26
                Log.e("gp", "Зомби 1")
                Zombie.zombieUnitStatus = 1
            }
            if(Zombie.zoombie1 <= 0 && Zombie.zoombie1 != 990) {
                Zombie.activeZombie = Zombie.zoombie2
                Zombie.zoombie2 = Zombie.activeZombie!!
                activeHealth = imageView27
                Log.e("gp", "Зомби 2")
                Zombie.zombieUnitStatus = 2
            }
            if(Zombie.zoombie2 <= 0) {
                Zombie.zoombie1 = 990
                Zombie.activeZombie = Zombie.zoombie3
                Zombie.zoombie3 = Zombie.activeZombie!!
                activeHealth = imageView28
                Log.e("gp", "Зомби 3")
                Zombie.zombieUnitStatus = 3
            }
            Log.e("gp", "Актив зомби " + Zombie.activeZombie.toString())
            Log.e("gp", "Зомби 2 " + Zombie.zoombie2.toString())
            Log.e("gp", "Зомби 1 " + Zombie.zoombie1.toString())
            Log.e("gp", "Зомби 3 " + Zombie.zoombie3.toString())
            Zombie.updateHealth(Zombie.activeZombie!!, activeHealth!!)
            DeadPool.updateDpHealth(dpHealth1)

            dpHpStatus.text = deadPoolHealth.toString() + GameData.hpText
            zHpStatus1.text = Zombie.zoombie1.toString() + GameData.hpText
            zHpStatus2.text = Zombie.zoombie2.toString() + GameData.hpText
            zHpStatus3.text = Zombie.zoombie3.toString() + GameData.hpText

            if(Zombie.zoombie1 == 990 || Zombie.zoombie1 == -20)
                zHpStatus1.text = "0" + GameData.hpText

            if(Zombie.zoombie2 == -20)
                zHpStatus2.text = "0" + GameData.hpText

            deadpool.setImageResource(R.drawable.move_dp)
            delay(80)
            deadpool.setImageResource(R.drawable.deadpool)

            // Ход детпула
            if(GameData.move){
                moveText.text = GameData.deadPoolMoveText
                defense.setOnClickListener{
                    defenseManager = Defense()
                    defenseManager!!.deadpoolDefence()  
                    GameData.move = false
                    val result: Snackbar = Snackbar.make(parentLayout!!, "Вы активируете щит", Snackbar.LENGTH_LONG)
                    result.show()

                }

                attack.setOnClickListener{
                    deadpool.setImageResource(R.drawable.attackdeadpool)
                    attackManager = Attack(it, this)
                    when(GameData.atackresultsuccess.random()){
                        0 -> {
                            attackManager!!.deadpoolAttackNull(1, Zombie.activeZombie!!)
                            mPlayer.start()
                        }
                        20 -> {
                            attackManager!!.deadpoolAttackTwenty(1, Zombie.activeZombie!!)
                            mPlayer.start()
                        }
                        40 -> {
                            attackManager!!.deadpoolAttackFourteen(1, Zombie.activeZombie!!)
                            mPlayer.start()
                        }

                    }
                    GameData.move = false

                }
                delay(1000)
            }
            // Ход зомби
            else if (!GameData.move){
                moveText.text = GameData.ZombieMoveText
                delay(1000)
                val zombieMoveStatus = Zombie.zombieMoveStatus.random()
                Log.e("gp", zombieMoveStatus.toString())

                if(zombieMoveStatus){
                    Log.e("gp", "Атака")

                    if(!Defense.defenseStatusDeadPool){
                        when(Zombie.zombieUnitStatus){
                            1 -> imageView14.setImageResource(R.drawable.zombieattack)
                            2 -> imageView19.setImageResource(R.drawable.zombieattack)
                            3 -> imageView20.setImageResource(R.drawable.zombieattack)
                        }
                        when(GameData.atackresultZombie.random()){
                            0 -> {
                                Log.e("gp", "0")
                                Log.e("gp", deadPoolHealth.toString())
                                val result: Snackbar = Snackbar.make(parentLayout!!, "Зомби не удалось вас атаковать, у вас осталось $deadPoolHealth hp", Snackbar.LENGTH_LONG)
                                result.show()
                                zombieVoice.start()
                                deadPoolHealth -= 0
                                GameData.move = true


                            }
                            20 -> {
                                zombieVoice.start()
                                deadPoolHealth -= 20
                                val result: Snackbar = Snackbar.make(parentLayout!!, "Вас атаковали зомби, отняв у вас 20 hp, у вас осталось $deadPoolHealth hp", Snackbar.LENGTH_LONG)
                                result.show()
                                Log.e("gp", "20")
                                Log.e("gp", deadPoolHealth.toString())
                                GameData.move = true
                            }
                            40 -> {
                                zombieVoice.start()
                                deadPoolHealth -= 40
                                val result: Snackbar = Snackbar.make(parentLayout!!, "Вас атаковали зомби, отняв у вас 40 hp, у вас осталось $deadPoolHealth hp", Snackbar.LENGTH_LONG)
                                result.show()
                                Log.e("gp", "40")
                                Log.e("gp", deadPoolHealth.toString())
                                GameData.move = true
                            }
                        }
                        delay(1000)
                    }else{
                        val result: Snackbar = Snackbar.make(parentLayout!!, "Вы смогли защитится от зомби", Snackbar.LENGTH_LONG)
                        result.show()
                        GameData.move = true
                    }

                }else{
                    Defense.defenseStatusZombie = true
                    Log.e("gp", "Зомби активирует щит")
                    GameData.move = true
                }

            }
        }

    }
}