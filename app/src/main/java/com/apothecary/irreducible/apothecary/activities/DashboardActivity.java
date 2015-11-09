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

import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 100;
    private ListView lvMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        lvMedicines = (ListView) findViewById(R.id.lvMedicines);
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
        String barcode;
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                barcode = data.getExtras().getString("barcode");
                Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Receipt");
                query.whereEqualTo("barcode", barcode);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            ParseObject med = objects.get(0);
                            Toast.makeText(DashboardActivity.this, med.getString("name"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DashboardActivity.this, "Unable to find receipt", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    public void addPrescription(MenuItem item) {
    }
}
