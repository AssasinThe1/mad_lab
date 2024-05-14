package com.example.applicationactual3fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
//import button and textview
import android.widget.Button;
import android.widget.TextView;
import com.example.applicationactual3fragments.databinding.FragmentSecondBinding;

import android.widget.Toast;

public class SecondFragment extends Fragment {

    private TextView textViewName, textViewPhone, textViewDepartment, textViewCollege, textViewDateOfBirth;
    private Button buttonEdit, buttonConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        textViewName = view.findViewById(R.id.textViewName);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewDepartment = view.findViewById(R.id.textViewDepartment);
        textViewCollege = view.findViewById(R.id.textViewCollege);
        textViewDateOfBirth = view.findViewById(R.id.textViewDateOfBirth);
        buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonConfirm = view.findViewById(R.id.buttonConfirm);

        Bundle bundle = getArguments();
        if (bundle != null) {
            textViewName.setText("Name: " + bundle.getString("name"));
//            phone number
            textViewPhone.setText("Phone Number: " + bundle.getString("phone"));
            textViewDepartment.setText("Department: " + bundle.getString("department"));
            textViewCollege.setText("College: " + bundle.getString("college"));
            textViewDateOfBirth.setText("Date of Birth: " + bundle.getString("dateOfBirth"));
        }

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous fragment to allow editing
                getFragmentManager().popBackStack();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thank you", Toast.LENGTH_SHORT).show();
                getActivity().finish(); // This will close the current activity
            }
        });

        return view;
    }
}
