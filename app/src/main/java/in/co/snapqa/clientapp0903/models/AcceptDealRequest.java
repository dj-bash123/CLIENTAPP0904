package in.co.snapqa.clientapp0903.models;

/**
 * Created by OLA on 05/04/17.
 */

public class AcceptDealRequest {

    public String token;
    public String _id;

    public AcceptDealRequest() {
        this.token = token;
        this._id = _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
