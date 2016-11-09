package edu.oswego.permaculturemonitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.astuetz.PagerSlidingTabStrip;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
    DBConnect connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database Connection

        connection = new DBConnect(); //&nbsp;//the class file
        DoQuery doQuery = new DoQuery();
        doQuery.execute("");

        // action bar toolbar
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setLogo(R.mipmap.logo);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setOnPageChangeListener(new OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                  Toast.makeText(MainActivity.this,
                  "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }


            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Toast.makeText(MainActivity.this, "---test---", Toast.LENGTH_SHORT).show();
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
               // Toast.makeText(MainActivity.this, "---TEST---", Toast.LENGTH_SHORT).show();

            }

        });
    }
    public class DoQuery extends AsyncTask<String,String,String> {
        String ret = "";
        @Override
        protected String doInBackground(String... params) {
            try{
                Connection con = connection.CONN();
                if(con == null){
                    //Error connecting to DataBase
                    Log.e("connection","Couldnt connect to to database");
                    ret = "Couldnt connect to to database.";
                } else {
                    String query = "select * from sensor";//just a test query
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()){
                        ret = "it worked!";
                    }
                }
            }catch (Exception ex){
                ret = "Exceptions";
            }
            return ret;
        }
    }
}

