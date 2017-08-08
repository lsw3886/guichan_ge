package lsw.guichange.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lsw.guichange.fragment.BookmarkFragment;
import lsw.guichange.fragment.ListFragment;
import lsw.guichange.fragment.RecentFragment;
import lsw.guichange.fragment.SettingFragment;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    ListFragment listFragment;
    RecentFragment recentFragment;
    BookmarkFragment bookmarkFragment;
    SettingFragment settingFragment;


    public PagerAdapter(FragmentManager fm){

        super(fm);
        listFragment = ListFragment.newInstance();
        recentFragment = RecentFragment.newInstance();
        bookmarkFragment = BookmarkFragment.newInstance();
        settingFragment = SettingFragment.newInstance();

    }

    @Override
    public Fragment getItem(int position){
        switch(position){

            case 0:

                return  listFragment;


            case 1:

                return recentFragment;

            case 2:

                return bookmarkFragment;

            case 3:

                return settingFragment;

            default :
                return null;
        }
    }

    @Override
    public int getCount(){
        return 4;
    }

    @Override
    public int getItemPosition(Object item) {

        return POSITION_NONE;   // notifyDataSetChanged


    }
}
