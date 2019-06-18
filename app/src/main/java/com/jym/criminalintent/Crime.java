package com.jym.criminalintent;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //constructor
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    //私有成员设置和获得方法

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
        df.format(mDate);
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
