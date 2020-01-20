package kr.co.ajsoft.myutube.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kr.co.ajsoft.myutube.Fragment.GameFragment;
import kr.co.ajsoft.myutube.Fragment.PetFragment;
import kr.co.ajsoft.myutube.Fragment.ComicFragment;
import kr.co.ajsoft.myutube.Fragment.KpopFragment;
import kr.co.ajsoft.myutube.Fragment.KstarFragment;
import kr.co.ajsoft.myutube.Fragment.MusicFragment;
import kr.co.ajsoft.myutube.Fragment.TvFragment;
import kr.co.ajsoft.myutube.Fragment.MukbangFragment;
import kr.co.ajsoft.myutube.Fragment.NewsFragment;

public class FgViewAdapter extends FragmentPagerAdapter {


    Fragment[] fragments=new Fragment[9];
    String[] pageTitles=new String[]{"K-POP","TV","Comic","K Star","Game","Mukbang","News","Pet","Music"};


    public FgViewAdapter(FragmentManager fm) {
        super(fm);

        fragments[0]=new KpopFragment();
        fragments[1]=new TvFragment();
        fragments[2]=new ComicFragment();
        fragments[3]=new KstarFragment();
        fragments[4]=new GameFragment();
        fragments[5]=new MukbangFragment();
        fragments[6]=new NewsFragment();
        fragments[7]=new PetFragment();
        fragments[8]=new MusicFragment();
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
