package com.apothecary.irreducible.apothecary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.apothecary.irreducible.apothecary.R;
import com.apothecary.irreducible.apothecary.models.PrescriptionItem;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by akulka2 on 11/7/15.
 */
public class PrescriptionItemAdapter extends ArrayAdapter<PrescriptionItem> {

    public PrescriptionItemAdapter(Context context,  List<PrescriptionItem> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private static class ViewHolder{
        TextView tvMedName;
        EditText etMedName;
        TextView tvMedQty;
        EditText etMedQty;
        TextView tvMedDoses;
        EditText etMedDoses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PrescriptionItem prescriptionItem = getItem(position);
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prescription_item,parent,false);
            viewHolder.tvMedName = (TextView)convertView.findViewById(R.id.tvMedName);
            viewHolder.tvMedQty = (TextView)convertView.findViewById(R.id.tvMedQty);
            viewHolder.tvMedDoses = (TextView) convertView.findViewById(R.id.tvMedDoses);
            viewHolder.etMedName = (EditText) convertView.findViewById(R.id.etMedicineName);
            viewHolder.etMedQty = (EditText) convertView.findViewById(R.id.etMedicineQty);
            viewHolder.etMedDoses = (EditText) convertView.findViewById(R.id.etDosePerDay);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.etMedName.setText(prescriptionItem.getName());
        viewHolder.etMedQty.setText(prescriptionItem.getQuantity());
        viewHolder.etMedDoses.setText(prescriptionItem.getDoses());

        return convertView;
    }
}
