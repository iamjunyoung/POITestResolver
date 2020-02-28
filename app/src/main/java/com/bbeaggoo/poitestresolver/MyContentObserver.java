package com.bbeaggoo.poitestresolver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class MyContentObserver extends ContentObserver  {
    private static final String TAG = "MyContentObserver" ;
    private Context mContext;

    public MyContentObserver(Handler handler) {
        super(handler);
    }
    public MyContentObserver(Context context, Handler handler) {
        super(handler);
        mContext = context;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange);
        //super.onChange(selfChange, uri);
        Log.i(TAG, "change is occured. uri : " + uri + "    LastPathSegment : " + uri.getLastPathSegment());
        //fetchLastedFiles();
        //shareTo(uri);
        //share to other people.
        //move this file to other folder. (or create other folder)

        //fetchLatestImages();

        //파일을 다운받았을 때,
        //03-25 23:16:20.747 5941-5941/com.bbeaggoo.myapplication I/APVM_CO: change is occured. uri : content://media/external/file/25014    LastPathSegment : 25014
        //03-25 23:16:20.855 5941-5941/com.bbeaggoo.myapplication I/APVM_CO: getLastestImage uri  /storage/emulated/0/Pictures/Screenshots/Screenshot_2018-03-25-22-51-24.png

        //이미지 파일 스크린샷 할 때,
        //03-25 23:19:02.890 5941-5941/com.bbeaggoo.myapplication I/APVM_CO: change is occured. uri : content://media/external/file/25016    LastPathSegment : 25016
        //03-25 23:19:03.217 5941-5941/com.bbeaggoo.myapplication I/APVM_CO: getLastestImage uri  /storage/emulated/0/Pictures/Screenshots/Screenshot_2018-03-25-23-19-02.png
    }
}
