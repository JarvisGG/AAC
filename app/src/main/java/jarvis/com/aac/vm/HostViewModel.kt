package jarvis.com.aac.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import jarvis.com.aac.api.Apis
import jarvis.com.aac.base.BaseViewModel
import jarvis.com.aac.model.FeedTab
import jarvis.com.aac.utils.Resource

/**
 * @author yyf
 * @since 08-09-2019
 */
class HostViewModel(application: Application) : BaseViewModel(application) {

    val feedTabsLiveData = MutableLiveData<Resource<ArrayList<FeedTab>>>()

    fun fetchFeedTabs() {
        Apis.feed.getFeedTabs()
            .subscribeOn(Schedulers.io())
            .autoDisposable()
            .subscribe({
                it.takeIf { res -> res.isSuccessful && res.body() != null }?.apply {
                    feedTabsLiveData.postValue(Resource.Success(it.body()!!.data))
                } ?: feedTabsLiveData.postValue(Resource.Error(Throwable(it.message())))

            }) {
                feedTabsLiveData.postValue(Resource.Error(it))
            }
    }

    val drawerSelectedTab = MutableLiveData<FeedTab>()

}