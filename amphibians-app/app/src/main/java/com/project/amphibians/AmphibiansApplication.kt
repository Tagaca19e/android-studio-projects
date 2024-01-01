package com.project.amphibians

import android.app.Application
import com.project.amphibians.data.AppContainer
import com.project.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
  lateinit var container: AppContainer
  override fun onCreate() {
    super.onCreate()
    container = DefaultAppContainer()
  }
}
