import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Applies to: core-ui
 * Android library + Compose enabled
 */
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35

                buildFeatures {
                    compose = true
                }
            }

            // Apply Compose BOM so all compose versions align automatically
            dependencies {
                val bom = platform("androidx.compose:compose-bom:2024.09.03")
                add("implementation", bom)
                add("androidTestImplementation", bom)
                add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:2.1.2")
            }
        }
    }
}
