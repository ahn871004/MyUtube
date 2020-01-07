package kr.co.ajsoft.myutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import kr.co.ajsoft.myutube.Adapter.FgViewAdapter;
import kr.co.ajsoft.myutube.Fragment.KpopFragment;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    TabLayout tabLayout;

    ViewPager pager;

    FgViewAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar를 액션바로 대체하기
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav);
        navigationView.setItemIconTintList(null);

        drawerLayout=findViewById(R.id.layout_drawer);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        tabLayout=findViewById(R.id.layout_tab);


        pager=findViewById(R.id.pager);
        adapter=new FgViewAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        //TabLayout과 viewpager를 연동
        tabLayout.setupWithViewPager(pager);

        //***viewpager와 연동하게 되면
        //위에 직접 코드로 추가했던 Tab객체는 무시됨
        //대신 Viewpager에서 탭버튼 글씨를 설정

        //제목줄에 서브제목 설정하기
        getSupportActionBar().setSubtitle("K-POP");



        //탭변경 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportActionBar().setSubtitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //네비게이션뷰에 아이템선택 리스너 추가
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_aa:

                        break;
                    case R.id.menu_bb:

                        break;
                    case R.id.menu_cc:

                        break;

                }
                drawerLayout.closeDrawer(navigationView); //클릭되면 돌아가기

                return false;
            }
        });


    }//onCreate Method..

    //옵션메뉴 만들어주는 메소드


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option,menu);

        return super.onCreateOptionsMenu(menu);

        // onOptionsItemSelected(new On)
    }




}
