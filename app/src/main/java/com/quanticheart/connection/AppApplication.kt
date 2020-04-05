package com.quanticheart.connection

import android.app.Application
import android.content.Context

/**
 * Remember**
 *
 * Add AppApplication in Manifest
 *
 * Example:
 *
 *   <application
 *     android:name=".AppApplication"
 */

class AppApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    init {
        appContext = this
    }
}
