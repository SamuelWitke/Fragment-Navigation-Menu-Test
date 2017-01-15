package com.example.sam.PartyGames.navFragments;


import java.util.Collections;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.example.sam.PartyGames.DataHolders.NameListHolder;
import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.SettingsFragments.RuleSettings;
import com.example.sam.PartyGames.adapters.MyFragmentPagerAdapter;
import com.example.sam.PartyGames.gameFragments.Catagories;
import com.example.sam.PartyGames.gameFragments.Chicks;
import com.example.sam.PartyGames.gameFragments.Date;
import com.example.sam.PartyGames.gameFragments.Guys;
import com.example.sam.PartyGames.gameFragments.Heaven;
import com.example.sam.PartyGames.gameFragments.Me;
import com.example.sam.PartyGames.gameFragments.MostLikely;
import com.example.sam.PartyGames.gameFragments.Rhyme;
import com.example.sam.PartyGames.gameFragments.Rule;
import com.example.sam.PartyGames.gameFragments.TruthOrDare;
import com.example.sam.PartyGames.gameFragments.Waterfall;
import com.example.sam.PartyGames.gameFragments.NeverHaveIEver;
import com.example.sam.PartyGames.gameFragments.Floor;
import com.example.sam.PartyGames.gameFragments.You;

public class KingsGame extends Fragment implements OnTabChangeListener,
        OnPageChangeListener {

    private TabHost tabHost;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myViewPagerAdapter;
    private View v;
    static NameListHolder nameListHolder;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tab_viewpager_layout, container, false);
        MainActivity.myFab.setVisibility(View.GONE);

        nameListHolder= new NameListHolder(getContext());

        // init tabhost
        initializeTabHost(savedInstanceState);

        // init ViewPager
        initializeViewPager();

        return v;
    }
    /*
    * Fake Host For Tabbed Names in initializeTabHost
    class FakeContent implements TabContentFactory {
        private final Context mContext;

        public FakeContent(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }
     */

    private void initializeViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();

    for(int i=0;i<4;i++) {
        fragments.add(new Waterfall());
        fragments.add(new NeverHaveIEver());
        fragments.add(new Date());
        fragments.add(new Floor());
        fragments.add(new Me());
        fragments.add(new You());
        fragments.add(new Rhyme());
        fragments.add(new Catagories());
        fragments.add(new MostLikely());
        fragments.add(new TruthOrDare());
        fragments.add(new Guys());
        fragments.add(new Chicks());
        fragments.add(new Heaven());
        fragments.add(new Rule());
    }
        Collections.shuffle(fragments);
        Collections.shuffle(fragments);
        Collections.shuffle(fragments);

        myViewPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), fragments);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        viewPager.setAdapter(this.myViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);

    }

    private void initializeTabHost(Bundle args) {

        tabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        tabHost.setup();

        /*
        * Set up Names for each tabbed activity
        for (int i = 1; i <= 6; i++) {
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec("Tab " + i);
            tabSpec.setIndicator("Tab " + i);
            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }
        */
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) v
                .findViewById(R.id.hScrollView);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }


    public static String getName(){
        String s= nameListHolder.getName();
        if(s != null)
            return s;
        else
            return "";
    }
}
