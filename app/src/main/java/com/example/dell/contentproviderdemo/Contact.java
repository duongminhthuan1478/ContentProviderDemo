package com.example.dell.contentproviderdemo;

/**
 * Created by Dell on 23-Jan-18.
 */

public class Contact {
    private String mName;
    private String mPhoneNumber;

    public Contact(String name, String phoneNumber) {

        mName = name;
        mPhoneNumber = phoneNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }


}
