package com.future.awaker

import android.content.Context
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import com.lion.font.FontHelper
import com.ruzhan.common.util.ResUtils

class MainApplication : android.app.Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ResUtils.init(this)
        com.ruzhan.lion.util.ResUtils.init(this)
        FontHelper.init(this)
        Utils.init(this)
    }
}