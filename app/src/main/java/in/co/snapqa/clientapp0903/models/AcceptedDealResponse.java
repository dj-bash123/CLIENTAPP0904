package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by OLA on 05/04/17.
 */

public class AcceptedDealResponse {

    @SerializedName("message")
    @Expose
    public String message;

    public AcceptedDealResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
