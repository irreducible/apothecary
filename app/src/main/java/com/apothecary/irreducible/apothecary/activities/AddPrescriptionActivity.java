package com.apothecary.irreducible.apothecary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.apothecary.irreducible.apothecary.R;
import com.apothecary.irreducible.apothecary.adapters.PrescriptionItemAdapter;
import com.apothecary.irreducible.apothecary.models.PrescriptionItem;

import java.util.ArrayList;

public class AddPrescriptionActivity extends AppCompatActivity {

    private ListView lvPrescriptionList;
    private ArrayList<PrescriptionItem> items;
    private PrescriptionItemAdapter prescriptionItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        setupViews();
        items = new ArrayList<PrescriptionItem>();
        prescriptionItemAdapter = new PrescriptionItemAdapter(this,items);
        lvPrescriptionList.setAdapter(prescriptionItemAdapter);
    }

    private void setupViews(){
        lvPrescriptionList = (ListView) findViewById(R.id.lvPrescriptionList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_prescription, menu);
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

    public void addPrescriptionItem(View view) {
        PrescriptionItem item = new PrescriptionItem();
        item.setName("");
        item.setQuantity("");
        item.setDoses("");
        item.setExpiration("");
        items.add(item);
        prescriptionItemAdapter.notifyDataSetChanged();
    }
}
