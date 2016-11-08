package edu.oswego.permaculturemonitor;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


/**
 * Created by Yovi on 10/31/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Graphs", "Maps", "Settings"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                android.support.v4.app.Fragment fragmenttab1 = new android.support.v4.app.Fragment();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                android.support.v4.app.Fragment fragmenttab2 = new android.support.v4.app.Fragment();
                return fragmenttab2;

            // Open FragmentTab3.java
            case 2:
                android.support.v4.app.Fragment fragmenttab3 = new android.support.v4.app.Fragment();
                return fragmenttab3;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}