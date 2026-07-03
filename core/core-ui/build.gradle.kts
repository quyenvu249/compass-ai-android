plugins {
    id("compassai.android.compose")
}

android {
    namespace = "com.compassai.core.ui"
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.coil.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
