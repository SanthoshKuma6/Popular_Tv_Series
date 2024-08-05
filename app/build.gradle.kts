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
    implementation(libs.androidx.navigation.compose)
    //retrofit
    implementation(libs.retrofit)
    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    //gson convertor
    implementation(libs.converter.gson)
    //serialization
    implementation(libs.gson)
    //viewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //Paging
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    // LiveData
    implementation(libs.androidx.runtime.livedata)
    // Activity Compose
    implementation(libs.androidx.activity.compose.v140)
    //ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Coil for Compose
    implementation(libs.coil.compose)
    //constraintlayout
    implementation(libs.androidx.constraintlayout.compose)
    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor (libs.androidx.room.compiler )
    //Compose Runtime
    implementation(libs.androidx.runtime)
    //Coroutines Core
    implementation(libs.kotlinx.coroutines.core)

}