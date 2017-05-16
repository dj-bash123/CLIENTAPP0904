package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 13/04/17.
 */

public class ChangePasswordRequest {
    String password;
    String changePassword;

    public ChangePasswordRequest(String password, String changePassword) {
        this.password = password;
        this.changePassword = changePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }
}
