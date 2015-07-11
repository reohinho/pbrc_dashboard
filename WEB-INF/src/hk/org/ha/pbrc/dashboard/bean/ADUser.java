package hk.org.ha.pbrc.dashboard.bean;

public class ADUser {
    
    private String username;
    private String givenName;
    private String lastName;
    private String displayName;
    private String telephone;
    private String email;
    private boolean existsInPBRC;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setExistsInPBRC(boolean existsInPBRC) {
        this.existsInPBRC = existsInPBRC;
    }

    public boolean isExistsInPBRC() {
        return existsInPBRC;
    }
}
