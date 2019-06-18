package com.jym.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends BaseFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
