package com.example.tp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // inisialisation du instance

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //
        EditText emailEditText = findViewById(R.id.editText_email);
        EditText passwordEditText = findViewById(R.id.editText_password);
        Button loginButton=findViewById(R.id.buttonConnection);
        Button createAccountButton=findViewById(R.id.buttonCreeationcompt);

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            // Ajoutez ici les contrôles de saisie nécessaires
            // Utilisez le service d'authentification Firebase pour la connexion
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // L'utilisateur est connecté avec succès
                            // Ajoutez ici le code à exécuter après la connexion réussie
                            user = mAuth.getCurrentUser();
                            Intent intent = new Intent(Login.this, ProductActivity.class);
                            startActivity(intent);

                            //Toast.makeText(Login.this, "Authentication success.",
                                    //Toast.LENGTH_SHORT).show();
                        } else {
                            // La connexion a échoué, affichez un message d'erreur
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }


}