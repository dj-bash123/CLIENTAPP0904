package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 13/04/17.
 */

public class ChangePasswordResponse {

    String message;

    public ChangePasswordResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
