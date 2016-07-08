package com.example.baicheng.fragmenttest.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by baicheng on 2016/7/5.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime:mCrimes){
            if (crime.getID().equals(id)){
                return crime;
            }
        }
        return null;
    }

    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab==null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime){
        mCrimes.add(crime);
    }
}
