package nu.helmers.sandbox.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Random;

/**  */
@ManagedBean
@SessionScoped
public class NumberBean implements Serializable {

    private static final long MAXIMUM = 10;
    private static final long MINIMUM = 0;

    Integer randomNumber = null;
    Integer userNumber = null;

    String response = null;

    public NumberBean() {
        Random randomGenerator = new Random();
        randomNumber = new Integer(randomGenerator.nextInt(10));
    }

    public void setUserNumber(Integer user_number) {
        userNumber = user_number;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomNumber) == 0)) {
            return "Yay! You got it!";
        } else {
            return "Sorry, " + userNumber + " is incorrect.";
        }
    }

    public long getMaximum() {
        return (this.MAXIMUM);
    }

    public long getMinimum() {
        return (this.MINIMUM);
    }
}
