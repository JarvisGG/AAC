package jarvis.com.aac.api

import io.reactivex.Observable
import jarvis.com.aac.model.FeedItem
import jarvis.com.aac.model.FeedTab
import jarvis.com.aac.model.State
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author yyf
 * @since 08-12-2019
 */
interface FeedService {

    /**
     * 获得指定平台热榜
     */
    @GET("GetTypeInfo")
    fun getFeedPage(
        @Query("id") id: Int
    ): Observable<Response<State<ArrayList<FeedItem>>>>

    /**
     * 获取所有分类
     */
    @GET("GetType")
    fun getFeedTabs(): Observable<Response<State<ArrayList<FeedTab>>>>
}