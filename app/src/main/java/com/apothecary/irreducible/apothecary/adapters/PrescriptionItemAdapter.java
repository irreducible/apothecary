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
        TextView tvExpiryDate;
        EditText etExpiryDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PrescriptionItem prescriptionItem = getItem(position);
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medicine_item,parent,false);
            viewHolder.tvMedName = (TextView)convertView.findViewById(R.id.tvMedItemName);
            viewHolder.tvMedQty = (TextView)convertView.findViewById(R.id.tvMedItemQty);
            viewHolder.tvMedDoses = (TextView) convertView.findViewById(R.id.tvMedItemQty);
            viewHolder.etMedName = (EditText) convertView.findViewById(R.id.etMedItemName);
            viewHolder.etMedQty = (EditText) convertView.findViewById(R.id.etMedItemQty);
            viewHolder.etMedDoses = (EditText) convertView.findViewById(R.id.etMedItemDosePerDay);
           // viewHolder.tvExpiryDate = (TextView) convertView.findViewById(R.id.tvExpiryDate);
           // viewHolder.etExpiryDate = (EditText) convertView.findViewById(R.id.etExpiryDate);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.etMedName.setText(prescriptionItem.getName());
        viewHolder.etMedQty.setText(prescriptionItem.getQuantity());
        viewHolder.etMedDoses.setText(prescriptionItem.getDoses());
       // viewHolder.etExpiryDate.setText(prescriptionItem.getExpiryDate());
        return convertView;
    }
}
