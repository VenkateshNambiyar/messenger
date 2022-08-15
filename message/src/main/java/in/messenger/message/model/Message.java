package in.messenger.message.model;

import java.sql.Timestamp;

/**
 * Its declare a variables as private.Its provide public get and set methods to access and update a private variable
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class Message {
    private String messageDetails;
    private Long profile_Id;
    private Timestamp messageTime;
    private Long message_id;
    private long user_id;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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