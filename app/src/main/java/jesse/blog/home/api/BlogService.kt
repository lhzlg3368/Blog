package jesse.blog.home.api

import jesse.blog.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jesse
 * @date 2022/3/8
 * @email lhzlg3368@163.com
 */
interface BlogService {
    /**
     * 获取博客列表数据
     */
    @GET("/blog/{json}")
    suspend fun getPosts(@Path("json") json: String = "posts.json"): List<BlogResponse>?

    companion object {

        fun create(): BlogService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BlogService::class.java)
        }

    }
}