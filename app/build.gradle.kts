plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}
android {
    namespace = "com.zoho.task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zoho.task"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation Graph
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")

    //gson convertor
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //serialization
    implementation("com.google.code.gson:gson:2.10")

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //Paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    // LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.4.0")

    //ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    //Coil for Compose
    implementation("io.coil-kt:coil-compose:2.1.0")

    //constraintlayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //room
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:1.0.0" )


    //Compose Runtime
    implementation("androidx.compose.runtime:runtime:1.5.0")

    //Coroutines Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")


}