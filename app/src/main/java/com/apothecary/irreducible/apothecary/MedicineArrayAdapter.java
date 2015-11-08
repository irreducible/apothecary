package com.apothecary.irreducible.apothecary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apothecary.irreducible.apothecary.models.Medicine;

/**
 * Created by amore on 11/8/15.
 */
public class MedicineArrayAdapter extends ArrayAdapter<Medicine> {
    public MedicineArrayAdapter(Context context, Medicine[] medicines) {
        super(context, 0, medicines);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicine medicine = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_medicine, parent, false);
        }
        TextView tvMedicineName = (TextView) convertView.findViewById(R.id.tvMedicineName);

        tvMedicineName.setText(medicine.getName());

        return convertView;

    }
}
