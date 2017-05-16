package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by OLA on 10/03/17.
 */

public class NewDealFragmentResponse {

    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("pointsOffered")
    @Expose
    int pointsOffered;

    @SerializedName("subjectName")
    @Expose
    String subjectName;

    @SerializedName("clientName")
    @Expose
    String clientName;

    @SerializedName("dealDate")
    @Expose
    Date dealDate;

    @SerializedName("adminName")
    @Expose
    String adminName;

    @SerializedName("__v")
    @Expose
    int  __v;

    @SerializedName("bookedStatus")
    @Expose
    List bookedStatus;

    @SerializedName("isActive")
    @Expose
    Boolean isActive;

    @SerializedName("feedback")
    @Expose
    List feedback;

    @SerializedName("ratingArray")
    @Expose
    List ratingArray;

    @SerializedName("createdAt")
    @Expose
    Date createdAt;

    @SerializedName("dealType")
    @Expose
    String dealType;


    @SerializedName("timeTo")
    @Expose
    String timeTo;

    @SerializedName("timeFrom")
    @Expose
    String timeFrom;

    public NewDealFragmentResponse(String _id, int pointsOffered, String subjectName, String clientName, Date dealDate, String adminName, int __v, List bookedStatus, Boolean isActive, List feedback, List ratingArray, Date createdAt, String dealType, String timeTo, String timeFrom) {
        this._id = _id;
        this.pointsOffered = pointsOffered;
        this.subjectName = subjectName;
        this.clientName = clientName;
        this.dealDate = dealDate;
        this.adminName = adminName;
        this.__v = __v;
        this.bookedStatus = bookedStatus;
        this.isActive = isActive;
        this.feedback = feedback;
        this.ratingArray = ratingArray;
        this.createdAt = createdAt;
        this.dealType = dealType;
        this.timeTo = timeTo;
        this.timeFrom = timeFrom;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPointsOffered() {
        return pointsOffered;
    }

    public void setPointsOffered(int pointsOffered) {
        this.pointsOffered = pointsOffered;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public List getBookedStatus() {
        return bookedStatus;
    }

    public void setBookedStatus(List bookedStatus) {
        this.bookedStatus = bookedStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List getFeedback() {
        return feedback;
    }

    public void setFeedback(List feedback) {
        this.feedback = feedback;
    }

    public List getRatingArray() {
        return ratingArray;
    }

    public void setRatingArray(List ratingArray) {
        this.ratingArray = ratingArray;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }
}
