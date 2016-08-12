package lim.weerawat.coachingtools;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private static final int myINT = 1;
    private String videopathString,namevdoString;

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
            namevdoString = videopathString.substring(videopathString.lastIndexOf("/")+ 1);

            Log.d("12Aug16", "Name of vdo ==>" + namevdoString);


        }   // if


    }//on activity

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
        startActivityForResult(Intent.createChooser(intent,"Select vdo"),myINT);




    }   //Upload VOD
}   //Main Class
