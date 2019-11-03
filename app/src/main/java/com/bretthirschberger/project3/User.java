package com.bretthirschberger.project3;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

final public class User {
    private String mEmail;
    private String mName;
    private String mPasswordHash;
    private String mDOB;
    private boolean mIsParent;

    public User(String email, String name, String password, String DOB, boolean isParent) {
        mEmail = email;
        mName = name;
        mPasswordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        mDOB = DOB;
        mIsParent = isParent;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getName() {
        return mName;
    }

    public String getPasswordHash() {
        return mPasswordHash;
    }

    public boolean isParent() {
        return mIsParent;
    }

    public String getDOB() {
        return mDOB;
    }

    @Override
    public String toString() {
        return "Email: "+mEmail+
                "\nName: "+mName+
                "\nPassword Hash: "+mPasswordHash+
                "\nDOB: "+mDOB+
                "\nIs Parent: "+mIsParent;
    }
}
