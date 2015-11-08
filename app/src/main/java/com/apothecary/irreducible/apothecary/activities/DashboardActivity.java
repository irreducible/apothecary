package com.apothecary.irreducible.apothecary.activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apothecary.irreducible.apothecary.R;

public class DashboardActivity extends AppCompatActivity {
    private String receiptNumber;
    private final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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

    public void scan(MenuItem item) {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                receiptNumber = data.getExtras().getString("barcode");
                Toast.makeText(this, receiptNumber, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void searchNearby(MenuItem item) {
        LocationManager locMgr  = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location recentLoc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double lat = recentLoc.getLatitude();
            double lon = recentLoc.getLongitude();
            String geoURI = String.format("geo:%f,%f?q=pharmacy", lat, lon);
            Uri geo = Uri.parse(geoURI);
            Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
            startActivity(geoMap);
        }
        catch (SecurityException e) {
            Toast.makeText(this, "Apothecary needs permission to show nearby pharmacies", Toast.LENGTH_SHORT).show();
        }
    }
}