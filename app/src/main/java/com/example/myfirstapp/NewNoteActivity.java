package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class NewNoteActivity extends AppCompatActivity {

    private EditText newNoteTextArea;
    private Button saveNewNoteButton;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        newNoteTextArea = (EditText) findViewById(R.id.new_note_textarea);
        saveNewNoteButton = (Button) findViewById(R.id.save_note_button);

        saveNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = newNoteTextArea.getText().toString();
                if (!TextUtils.isEmpty(note)) {
                    Long currentTime = System.currentTimeMillis() / 1000;
                    String timeStamp = currentTime.toString();

                    String currentUserID = firebaseAuth.getCurrentUser().getUid();

                    Map<String, Object> newNoteMap = new HashMap<>();
                    newNoteMap.put("userId", currentUserID);
                    newNoteMap.put("note", note);
                    newNoteMap.put("timeStamp", timeStamp);

                    db.collection("notes")
                            .add(newNoteMap)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(NewNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();

                                    navigateToMainPage();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    String error = e.getMessage();
                                    Toast.makeText(NewNoteActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    Toast.makeText(NewNoteActivity.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void navigateToMainPage() {
        Intent mainIntent = new Intent(NewNoteActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}