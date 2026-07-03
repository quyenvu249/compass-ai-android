plugins {
    id("compassai.android.feature")
}

android {
    namespace = "com.compassai.feature.analysis"
}

dependencies {
    implementation(project(":core:core-data"))
    implementation(project(":core:core-network"))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
