import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin: compassai.android.application
 *
 * Applied ONLY to the :app module. Sets up the Android Application plugin
 * with shared config. The :app module still declares its own applicationId,
 * versionCode, versionName.
 *
 * Usage in :app/build.gradle.kts:
 *   plugins { id("compassai.android.application") }
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidCommon(this)

                defaultConfig {
                    targetSdk = 35
                }

                buildTypes {
                    debug {
                        isDebuggable = true
                        applicationIdSuffix = ".debug"
                        versionNameSuffix = "-debug"
                    }
                    release {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}
