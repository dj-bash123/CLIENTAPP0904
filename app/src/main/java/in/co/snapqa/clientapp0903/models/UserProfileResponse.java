package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by OLA on 07/04/17.
 */

public class UserProfileResponse {

    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("Phone")
    @Expose
    String Phone;

    @SerializedName("Email")
    @Expose
    String Email;

    @SerializedName("rating")
    @Expose
    int rating;

    @SerializedName("Points")
    @Expose
    int Points;

    @SerializedName("Name")
    @Expose
    String Name;

    @SerializedName("Specialization")
    @Expose
    List<String> Specialization;

    @SerializedName("bankDetails")
    @Expose
    BankDetailModel bankDetails;

    public UserProfileResponse(String _id, String phone, String email, int rating, int points, String name, List<String> specialization, BankDetailModel bankDetails) {
        this._id = _id;
        Phone = phone;
        Email = email;
        this.rating = rating;
        Points = points;
        Name = name;
        Specialization = specialization;
        this.bankDetails = bankDetails;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(List<String> specialization) {
        Specialization = specialization;
    }

    public BankDetailModel getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetailModel bankDetails) {
        this.bankDetails = bankDetails;
    }
}
