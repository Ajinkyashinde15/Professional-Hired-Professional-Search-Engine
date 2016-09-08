package nixer.nixer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class SelectProfessinalType extends AppCompatActivity {

    //Create Variables
    protected Button programmer,plumber,makeup_artist;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectprofessionaltype);

        programmer = (Button) findViewById(R.id.programmer);
        plumber = (Button) findViewById(R.id.plumber);
        makeup_artist= (Button) findViewById(R.id.makeup_artist);

        programmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectProfessinalType.this, ShowMapStandard.class);
                intent.putExtra("profession", "Programmer");
                startActivity(intent);
                finish();

            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectProfessinalType.this, ShowMapStandard.class);
                intent.putExtra("profession","Plumber");
                startActivity(intent);
                finish();

            }
        });
        makeup_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectProfessinalType.this, ShowMapStandard.class);
                intent.putExtra("profession","Makeup_Artist");
                startActivity(intent);
                finish();

            }
        });

    }
}