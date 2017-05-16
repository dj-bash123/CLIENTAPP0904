package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 12/04/17.
 */

public class ForgotPasswordRequest {

    String Phone;
    String Email;

    public ForgotPasswordRequest(String phone, String email) {
        Phone = phone;
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
