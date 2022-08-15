package in.messenger.message.model;

/**
 * Its declare a variables as private. Its provide public get and set methods to access and update to a private variable
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class Contact extends Message {
    private Long mobileNumber;
    private String personName;

    private String username;
    private Long contactId;
    private Long retrieveUserId;
    private Long retrieveContactId;
    private Long retrieveMobileNumber;
    private String retrievePersonName;
    private String accessIdName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setRetrieveContactId(Long retrieveContactId) {
        this.retrieveContactId = retrieveContactId;
    }

    public void setRetrieveUserId(Long retrieveUserId) {
        this.retrieveUserId = retrieveUserId;
    }

    public Long getRetrieveUserId() {
        return retrieveUserId;
    }

    public Long getRetrieveContactId() {
        return retrieveContactId;
    }

    public void setRetrieveMobileNumber(Long retrieveMobileNumber) {
        this.retrieveMobileNumber = retrieveMobileNumber;
    }

    public void setRetrievePersonName(String retrievePersonName) {
        this.retrievePersonName = retrievePersonName;
    }

    public String getAccessIdName() {
        return accessIdName;
    }

    public String toString() {
        return contactId+ "\t\t\t" + retrieveMobileNumber+ "\t\t"+ retrievePersonName;
    }
}