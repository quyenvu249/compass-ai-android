plugins {
    id("compassai.android.library")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.compassai.core.data"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-network"))

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Security (encrypted SharedPreferences for token storage)
    implementation(libs.androidx.security.crypto)

    testImplementation(libs.bundles.testing.unit)
}
