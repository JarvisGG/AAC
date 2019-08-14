package jarvis.com.aac.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @author yyf
 * @since 08-14-2019
 */
@Parcelize
@Serializable
data class FeedTab(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String
): Parcelable