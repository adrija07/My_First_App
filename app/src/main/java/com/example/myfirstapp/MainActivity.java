package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private Button atlantaMapButton;
    private Button discoverAtlantaButton;
    private Button newNoteButton;
    private Button signOutButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Welcome to Atlanta!");

        atlantaMapButton = findViewById(R.id.atlanta_map_button);
        atlantaMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LocationMapsActivity.class));
            }
        });

        discoverAtlantaButton = findViewById(R.id.discover_atlanta_button);
        discoverAtlantaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String url = "https://discoveratlanta.com";
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //startActivity(browserIntent);
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });

        newNoteButton = findViewById(R.id.new_note_button);
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newNoteIntent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(newNoteIntent);
                finish();
            }
        });

        signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

    private void signOut() {
        firebaseAuth.signOut();
        goToLoginPage();
    }

    private void goToLoginPage() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}