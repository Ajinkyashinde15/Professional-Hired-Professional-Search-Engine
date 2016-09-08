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
import android.widget.Spinner;
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


public class ProfessionalAccountSignup extends AppCompatActivity
{
    protected EditText professionusername,professionpassword,tphoneno,emailidtext;
    Spinner spinner;
    protected Button professionalsignup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professionalaccount);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        professionusername = (EditText) findViewById(R.id.professionusername);
        professionpassword = (EditText) findViewById(R.id.professionpassword);
        tphoneno = (EditText) findViewById(R.id.phoneno);
        emailidtext = (EditText) findViewById(R.id.emailidtext);
        spinner= (Spinner) findViewById(R.id.spinner);

        professionalsignup = (Button) findViewById(R.id.professionalsignup);

        professionalsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=professionusername.getText().toString();
                String password=professionpassword.getText().toString();
                String account="professional".toString();
                String professional= (String) spinner.getSelectedItem();
                String phoneno=tphoneno.getText().toString();
                String emailid=emailidtext.getText().toString();

                insertToDatabase(name,password,account,professional,phoneno,emailid);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfessionalAccountSignup.this);
                builder.setMessage(R.string.sign_up_button_label)
                        .setTitle(R.string.signup_success)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

                Intent intent = new Intent(ProfessionalAccountSignup.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void insertToDatabase(final String name, final String password,final String account_type,final String profession,final String phoneno,final String emailid ) {


        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String pname =name;
                String ppassword = password;
                String paccount = account_type;
                String userlatitute = "0";
                String userlongitute = "0";

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
