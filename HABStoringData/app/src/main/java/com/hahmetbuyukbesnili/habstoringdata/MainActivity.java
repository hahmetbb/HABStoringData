package com.hahmetbuyukbesnili.habstoringdata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        alertDialog = new AlertDialog.Builder(this);


        sharedPreferences =this.getSharedPreferences("com.hahmetbuyukbesnili.habstoringdata" , Context.MODE_PRIVATE);

        int savedAge = sharedPreferences.getInt("StoredAge", 0);

        if (savedAge == 0) {
            textView.setText("Your Age: ");
        } else {
            textView.setText("Your Age: "+ savedAge);
        }

    }


    public void save(View view){

        alertDialog.setTitle("Save");
        alertDialog.setMessage("Are you sure?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (editText.getText().toString().matches("")){
                    textView.setText("Please enter your age");
                } else {
                    int age = Integer.parseInt(editText.getText().toString());
                    textView.setText("Your Age: "+ age);

                    sharedPreferences.edit().putInt("StoredAge",age).apply();
                }

                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();

            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.show();


    }

    public void delete(View view){

        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Are you sure?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int savedAge = sharedPreferences.getInt("StoredAge",0);

                if (savedAge != 0){
                    sharedPreferences.edit().remove("StoredAge").apply();
                    textView.setText("Your Age: ");
                }

                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();

            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_LONG).show();

            }
        });

        alertDialog.show();

    }

}