package com.example.applicationactual3email;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private static final int PICK_ATTACHMENT_REQUEST = 1;

    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextMessage;
    private Uri attachmentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTo = findViewById(R.id.editTextTo);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextMessage = findViewById(R.id.editTextMessage);
        Button buttonAttachment = findViewById(R.id.buttonAttachment);
        Button buttonSend = findViewById(R.id.buttonSend);

        buttonAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickAttachment();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void pickAttachment() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), PICK_ATTACHMENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_ATTACHMENT_REQUEST && resultCode == RESULT_OK && data != null) {
            attachmentUri = data.getData();
        }
    }

    protected void sendEmail() {
        String recipientList = editTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (attachmentUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, attachmentUri);
        }

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
