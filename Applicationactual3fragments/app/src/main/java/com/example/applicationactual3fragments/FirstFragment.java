package com.example.applicationactual3fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import com.example.applicationactual3fragments.databinding.FragmentFirstBinding;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.net.URLEncoder;


public class FirstFragment extends Fragment {

    private EditText editTextName, editTextDepartment, editTextCollege, editTextDateOfBirth, editTextPhone;
    private Button buttonSubmit, buttonContact;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Using view binding for the fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        editTextName = binding.editTextName;
        editTextPhone = binding.editTextPhone;
        editTextDepartment = binding.editTextDepartment;
        editTextCollege = binding.editTextCollege;
        editTextDateOfBirth = binding.editTextDateOfBirth;
        buttonSubmit = binding.buttonSubmit;
        buttonContact = binding.buttonContact;




        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactOptionsDialog();
            }
        });



        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String department = editTextDepartment.getText().toString();
                String college = editTextCollege.getText().toString();
                String dateOfBirth = editTextDateOfBirth.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("phone", phone);
                bundle.putString("department", department);
                bundle.putString("college", college);
                bundle.putString("dateOfBirth", dateOfBirth);

                // Use MainActivity to handle fragment change
                ((MainActivity)getActivity()).goToSecondFragment(bundle);
            }
        });

        return view;
    }

    private void showContactOptionsDialog() {
        String[] options = {"WhatsApp", "Phone", "Website"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Contact via")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // WhatsApp
                                openWhatsApp();
                                break;
                            case 1: // Phone
                                openPhoneDialer();
                                break;
                            case 2: // Website
                                openWebsite();
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void openWhatsApp() {
        try {
            String phoneNumberWithCountryCode = "+918790166779";
            String message = "Hello!";
            getActivity().startActivity(
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumberWithCountryCode + "&text=" + URLEncoder.encode(message, "UTF-8")))
            );
        } catch (Exception e) {
            Toast.makeText(getActivity(), "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openPhoneDialer() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+918790166779"));
        startActivity(intent);
    }

    private void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=oHg5SJYRHA0"));
        startActivity(intent);
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Set day of month, month and year value in the edit text
                        editTextDateOfBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
