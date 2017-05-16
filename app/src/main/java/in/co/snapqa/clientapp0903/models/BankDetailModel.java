package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by OLA on 13/04/17.
 */

public class BankDetailModel {

    @SerializedName("accNo")
    @Expose
    String accNo;

    @SerializedName("panNumber")
    @Expose
    String panNumber;

    @SerializedName("iFSC")
    @Expose
    String iFSC;

    public BankDetailModel(String accNo, String panNumber, String iFSC) {
        this.accNo = accNo;
        this.panNumber = panNumber;
        this.iFSC = iFSC;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getiFSC() {
        return iFSC;
    }

    public void setiFSC(String iFSC) {
        this.iFSC = iFSC;
    }
}
