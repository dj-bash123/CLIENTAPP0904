package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 29/03/17.
 */

public class SendOTP {

    String Phone;

    public SendOTP(String phone) {
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
