package lim.weerawat.coachingtools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        }
    }    //ClickSignIN



    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUPActivity.class));
    }
}   //Main Class

