package com.example.chess2

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.InitializationListener
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_menu.*

class Description : AppCompatActivity() {
    var yandexAd: YandexAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        imageView5.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC9fdM9volSGlCTefuCYuTkw"))
            startActivity(browserIntent)

            MobileAds.initialize(this, InitializationListener(){})

            val AdRequest = AdRequest.Builder().build()
            yandexAd = YandexAd(this, GameData.AdUnitId, AdRequest)
            yandexAd?.createAd()
            yandexAd?.showAd()
            yandexAd?.loadAd()
            //MobileAds.enableDebugErrorIndicator(true)
        }
    }
}