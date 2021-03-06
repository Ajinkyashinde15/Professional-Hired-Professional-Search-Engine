package nixer.nixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectAccountType extends AppCompatActivity {

    //Create Variables
    protected Button standardacc,professionalacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseaccount);

        standardacc = (Button) findViewById(R.id.standaccountbutton);
        professionalacc = (Button) findViewById(R.id.professionbutton);


        standardacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAccountType.this, StandardAccountSignup.class);
                startActivity(intent);
                finish();
            }
        });
        professionalacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAccountType.this, ProfessionalAccountSignup.class);
                startActivity(intent);
                finish();
            }
        });


    }
}