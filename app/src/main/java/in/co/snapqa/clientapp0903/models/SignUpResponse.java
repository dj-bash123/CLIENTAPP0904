package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by OLA on 09/03/17.
 */

public class SignUpResponse {
    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("token")
    @Expose
    public String token;

    public String getToken() {
        return token;
    }
}
