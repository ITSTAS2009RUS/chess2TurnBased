package com.example.chess2

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Menu : AppCompatActivity() {
    var menuSound: MediaPlayer? = null
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
                menuSound!!.pause()
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
        setContentView(R.layout.activity_menu)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        lifecycleScope.launch(){
            menu()
        }
    }
    private suspend fun menu(){
        menuSound = MediaPlayer.create(this, R.raw.main_menu)
        menuSound!!.start()
        while (true){
            delay(1)
            val ConnectionManager = ConnectionManager()
            val connectStatus = ConnectionManager.isOnline(this)
            startgame.setOnClickListener{
                if(connectStatus){
                    val launchGame = Intent(this, MainActivity::class.java)
                    startActivity(launchGame)
                    menuSound!!.stop()
                    finish()
                }
                if(!connectStatus){
                    Toast.makeText(this, "Не удалось подключится к серверу, проверьте подключение к сети", Toast.LENGTH_LONG).show()
                }
            }
            imageView6.setOnClickListener{
                val launchGame = Intent(this, Description::class.java)
                startActivity(launchGame)
            }
        }
    }
}