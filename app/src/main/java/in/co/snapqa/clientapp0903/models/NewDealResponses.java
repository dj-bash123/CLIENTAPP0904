package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLA on 25/03/17.
 */

public class NewDealResponses {

    @SerializedName("responses")
    @Expose
    List<NewDealFragmentResponse> responses;

    @SerializedName("message")
    @Expose
    String message;

    public NewDealResponses(List<NewDealFragmentResponse> responses, String message) {
        this.responses = responses;
        this.message = message;
    }

    public List<NewDealFragmentResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<NewDealFragmentResponse> responses) {
        this.responses = responses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
