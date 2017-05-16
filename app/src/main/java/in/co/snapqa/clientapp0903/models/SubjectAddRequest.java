package in.co.snapqa.clientapp0903.models;

import java.util.ArrayList;

/**
 * Created by OLA on 24/03/17.
 */

public class SubjectAddRequest {

    public String token;
    public ArrayList<String> subjects;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }
}
