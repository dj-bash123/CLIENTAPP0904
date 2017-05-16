package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 09/03/17.
 */

public class SignUpRequest {
    public String Name;
    public String Phone;
    public String Password;
    public String Email;
    public String Whatsapp;

    public SignUpRequest(String name, String phone, String password, String email, String whatsapp) {
        Name = name;
        Phone = phone;
        Password = password;
        Email = email;
        Whatsapp = whatsapp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWhatsapp() {
        return Whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        Whatsapp = whatsapp;
    }
}
