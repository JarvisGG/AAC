package jarvis.com.aac.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * @author yyf
 * @since 08-12-2019
 */
internal object Apis {

    val MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()
    val json: StringFormat = Json(JsonConfiguration.Stable.copy(strictMode = false))
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.printf520.com:8080/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(json.asConverterFactory(MEDIA_TYPE))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    /**
     * 所有 Http 接口
     */
    val feed: FeedService = retrofit.create(FeedService::class.java)
}