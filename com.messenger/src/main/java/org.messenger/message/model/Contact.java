package org.messenger.message.model;


/**
 * Contact class is an Encapsulation. Its declare a variables as private.
 *  * Its provide public get and set methods to access and update the value of a private variable
 */
public class Contact {
    private Long mobileNumber;
    private String personName;
    private Long contactId;
    private Long retrieveUserId;
    private Long retrieveContactId;
    private Long retrieveMobileNumber;
    private String retrievePersonName;
    private String accessIdName;
    private static Contact contact;

    /**
     * Contact()  creating a private Constructor to avoid multiple object creations for a class
     */
    private Contact() {
        super();
    }

    /**
     * getInstance() method to check the object create instance for a class
     */
    public static Contact getInstance() {
        if (contact == null) {
            contact = new Contact();
        }
        return contact;
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

    public void setAccessIdName(String accessIdName) {
        this.accessIdName = accessIdName;
    }

    public String getAccessIdName() {
        return accessIdName;
    }

    public String toString() {
        return contactId+ "\t\t\t" + retrieveMobileNumber+ "\t\t"+ retrievePersonName;
    }
}