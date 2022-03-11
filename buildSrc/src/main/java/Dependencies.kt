object BuildVersion {
    const val compile_sdk = 31
    const val min_sdk = 21
    const val target_sdk = 31
    const val version_code = 1
    const val version_name = "1.0"
}

object Version {
    const val lifecycle_version = "2.4.0"
    const val room_version = "2.4.2"
    const val paging_version = "3.1.0"
    const val retrofit_version = "2.9.0"
    const val okhttp_version = "4.9.3"

    const val coroutines = "1.5.2"
    const val support_lib_version = "1.4.0"
    const val glide_version = "4.13.0"
}

object Libs {

    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.support_lib_version}"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.2"
    const val material = "com.google.android.material:material:1.4.0"

    // architecture components
    const val android_core_ktx =  "androidx.core:core-ktx:1.7.0"
    const val lifecycle_runtime_ktx =  "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_version}"
    const val lifecycle_viewmodel_ktx =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle_version}"
    const val lifecycle_livedata_ktx =  "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle_version}"
    const val lifecycle_viewmodel_savedstate =  "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.lifecycle_version}"
    const val room_runtime =  "androidx.room:room-runtime:${Version.room_version}"
    const val room_compiler= "androidx.room:room-compiler:${Version.room_version}"
    const val room_ktx= "androidx.room:room-ktx:${Version.room_version}"
    const val room_paging = "androidx.room:room-paging:${Version.room_version}"
    const val paging_runtime_ktx= "androidx.paging:paging-runtime-ktx:${Version.paging_version}"

    // retrofit
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Version.retrofit_version}"
    const val retrofit2_gson = "com.squareup.retrofit2:converter-gson:${Version.retrofit_version}"
    const val retrofit2_mock = "com.squareup.retrofit2:retrofit-mock:${Version.retrofit_version}"
    const val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp_version}"

    // glide
    const val glide = "com.github.bumptech.glide:glide:${Version.glide_version}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Version.glide_version}"

}