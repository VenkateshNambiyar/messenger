package org.messenger.message.model;


import java.sql.Timestamp;


/**
 * Message class is an Encapsulation. Its declare a variables as private.
 * Its provide public get and set methods to access and update the value of a private variable
 */
public class Message {
    private String messageDetails;
    private Long profile_Id;
    private Timestamp messageTime;
    private Long message_id;
    private static Message message;

    /**
     * Message()  creating a private Constructor to avoid multiple object creations for a class
     */
    private Message() {
        super();
    }

    /**
     * getInstance() method to check the object create instance for a class
     */
    public static Message getInstance() {
        if (message == null) {
            message = new Message();
        }
        return message;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public Long getProfile_Id() {
        return profile_Id;
    }

    public void setProfile_Id(Long profile_Id) {
        this.profile_Id = profile_Id;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String toString() {
        return messageTime+ "\t\t" + messageDetails;
    }
}