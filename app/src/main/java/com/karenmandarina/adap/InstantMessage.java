package com.karenmandarina.adap;

/**
 * Created by karenmandarina on 6/12/18.
 */

import java.util.Date;

class InstantMessage {
// This is the class that structures the data stored on Firebase Database

    private String message;
    //private String author;
    private Date sentTime;
    private String senderEmail;
    private String userType;
    // private recepientEmail;

    InstantMessage(String message, Date sentTime, String senderEmail, String userType) {
        this.message = message;
        // this.author = author;
        this.senderEmail = senderEmail;
        this.sentTime = sentTime;
        this.userType = userType;
    }

    public InstantMessage() {

    }

    String getMessage() {
        return message;
    }
    //String getAuthor() {
    //    return author;
    //}
    String getSenderEmail() {
        return senderEmail;
    }
    Date getSentTime() {
        return sentTime;
    }
    String getUserType() { return userType;}

}

