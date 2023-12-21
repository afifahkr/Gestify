// ApiService.kt
import com.ibham.frontend.ui.LoginRequest
import com.ibham.frontend.ui.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    // Tambahkan endpoint lain jika diperlukan
}
