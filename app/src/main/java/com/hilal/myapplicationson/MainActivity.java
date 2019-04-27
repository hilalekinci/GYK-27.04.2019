package com.hilal.myapplicationson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et1 = findViewById(R.id.text);
        final EditText et2 = findViewById(R.id.description);
        Button btn = findViewById(R.id.kaydet);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();

                if( !s1.equals("") && !s2.equals("")){
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    // Create a new user with a first and last name
                    Map<String, Object> note = new HashMap<>();
                    note.put("title", s1);
                    note.put("description", s2);

                    db.collection("notes")
                            .add(note)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(MainActivity.this, "Notunuz kaydedilmiştir.", Toast.LENGTH_LONG).show();
                                    et1.setText("");
                                    et2.setText("");

                                    startActivity( new Intent(MainActivity.this, ListActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("", "Error adding document", e);
                                    Toast.makeText(MainActivity.this, "İşlem başarısız.", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else{
                    Toast.makeText(MainActivity.this, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
