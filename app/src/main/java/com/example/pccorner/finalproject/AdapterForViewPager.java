package com.example.pccorner.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AdapterForViewPager extends FragmentStatePagerAdapter {
    private int numberOfPages;
    public AdapterForViewPager(FragmentManager fm,int numberOfPages) {
        super(fm);
        this.numberOfPages=numberOfPages;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment f;
        if(i==0){
            f=new FragmentAddExtraLessons();
        }else if(i==1){
            f=new RecordExtraLessons();
        }else{
            f=new FragemntNotPaidExtraLessons();
        }

        return f;
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }
}
