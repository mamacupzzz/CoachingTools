package lim.weerawat.coachingtools;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText usereditText, passwordgetEditText;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        usereditText = (EditText) findViewById(R.id.editText4);
        passwordgetEditText = (EditText) findViewById(R.id.editText5);




    }   //Main Methood

    //Create Inner Class
    private class SynUser extends AsyncTask<Void, Void, String> {
        //Explicit
        private Context context;
        private String myUserString, myPasswordString,truePasswordString,nameString;
        private static final String urlJON = "http://swiftcodingthai.com/mama/get_data_mama.php";
        private boolean statusABoolean = true;


        public SynUser(Context context, String myUserString, String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }

        }   //Network BackEnd

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("11AugV1", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length(); i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (myUserString.equals(jsonObject.getString("user"))) {
                        statusABoolean = false;
                        truePasswordString = jsonObject.getString("password");
                        nameString = jsonObject.getString("name");

                    }   //if

                }   //for
                //Chk user
                if (statusABoolean) {
                    //User Fail
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,"User Fail","ไม่มีข้อมูล"+myUserString+"ในฐานข้อมูลของเรา");
                } else if (myPasswordString.equals(truePasswordString)) {
                    // Pass True
                    Toast.makeText(context,"Welcome"+nameString,
                            Toast.LENGTH_SHORT).show();

                } else {
                    //Password fail
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "Password Fail",
                            "Please Try Again");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // Posting
    }   //SynUser Class


    public void clickSignIn(View view) {
        //Get Value
        userString = usereditText.getText().toString().trim();
        passwordString = passwordgetEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,
                    getResources().getString(R.string.have_space_title),
                    getResources().getString(R.string.have_space_message));

        } else {
            //No Space
            SynUser synUser = new SynUser(this,userString,passwordString);
            synUser.execute();

        }
    }    //ClickSignIN



    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUPActivity.class));
    }
}   //Main Class

