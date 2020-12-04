import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.jhughes.getstuffdone"
        minSdkVersion(29)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.jhughes.getstuffdone.MainTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
        kotlinCompilerVersion = Libs.Kotlin.version
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Kotlin.Coroutines.android)

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.activityKtx)
    implementation(Libs.AndroidX.fragmentKtx)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.UI.tooling)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.runtimeLivedata)
    implementation(Libs.AndroidX.Compose.navigation)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(Libs.AndroidX.Room.runtime)
    kapt(Libs.AndroidX.Room.compiler)
    implementation(Libs.AndroidX.Room.ktx)
    implementation(Libs.AndroidX.Work.runtimeKtx)

    implementation(Libs.Accompanist.insets)

    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)
    implementation(Libs.Hilt.AndroidX.viewModel)
    kapt(Libs.Hilt.AndroidX.compiler)

//    testImplementation(Libs.AndroidX.coreTesting)
//    testImplementation(Libs.mockk)
//    testImplementation(Libs.Kotlin.Coroutines.test)
//    testImplementation(Libs.JUnit.junit)
//    testImplementation(Libs.Hilt.android)
//    testImplementation(Libs.Hilt.testing)
//    kaptTest(Libs.Hilt.compiler)
//    testImplementation(Libs.AndroidX.Room.test)
//    testImplementation(Libs.Hilt.AndroidX.viewModel)
//    kaptTest(Libs.Hilt.AndroidX.compiler)

    androidTestImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.AndroidX.coreTesting)
    androidTestImplementation(Libs.AndroidX.Test.core)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Work.testing)
    androidTestImplementation(Libs.AndroidX.Compose.UI.uiTest)
    androidTestImplementation(Libs.Hilt.android)
    androidTestImplementation(Libs.Hilt.testing)
    androidTestImplementation(Libs.Kotlin.Coroutines.test)
    androidTestImplementation(Libs.truth)
    kaptAndroidTest(Libs.Hilt.compiler)
    androidTestImplementation(Libs.Hilt.AndroidX.viewModel)
    kaptAndroidTest(Libs.Hilt.AndroidX.compiler)

}