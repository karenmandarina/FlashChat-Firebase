package com.karenmandarina.flashchatnewfirebase;

/**
 * Created by karenmandarina on 6/12/18.
 */


class InstantMessage {

    private String message;
    private String author;

    InstantMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public InstantMessage() {



    }

    String getMessage() {
        return message;
    }

    String getAuthor() {
        return author;
    }
}
