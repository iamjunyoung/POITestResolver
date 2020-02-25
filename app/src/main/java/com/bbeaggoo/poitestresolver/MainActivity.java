package com.bbeaggoo.poitestresolver;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String _ID = "poi_id";
    public static final String FLOOR_ID = "floor_id";
    public static final String FLOOR_NAME_EN = "floor_name_en";
    public static final String FLOOR_NAME_KR = "floor_name_kr";
    public static final String FLOOR_ORDER = "floor_order";
    public static final String ATTRIBUTE_EL_ID = "attribute_el_id";
    public static final String ATTRIBUTE_EL_VENDER = "attribute_el_vendor";
    public static final String ATTRIBUTE_EL_FLOOR_LIST = "attribute_el_floor_list";
    public static final String ATTRIBUTE_DESC = "attribute_desc";
    public static final String ATTRIBUTE_TEL = "attribute_tel";
    public static final String RADIUS = "radius";
    public static final String IS_RESTRICTED = "is_restricted";
    public static final String NAME_KR = "name_kr";
    public static final String POSITION_X = "position_x";
    public static final String POSITION_Y = "position_y";
    public static final String POSITION_Z = "position_z";
    public static final String IS_HOME = "is_home";
    public static final String IS_CHARGER = "is_charger";
    public static final Uri CONTENT_URI = Uri.parse("content://com.bbeaggoo.poitest/pois");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                Cursor c = getContentResolver().query(CONTENT_URI
                , new String[] {_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, IS_HOME, IS_CHARGER}
                , null
                , null
                , null);

                if (c != null) {
                    while (c.moveToNext()) {
                        Log.d("hello", c.toString() + "\n");
                    }
                }
                break;
        }
    }
}
