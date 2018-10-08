package com.worldsnas.objectboxrelationstest

import android.app.Application
import com.worldsnas.objectboxrelationstest.entity.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

lateinit var box: BoxStore

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        box = MyObjectBox.builder().androidContext(this).build()
        AndroidObjectBrowser(box).start(this)

    }
}