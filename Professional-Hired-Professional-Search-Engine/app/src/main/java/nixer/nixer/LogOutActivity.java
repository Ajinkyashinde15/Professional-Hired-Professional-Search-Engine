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


public class LogOutActivity extends AppCompatActivity {

    //Create Variables
    protected Button logoutbutton,continuebutton;
    public static final String PREFS_NAME= "loginusersdetails";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logoutlayout);

        logoutbutton = (Button) findViewById(R.id.logoutbutton);
        continuebutton = (Button) findViewById(R.id.continuebutton);

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             // Update login value it states that user is deactive

                preferences= getSharedPreferences("loginusersdetails", MODE_PRIVATE);
                String name=preferences.getString("name", null);
                String password=preferences.getString("password", null);
                String login="n";

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("login", login));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://137.43.93.134/nixerproject/updatelogindetails.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    Toast.makeText(getApplicationContext(), "Logout Successfully "+name+" !!!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error= "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            //
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("name");
                editor.remove("password");
                editor.remove("account_type");
                editor.remove("profession");
                editor.remove("phoneno");
                editor.remove("emailid");
                editor.remove("userlatitute");
                editor.remove("userlongitute");
                editor.remove("logged");
                editor.commit();

                Intent intent = new Intent(LogOutActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                if (settings.getString("logged", null).toString().equals("logged"))
                {
                    if(settings.getString("account_type",null).toString().equals("standard"))
                    {
                        Intent intent2 = new Intent(LogOutActivity.this, SelectProfessinalType.class);
                        startActivity(intent2);
                        finish();

                    }else {
                        Intent intent1 = new Intent(LogOutActivity.this, ShowMapProfessional.class); //change your current location
                        startActivity(intent1);
                        finish();

                    }
                }
            }
        });


    }
}