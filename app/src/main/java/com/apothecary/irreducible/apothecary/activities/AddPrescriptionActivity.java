package com.apothecary.irreducible.apothecary.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.apothecary.irreducible.apothecary.R;
import com.apothecary.irreducible.apothecary.adapters.PrescriptionItemAdapter;
import com.apothecary.irreducible.apothecary.models.PrescriptionItem;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class AddPrescriptionActivity extends AppCompatActivity {

    private ListView lvPrescriptionList;
    private ArrayList<PrescriptionItem> items;
    private PrescriptionItemAdapter prescriptionItemAdapter;
    private String userName;
    private List<ParseObject> parseObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        setupViews();
        items = new ArrayList<PrescriptionItem>();
        prescriptionItemAdapter = new PrescriptionItemAdapter(this,items);
        lvPrescriptionList.setAdapter(prescriptionItemAdapter);
        Intent i = getIntent();
        parseObjects = (List<ParseObject>)i.getSerializableExtra("prescriptionItems");
        userName = i.getStringExtra("userName");
        populateViews();
    }

    private void setupViews(){
        lvPrescriptionList = (ListView) findViewById(R.id.lvPrescriptionList);
    }

    private void populateViews(){
        for(ParseObject parseObject:parseObjects){
            PrescriptionItem item = new PrescriptionItem();
            item.setName(parseObject.getString("name"));
            item.setQuantity(parseObject.getString("quantity"));
            item.setDoses(parseObject.getString("doses"));
            //item.setExpiryDate(parseObject.getString("expiryDate"));
            items.add(item);
            prescriptionItemAdapter.notifyDataSetChanged();
        }
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

    public void savePrescription(View view) {

        Log.i("INFO", "Saving Result to User Medicines");
        for(int i=0;i<items.size();i++) {
            View v = lvPrescriptionList.getChildAt(i);
            ParseObject prescription = new ParseObject("User_Medicines");
            EditText etMedName = (EditText) v.findViewById(R.id.etMedItemName);
            EditText etMedQty = (EditText) v.findViewById(R.id.etMedItemQty);
            EditText etMedDoses = (EditText) v.findViewById(R.id.etMedItemDosePerDay);
            //EditText etExpiryDate = (EditText) v.findViewById(R.id.etExpiryDate);
            prescription.put("userName", userName);
            prescription.put("name", etMedName.getText().toString());
            prescription.put("quantity", etMedQty.getText().toString());
            prescription.put("doses", etMedDoses.getText().toString());
            //prescription.put("expiryDate",etExpiryDate.getText().toString());
            prescription.saveInBackground();
        }

        finish();
    }
}
