package lim.weerawat.coachingtools;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private static final int myINT = 1;
    private String videopathString, namevdoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }    //Main Method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("12Aug16", "Requestcode ==>" + requestCode);
        Log.d("12Aug16", "Resultcode ==>" + resultCode);


        if ((requestCode == myINT) && (resultCode == RESULT_OK)) {

            Uri uri = data.getData();
            videopathString = findPath(uri);

            Log.d("12Aug16", "Path of VDO ==>" + videopathString);
            namevdoString = videopathString.substring(videopathString.lastIndexOf("/") + 1);

            Log.d("12Aug16", "Name of vdo ==>" + namevdoString);
            uploadvdotoserver();


        }   // if


    }//on activity

    private void uploadvdotoserver() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {

            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com", 21,
                    "mama@swiftcodingthai.com", "Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("Video");

            simpleFTP.stor(new File(videopathString));
            simpleFTP.disconnect();

            Toast.makeText(this, "Upload VDO Done", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {

            Log.d("12Aug16", "e==>" + e.toString());

        }

    }    //upload

    private String findPath(Uri uri) {

        String strvideopath = null;
        String[] columnStrings = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, columnStrings, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
            int intcolumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            strvideopath = cursor.getString(intcolumnIndex);
        } else {

            strvideopath = uri.getPath();


        }

        return strvideopath;

    }

    public void clickupload(View view) {
        //Choose VDO
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(Intent.createChooser(intent, "Select vdo"), myINT);


    }   //Upload VOD
}   //Main Class
