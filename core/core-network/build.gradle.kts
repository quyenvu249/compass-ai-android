plugins {
    id("compassai.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(libs.bundles.networking)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.bundles.testing.unit)
}
