package lim.weerawat.coachingtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUPActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText,userEditText ,passEditText;
    private String nameString,userString, passString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);

    }   //Main Method

    public void clickSignUpSign(View view) {
        //Get value from Edit text
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        //chk space
        if (nameString.equals("") || userString.equals("") || passString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณากรอกให้ครบถ้วน");
        } else {
            //No Space

        }

    }   //click sign



}   //MainClass
