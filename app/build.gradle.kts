plugins {
    alias(libs.plugins.android.application) // Áp dụng plugin Android Application
}

android {
    namespace = "com.example.GoalMaster"  // Namespace của ứng dụng
    compileSdk = 34  // SDK biên dịch ứng dụng

    defaultConfig {
        applicationId = "com.example.GoalMaster"  // ID ứng dụng
        minSdk = 24  // Mức SDK tối thiểu
        targetSdk = 34  // Mức SDK mục tiêu
        versionCode = 1  // Mã phiên bản
        versionName = "1.0"  // Tên phiên bản

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  // Bộ chạy thử tự động
    }

    buildTypes {
        release {
            isMinifyEnabled = false  // Tắt tối ưu hóa khi build release
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"  // Quy tắc ProGuard tùy chỉnh
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // Sử dụng Java 17
        targetCompatibility = JavaVersion.VERSION_17  // Đảm bảo ứng dụng tương thích với Java 17
    }
}

dependencies {
    // Các thư viện cơ bản
    implementation(libs.appcompat)  // Thư viện AppCompat
    implementation(libs.material)  // Thư viện Material Design
    implementation(libs.activity)  // Thư viện Activity
    implementation(libs.constraintlayout)  // Thư viện ConstraintLayout
    implementation(libs.play.services.base)  // Google Play Services Base

        // Glide để load ảnh
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation(libs.contentpager)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")



    // Các thư viện kiểm thử
    testImplementation(libs.junit)  // JUnit cho kiểm thử
    androidTestImplementation(libs.ext.junit)  // JUnit cho kiểm thử trên Android
    androidTestImplementation(libs.espresso.core)  // Espresso cho kiểm thử UI

    // Retrofit & Gson cho xử lý API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")  // Retrofit để gọi API
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")  // Gson converter cho Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")  // Interceptor để log các yêu cầu và phản hồi HTTP
}
