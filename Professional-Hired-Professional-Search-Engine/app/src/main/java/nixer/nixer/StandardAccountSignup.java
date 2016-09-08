package nixer.nixer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StandardAccountSignup extends AppCompatActivity
{
    protected EditText standerdemail;
    protected EditText standerdpassword;
    protected Button signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standardaccount);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        standerdemail = (EditText) findViewById(R.id.standerdemail);
        standerdpassword = (EditText) findViewById(R.id.standerdpassword);
        signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=standerdemail.getText().toString();
                String password=standerdpassword.getText().toString();
                String account="standard".toString();

                insertToDatabase(name,password,account);
                AlertDialog.Builder builder = new AlertDialog.Builder(StandardAccountSignup.this);
                builder.setMessage(R.string.sign_up_button_label)
                        .setTitle(R.string.signup_success)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

                Intent intent = new Intent(StandardAccountSignup.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void insertToDatabase(final String name, final String password,final String account_type) {



        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String pname =name;
                String ppassword = password;
                String paccount = account_type;
                String profession = "";
                String emailid = "";
                String phoneno = "";
                String userlatitute = "";
                String userlongitute = "";

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", pname));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("account_type", account_type));
                nameValuePairs.add(new BasicNameValuePair("profession", profession));
                nameValuePairs.add(new BasicNameValuePair("phoneno", phoneno));
                nameValuePairs.add(new BasicNameValuePair("emailid", emailid));
                nameValuePairs.add(new BasicNameValuePair("userlatitute", userlatitute));
                nameValuePairs.add(new BasicNameValuePair("userlongitute", userlongitute));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://137.43.93.134/nixerproject/inserdatabaseregistration.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (Exception e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, password,account_type,"","","","","");
    }
}
