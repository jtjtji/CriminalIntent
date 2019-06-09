package com.jym.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity{
    private static final String EXTRA_CRIME_ID="com.jym.criminalIntent_id";
    @Override
    protected Fragment createFragment() {
        UUID crimeId=(UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
    public static Intent newIntent(Context packagecontext, UUID crimeId){
        Intent intent=new Intent(packagecontext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
}

