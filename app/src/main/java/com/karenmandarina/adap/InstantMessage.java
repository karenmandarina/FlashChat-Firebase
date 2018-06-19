package com.karenmandarina.adap;

/**
 * Created by karenmandarina on 6/12/18.
 */

import java.util.Date;

class InstantMessage {


    private String message;
    private String author;
    private Date sentTime;
    private String senderEmail;

    InstantMessage(String message, Date sentTime, String senderEmail) {
        this.message = message;
        // this.author = author;
        this.senderEmail = senderEmail;
        this.sentTime = sentTime;

    }

    public InstantMessage() {

    }

    String getMessage() {
        return message;
    }
    String getAuthor() {
        return author;
    }
    String getSenderEmail() {
        return senderEmail;
    }
    Date getSentTime() {
        return sentTime;
    }

}

