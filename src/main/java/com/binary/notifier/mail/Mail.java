package com.binary.notifier.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Mail {

    private String address;
    private String subject;
    private String text;
    private String attachmentContentType;
    private String attachmentFileName;
    private byte[] attachmentBytes;

}
