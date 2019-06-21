package com.jym.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jym.criminalintent.database.CrimeBaseHelper;
import com.jym.criminalintent.database.CrimeCursorWrapper;
import com.jym.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author jym
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * 私有构造函数
     */
    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursorWrapper = queryCrimes(CrimeDbSchema.CrimeTable.Cols.UUID + "=?", new String[]{id.toString()});
        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        } finally {
            cursorWrapper.close();
        }
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues sValues = new ContentValues();
        sValues.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        sValues.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        sValues.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        sValues.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 10);
        return sValues;
    }

    public void updateCrime(Crime crime) {
        String uuidSting = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values, CrimeDbSchema.CrimeTable.Cols.UUID + "=?", new String[]{uuidSting});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME, null, whereClause, whereArgs, null, null, null
        );
        return new CrimeCursorWrapper(cursor);
    }
}
