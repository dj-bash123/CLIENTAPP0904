package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by OLA on 10/03/17.
 */

public class AuthRequest {

    @SerializedName("token")
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
