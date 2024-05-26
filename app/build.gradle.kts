plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.nopshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nopshop"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation("me.relex:circleindicator:2.1.6")
    // Carousel
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    // OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // Coil
    implementation("io.coil-kt:coil:2.6.0")
    // Jsoup
    implementation ("org.jsoup:jsoup:1.17.2")
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}