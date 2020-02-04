package kr.co.ajsoft.myutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Collections;

import kr.co.ajsoft.myutube.Adapter.FgViewAdapter;
import kr.co.ajsoft.myutube.Fragment.KpopFragment;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    //SearchView searchView;



    TabLayout tabLayout;

    ViewPager pager;

    FgViewAdapter adapter;

    private long pressedTime=0;





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
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_aa:
//
//                        break;
//                    case R.id.menu_bb:
//
//                        break;
//                    case R.id.menu_cc:
//
//                        break;
//
//                }
//                drawerLayout.closeDrawer(navigationView); //클릭되면 돌아가기
//
//                return false;
//            }
//        });


    }//onCreate Method..

    //옵션메뉴 만들어주는 메소드




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.option,menu);
//
//        MenuItem item=menu.findItem(R.id.menu_search);
//        searchView=(SearchView)item.getActionView();
//
//        searchView.setQueryHint("검색");
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                Toast.makeText(MainActivity.this,query, Toast.LENGTH_SHORT).show();
//
//                searchView.setQuery("",true);
//
//                searchView.setIconified(true);
//                searchView.setBackgroundColor(Color.WHITE);
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//
//        // onOptionsItemSelected(new On)
//    }

    public  interface  OnBackPressedListener{
        public void onBack();
    }

    private OnBackPressedListener mBackListener;

    public void setOnBackPressedListener(OnBackPressedListener listener){
        mBackListener=listener;
    }

    //뒤로가기 버튼누르면 앱 종료 & 다른 Fragment에서 뒤로가기 누르면 main으로 오는 메소드


    @Override
    public void onBackPressed() {
        if(mBackListener!=null){
            mBackListener.onBack();
            Log.e("!!!","Listener is not null");


        }else{
            Log.e("!!!","Listener is null");
            if(pressedTime==0){
                Snackbar.make(findViewById(R.id.layout_drawer),"한 번 더 누르면 종료됩니다.",Snackbar.LENGTH_LONG).show();
                pressedTime=System.currentTimeMillis();

            }else{
                int seconds=(int)(System.currentTimeMillis()-pressedTime);
                if(seconds>2000){
                    Snackbar.make(findViewById(R.id.layout_drawer),"한 번 더 누르면 종료됩니다.",Snackbar.LENGTH_LONG).show();
                    pressedTime=0;

                }else{
                    super.onBackPressed();
                    Log.e("!!!","onBackPressed : finish, killProcess");
                    finish();
                    //android.os.Process.killProcess(android.os.Process.myPid());
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);

                }

            }

        }

    }
}
