package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 12/04/17.
 */

public class ForgotPasswordResponse {

    String message;

    public ForgotPasswordResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
