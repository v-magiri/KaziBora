package Employer;

public class Employer {
    String Name;
    String userID;
    String EmailAddress;
    String PhoneNumber;

    public Employer(String name, String userID, String emailAddress, String phoneNumber) {
        Name = name;
        this.userID = userID;
        EmailAddress = emailAddress;
        PhoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
