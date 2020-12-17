object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha03"

    const val junit = "junit:junit:4.13"

    object Kotlin {
        const val version = "1.4.20"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            private const val version = "1.4.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0-rc01"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha01"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val activityKtx = "androidx.activity:activity-ktx:1.2.0-beta01"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-beta01"

        const val junit = "junit:junit:4.13"

        const val material = "com.google.android.material:material:1.2.1"

        object Work {
            const val version = "2.4.0"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
            const val testing = "androidx.work:work-testing:$version"
        }

        object Room {
            const val version = "2.2.5"

            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version" //kapt
            // optional - Kotlin Extensions and Coroutines support for Room
            const val ktx = "androidx.room:room-ktx:$version"
            // optional - Test helpers
            const val test = "androidx.room:room-testing:$version"
        }

        object Compose {
            const val version = "1.0.0-alpha08"

            const val compiler = "androidx.compose.compiler:compiler:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended =
                    "androidx.compose.material:material-icons-extended:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val test = "androidx.compose.test:test-core:$version"
            const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha03"

            object UI {
                const val tooling = "androidx.ui:ui-tooling:1.0.0-alpha07"
                const val uiTest = "androidx.ui:ui-test:1.0.0-alpha07"
            }
        }

        object Lifecycle {
            private const val version = "2.3.0-beta01"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
            const val runner = "androidx.test:runner:$version"

            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Hilt {
        private const val version = "2.29.1-alpha"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"

        //intergration with Hilt and Jetpack (ie viewModels)
        object AndroidX {
            private const val version = "1.0.0-alpha02"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
        }
    }

    object Accompanist {
        private const val version = "0.4.0"
        const val insets = "dev.chrisbanes.accompanist:accompanist-insets:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    const val mockk = "io.mockk:mockk:1.10.0"

    const val truth = "com.google.truth:truth:0.42"
}