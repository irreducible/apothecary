package com.apothecary.irreducible.apothecary.dialogs;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.apothecary.irreducible.apothecary.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by akulka2 on 11/8/15.
 */
public class AddPrescriptionDialog extends DialogFragment {

    private TextView tvMedName;
    private EditText etMedName;
    private TextView tvMedQty;
    private EditText etMedQty;
    private TextView tvMedDoses;
    private EditText etMedDoses;
    private TextView tvExpiryDate;
    private EditText etExpiryDate;
    private String userName;

    public AddPrescriptionDialog(){
    }

    public static AddPrescriptionDialog newInstance(String title, String userName) {
        AddPrescriptionDialog fragment = new AddPrescriptionDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("username",userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prescription_item, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        // Fetch arguments from bundle and set title
        setUpViews(view);
        String title = getArguments().getString("title");
        getDialog().setTitle(title);

//        userName = ParseUser.getCurrentUser().getUsername();
        userName = getArguments().getString("username");
        // getDialog().getWindow().setSoftInputMode(
        //        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                ((Button) view.findViewById(R.id.btnSaveMedicine)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.

                ParseObject prescription = new ParseObject("User_Medicines");

                prescription.put("userName", userName);
                prescription.put("name", etMedName.getText().toString());
                prescription.put("quantity", etMedQty.getText().toString());
                prescription.put("doses", etMedDoses.getText().toString());
//                    prescription.put("expiryDate",etExpiryDate.getText().toString());
                prescription.saveInBackground();

                dismiss();

            }
        });
        ((Button) view.findViewById(R.id.btnCancelMedicine)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, dismiss this dialog by clearing out the filters
                dismiss();

            }
        });
    }

    private void setUpViews(View view){
        tvMedName = (TextView) view.findViewById(R.id.tvMedName);
        etMedName = (EditText) view.findViewById(R.id.etMedicineName);
        tvMedQty = (TextView) view.findViewById(R.id.tvMedQty);
        etMedQty = (EditText) view.findViewById(R.id.etMedicineQty);
        tvMedDoses = (TextView) view.findViewById(R.id.tvMedDoses);
        etMedDoses = (EditText) view.findViewById(R.id.etDosePerDay);
       // tvExpiryDate = (TextView) view.findViewById(R.id.tvExpiryDate);
       // etExpiryDate = (EditText) view.findViewById(R.id.etExpiryDate);
    }

}
