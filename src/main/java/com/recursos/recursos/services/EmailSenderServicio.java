package com.recursos.recursos.services;

public interface EmailSenderServicio {

    void sendEmail(String toUser, String subject, StringBuffer message);


}