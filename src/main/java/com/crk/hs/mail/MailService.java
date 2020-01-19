package com.crk.hs.mail;

public interface MailService {
    public void sendSimpleMail(String to, String subject, String content);
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws Exception;
}
