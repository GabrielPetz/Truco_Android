package codelabs.com.br.truco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import codelabs.com.br.truco.model.User;


public class RegisterActivity extends Activity {

    private EditText password;
    private EditText email;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(View v) {
        String sLogin = email.getText().toString();
        String sPassword = password.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(sLogin, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        if (task.isSuccessful()) {
                            insertInfo(firebaseAuth);
                            Toast.makeText(RegisterActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Erro no registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void insertInfo(FirebaseAuth auth){

        mDataBase.child("user").child(auth.getUid()).setValue(new User(auth.getUid(), auth.getCurrentUser().getEmail(), auth.getCurrentUser().getDisplayName()))
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void gologin(View v) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}
