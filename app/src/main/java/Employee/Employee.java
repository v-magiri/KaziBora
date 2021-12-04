package Employee;

public class Employee {
    String Name;
    String userID;
    String EmailAddress;
    String phoneNumber;

    public Employee(String name, String userID, String emailAddress, String phoneNumber) {
        Name = name;
        this.userID = userID;
        EmailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
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
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
