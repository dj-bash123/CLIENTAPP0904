package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 12/04/17.
 */

public class PaymentDetailsRequest {
    String accountNumber;
    String ifscCode;
    String panNumber;
    String token;

    public PaymentDetailsRequest(String accountNumber, String ifscCode, String panNumber, String token) {
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.panNumber = panNumber;
        this.token = token;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
