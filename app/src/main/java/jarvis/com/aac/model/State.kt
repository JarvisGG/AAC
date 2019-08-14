package jarvis.com.aac.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author yyf
 * @since 08-14-2019
 */
@Serializable
data class State<T> (
    @SerialName("Code") val code: String,
    @SerialName("Message") val message: String,
    @SerialName("Data") val data: T
)