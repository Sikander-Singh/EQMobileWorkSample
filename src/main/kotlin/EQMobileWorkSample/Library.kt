package EQMobileWorkSample
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException


public data class LocationEvent(val lat: Float, val lon: Float, val time: Long, val ext: String)
private val client = OkHttpClient()
public class Library {
    fun setup(): Boolean {
        return true
    }
    fun log(event: LocationEvent) {
        val formBody = FormBody.Builder()
                .add("latitude",event.lon.toString())
                .add("longitude",event.lat.toString())
                .add("timestamp",event.time.toString())
                .build()
        val request = Request.Builder()
                .url("https://httpbin.org/post")
                .post(formBody)
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }
}

