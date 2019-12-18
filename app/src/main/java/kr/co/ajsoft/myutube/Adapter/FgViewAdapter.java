package kr.co.ajsoft.myutube.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kr.co.ajsoft.myutube.Fragment.CarFragment;
import kr.co.ajsoft.myutube.Fragment.ComicFragment;
import kr.co.ajsoft.myutube.Fragment.KpopFragment;
import kr.co.ajsoft.myutube.Fragment.PetFragment;

public class FgViewAdapter extends FragmentPagerAdapter {


    Fragment[] fragments=new Fragment[4];
    String[] pageTitles=new String[]{"K-POP","Pet","Comic","Car"};


    public FgViewAdapter(FragmentManager fm) {
        super(fm);

        fragments[0]=new KpopFragment();
        fragments[1]=new PetFragment();
        fragments[2]=new ComicFragment();
        fragments[3]=new CarFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return pageTitles[position];
    }
}
