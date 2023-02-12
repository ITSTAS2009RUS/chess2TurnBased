package com.example.chess2

import android.content.Context
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.interstitial.InterstitialAd

class YandexAd(context: Context, AdUnitId: String, AdRequest: AdRequest) {
    val AdUnitId = AdUnitId
    val contextVal = context
    val AdRequest = AdRequest
    val mInterstitialAd = InterstitialAd(contextVal)

    fun createAd(){
        mInterstitialAd.setAdUnitId(AdUnitId)

    }
    fun loadAd(){
        mInterstitialAd.loadAd(AdRequest)
    }
    fun showAd(){
        mInterstitialAd.show()
    }
}