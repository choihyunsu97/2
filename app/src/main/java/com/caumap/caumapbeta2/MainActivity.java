package com.caumap.caumapbeta2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

  // Fragment000 Fragment000;
    float a =10.0f; float b = 10.0f;
    WifiManager wifiManager;
    private List<ScanResult> scanResults;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager  = (LocationManager)getSystemService(LOCATION_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionfineloca = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionfineloca == PackageManager.PERMISSION_DENIED){checkPermissions(permissions);
        }else{}

       // if(!wifi.isWifiEnabled()){
      //      wifi.setWifiEnabled(true);
     //   }
        //
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //GPS 설정화면으로 이동
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }





        //!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        /*
        //first, onclick 리스너 쓰고 onclick 메소드 오버라이드 함....
        //create instance of databasAccess class and open database  connection

       DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
       databaseAccess.open();

        // getting Location from BSSID

        //String n = BSSID
        //String location = databaseAccess.getLocation(n);
        // adress 값으로 location 값 구함.
        databaseAccess.close();

*/





        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("psd");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

       // Fragment000 = new Fragment000();


        LinearLayout container2 = findViewById(R.id.container2);

        final ImageDisplayView view = new ImageDisplayView(this);

        setimage(R.raw.b310f3,view);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
      //  getSupportFragmentManager().beginTransaction().add(R.id.container,Fragment000).commit();
        container2.addView(view, params);

        //setimage(R.raw.fullmap,view);

        CheckBox checkBox = (CheckBox) findViewById(R.id.test) ;
        Button backButton = findViewById(R.id.backkey);
        backButton.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){onBackPressed();}
        });
        checkBox.setOnClickListener(new CheckBox.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    setimage(R.raw.fullmap,view);
                    view.redraw();

                    wifiManager.setWifiEnabled(true);

                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                    getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);
                    boolean success = wifiManager.startScan();
                    if (!success) {
                        // scan failure handling
                        scanFailure();
                    }


                } else {
                    setimage(R.raw.b310f3,view);
                    view.redraw();
                    WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                    wifi.setWifiEnabled(false);
                }
            }
        }) ;

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this,"위치ㅣ가",Toast.LENGTH_LONG).show();
            Toolbar toolbar = findViewById(R.id.toolbar);

            toolbar.setTitle("psdsdadasdadasd");


        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.Bd101) {
            Toast.makeText(this,"위치ㅣ가",Toast.LENGTH_LONG).show();

        } else if (id == R.id.Bd102) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    @Override
    public void onFragmentSelected(int position, Bundle bundle) {


        getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment000).commit();

    }*/

public void setimage(int id,ImageDisplayView view){
    Resources res = getResources();
    Bitmap bitmap = BitmapFactory.decodeResource(res,id);
    view.setImageData(bitmap);
    view.mMatrix.reset();
}
    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for (int i = 0; i < permissions.length; i++) {
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " 권한 있음.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, curPermission + " 권한 없음.", Toast.LENGTH_LONG).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " 권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        ActivityCompat.requestPermissions(this, targets, 101);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫번째 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "첫번째 권한 거부됨.", Toast.LENGTH_LONG).show();
                }

                return;
            }
        }
    }


    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            boolean success = intent.getBooleanExtra(
                    WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
                Log.i("ssid[]", "success");
                scanResults = wifiManager.getScanResults();
                for(int i = 0; i < scanResults.size(); i++) {
                    Log.i("ssid[" + i + "]", String.format("SSID is %s,level is %3d",scanResults.get(i).BSSID,scanResults.get(i).level));
                }
            } else {
                // scan failure handling
                scanFailure();
                Log.i("ssid[]", "fail");
            }
        }
    };

    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();

    }

    private void scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        List<ScanResult> results = wifiManager.getScanResults();
    }
}
