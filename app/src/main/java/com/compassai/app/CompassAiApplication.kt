package com.compassai.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class.
 *
 * Annotated with @HiltAndroidApp to trigger Hilt's code generation and
 * establish the application-level dependency injection component.
 * This is the root of the entire Hilt component hierarchy.
 */
@HiltAndroidApp
class CompassAiApplication : Application()
