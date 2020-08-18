package com.sverbusoft.genesis_test

import android.app.Application
import android.content.Context

class App: Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App

        fun getContext(): Context {
            return instance.applicationContext
        }
    }

}