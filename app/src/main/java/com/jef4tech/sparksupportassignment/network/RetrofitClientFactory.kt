package com.jef4tech.sparksupportassignment.network


import androidx.viewbinding.BuildConfig
import com.jef4tech.sparksupportassignment.BaseApplication
import com.jef4tech.sparksupportassignment.utils.Extensions.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClientFactory {
    private const val BASE_URL = "http://54.36.143.60:8000"


    val retrofitClient: Retrofit.Builder by lazy {
        val cacheSize = 50 * 1024
        val myCache = Cache(File(BaseApplication.instance.cacheDir, "responses"), cacheSize.toLong())
        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder().cache(myCache) .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        okhttpClient.addInterceptor { chain ->

            var request = chain.request()

            request = if (BaseApplication.instance.let { hasNetwork(it) }!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()

            chain.proceed(request)
        }


        Retrofit.Builder()
            .baseUrl(BASE_URL+"/")
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val restApis: RestApis by lazy {
        retrofitClient
            .build()
            .create(RestApis::class.java)
    }
}