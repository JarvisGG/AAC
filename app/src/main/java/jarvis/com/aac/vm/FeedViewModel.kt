package jarvis.com.aac.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import jarvis.com.aac.api.Apis
import jarvis.com.aac.base.BaseViewModel
import jarvis.com.aac.model.FeedItem
import jarvis.com.aac.utils.Resource

/**
 * @author yyf
 * @since 08-09-2019
 */
class FeedViewModel(application: Application) : BaseViewModel(application) {

    val feedItemsLiveData = MutableLiveData<Resource<ArrayList<FeedItem>>>()

    fun fetchFeedDataById(id: Int) {

        Apis.feed.getFeedPage(id)
            .subscribeOn(Schedulers.io())
            .autoDisposable()
            .subscribe({
                it.takeIf { res -> res.isSuccessful && res.body() != null }?.apply {
                    feedItemsLiveData.postValue(Resource.Success(it.body()!!.data))
                } ?: feedItemsLiveData.postValue(Resource.Error(Throwable(it.message())))

            }) {
                feedItemsLiveData.postValue(Resource.Error(it))
            }
    }
}