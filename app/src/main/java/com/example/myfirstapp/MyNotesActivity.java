package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MyNotesActivity extends AppCompatActivity {

    private TextView myNotesTextArea;
    private static final String TAG = "MyActivity";

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        myNotesTextArea = (TextView) findViewById(R.id.my_notes_textarea);
        final String[] myNotesText = {myNotesTextArea.toString()};
                String currentUserID = firebaseAuth.getCurrentUser().getUid();
                    db.collection("notes")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                          //                  myNotesText[0] = currentUserID + " => " + document.getData().get("note");
                          //                   Log.d(TAG, currentUserID + " => " + document.getData().get("note")
                          //                          );
                                            System.out.println(currentUserID);
                                        }
                                    } else {
                                        myNotesText[0] = String.valueOf(Log.w(TAG, "Error getting notes", task.getException()));
                                    }
                                }
                            });
            }
}