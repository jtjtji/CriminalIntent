package com.jym.criminalintent;

import android.support.v4.app.Fragment;

/**
 * @author jym
 */
public class CrimeListActivity extends BaseFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
