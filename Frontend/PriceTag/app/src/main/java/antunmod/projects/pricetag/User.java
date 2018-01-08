package antunmod.projects.pricetag;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by antun on 1/6/2018.
 */

public class User  implements Serializable {

    private long userId;
    private String name;
    private String password;
    private String email;
    private String signupDate;
    private int points;
    private int userType;

    public User(long userId, String name, String password, String email, String signupDate, int points, int userType) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.signupDate = signupDate;
        this.points = points;
        this.userType = userType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(String signupDate) {
        this.signupDate = signupDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
