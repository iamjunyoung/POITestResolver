package com.bbeaggoo.poitestresolver;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String ID = "_id";

    public static final String POI_ID = "poi_id";
    public static final String FLOOR_ID = "floor_id";
    public static final String FLOOR_NAME_EN = "floor_name_en";
    public static final String FLOOR_NAME_KR = "floor_name_kr";
    public static final String FLOOR_ORDER = "floor_order";
    public static final String FLOOR_INDEX = "floor_index";
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
    public static final String THETA = "theta";
    public static final String TYPE = "type";
    public static final String IS_HOME = "is_home";
    public static final String IS_CHARGER = "is_charger";
    public static final String IS_IN_POILIST = "is_in_poi_list";

    public static final Uri CONTENT_URI = Uri.parse("content://com.bbeaggoo.poitest/pois");
    TextView result = null;
    ContentObserver co = null;
    private String TAG = "[POITestResolver][MainActivity]";

    /*
            POIColumns.ID + " integer primary key autoincrement, " +

            POIColumns.POI_ID + " text, " +
            POIColumns.FLOOR_ID + " text, " +
            POIColumns.FLOOR_NAME_EN + " text, " +
            POIColumns.FLOOR_NAME_KR + " text, " +
            POIColumns.FLOOR_ORDER + " integer, " +
            POIColumns.ATTRIBUTE_EL_ID + " text, " +
            POIColumns.ATTRIBUTE_EL_VENDER + " text, " +
            POIColumns.ATTRIBUTE_EL_FLOOR_LIST + " text, " +
            POIColumns.ATTRIBUTE_DESC + " text, " +
            POIColumns.ATTRIBUTE_TEL + " text, " +
            POIColumns.RADIUS + " integer, " +
            POIColumns.IS_RESTRICTED + " integer, " +
            POIColumns.NAME_KR + " text, " +
            POIColumns.POSITION_X + " integer, " +
            POIColumns.POSITION_Y + " integer, " +
            POIColumns.POSITION_Z + " integer, " +
            POIColumns.THETA + " integer, " +
            POIColumns.TYPE + " integer, " +
            POIColumns.IS_HOME + " text, " +
            POIColumns.IS_CHARGER + " text);";
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);

        co = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "selfChange : " + selfChange + "  uri " + uri + "  " + uri.getPath());
            }
        };
        getContentResolver().registerContentObserver(CONTENT_URI, true, co);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        getContentResolver().unregisterContentObserver(co);
        super.onDestroy();
    }

    public void onClick(View v) {
        Cursor c2 = null;
        switch (v.getId()) {
            case R.id.insert:
                ContentValues cv = new ContentValues();

                cv.put(POI_ID, "1234");
                cv.put(FLOOR_ID, "-2");
                cv.put(FLOOR_NAME_EN, "B2");
                cv.put(FLOOR_NAME_KR, "지하2층");
                cv.put(FLOOR_ORDER, -2);
                cv.put(FLOOR_INDEX, -2);
                cv.put(ATTRIBUTE_EL_ID,"0012312");
                cv.put(ATTRIBUTE_EL_VENDER, "3");
                cv.put(ATTRIBUTE_EL_FLOOR_LIST, "Mitsubishi");
                cv.put(ATTRIBUTE_DESC, "엘리베이터");
                cv.put(ATTRIBUTE_TEL, "02-333-4444");
                cv.put(RADIUS, 3);
                cv.put(IS_RESTRICTED, 1);
                cv.put(NAME_KR, "엘리베이터1");
                cv.put(POSITION_X, 1);
                cv.put(POSITION_Y, 2);
                cv.put(POSITION_Z, 3);
                cv.put(THETA, 270);
                cv.put(TYPE, 1);
                cv.put(IS_HOME, "false");
                cv.put(IS_CHARGER, "false");
                cv.put(IS_IN_POILIST, "false");
                Uri retUir  = getContentResolver().insert(CONTENT_URI, cv);
                result.setText("insert return \n" + retUir);

                break;
            case R.id.update:
                break;
            case R.id.delete:
                break;
            case R.id.query:
                Cursor c = getContentResolver().query(CONTENT_URI
                        , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                                , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                                , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                                , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                        , null
                        , null
                        , "_id ASC");

                showResult(c);
                c.close();
                break;

            case R.id.queryByPOI:
                c2 = getContentResolver().query(CONTENT_URI
                        , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                                , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                                , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                                , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                                , POI_ID + "=?"
                        , new String[] {"cb9b9eee-bc1e-4c42-85ab-ef34ed3821c6"}
                        , null);

                showResult(c2);
                c2.close();
                break;

            case R.id.queryByPOI1234:
                c2 = getContentResolver().query(CONTENT_URI
                        , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                                , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                                , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                                , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                        , POI_ID + "=? and " +  IS_IN_POILIST + "=?"
                        , new String[] {"1234", "false"}
                        , null);

                showResult(c2);
                c2.close();
                break;
                /*
                {
  "data": {
    "6831": {
      "name": {
        "en": "dh",
        "kr": "대한외래"
      },
      "floors": {
        "-2": {
          "name": {
            "en": "B2",
            "kr": "지하2층"
          },
          "order": -2,
          "index": -2,
          "customPointData": {
            "2593e5e0-5791-11ea-92d2-893e361a6a29"
            "80e8bc0b-591d-11ea-8465-2f727b4b81f5"
            "8ab2c5a6-5913-11ea-93f1-9f906c0e6718"
            "cb9b9eee-bc1e-4c42-85ab-ef34ed3821c6"
            "2f138266-1038-4d84-8e02-05b5cdd96e09"
            "72f7445a-f302-4ba3-b20c-6caabb78b551"
            "e82f0f2f-30db-4934-bd27-27824f0f546d"
            "50f38756-c203-410c-ae92-2fd8cb932332"
            "6224e452-03e6-44bd-a981-928a184fa4da"
                 */
            case R.id.registerHomeByLocation:
                registerHomeByLocation(1,2,3);
                break;
            case R.id.registerHomeByPOI:
                registerHomeByPOI("2593e5e0-5791-11ea-92d2-893e361a6a29"); //첫번째거
                break;
            case R.id.registerCharger:
                registerCharger(2, 3, 4, 170);
                break;
            case R.id.updateHomeByLocation:
                updateHomeByLocation(6, 7, 8);
                break;
            case R.id.updateHomeByPOI:
                updateHomeByPOI("6224e452-03e6-44bd-a981-928a184fa4da"); //마지막거
                break;
            case R.id.updateCharger:
                updateCharger(1, 1, 1, 130);
                break;
            case R.id.removeHome:
                removeHome();
                break;
            case R.id.removeCharger:
                removeCharger();
                break;


        }

    }

    public void showResult(Cursor c) {
        SpannableStringBuilder text = new SpannableStringBuilder();
        if (c != null) {
            while (c.moveToNext()) {
                text.append("\n" +
                        "ID : " + c.getInt(0) + "\n" +
                        "POI_ID : " + c.getString(1) + "\n" +
                        "FLOOR_ID : " + c.getString(2) + "\n" +
                        "FLOOR_NAME_EN : " + c.getString(3) + "\n" +
                        "FLOOR_NAME_KR : " + c.getString(4) + "\n" +
                        "FLOOR_ORDER : " + c.getInt(5) + "\n" +
                        "FLOOR_INDEX : " + c.getInt(6) + "\n" +
                        "ATTRIBUTE_EL_ID : " + c.getString(7) + "\n" +
                        "ATTRIBUTE_EL_VENDER : " + c.getString(8) + "\n" +
                        "ATTRIBUTE_EL_FLOOR_LIST : " + c.getString(9) + "\n" +
                        "ATTRIBUTE_DESC : " + c.getString(10) + "\n" +
                        "ATTRIBUTE_TEL : " + c.getString(11) + "\n" +
                        "RADIUS : " + c.getInt(12) + "\n" +
                        "IS_RESTRICTED : " + c.getInt(13) + "\n" +
                        "NAME_KR : " + c.getString(14) + "\n" +
                        "POSITION_X : " + c.getFloat(15) + "\n" +
                        "POSITION_Y : " + c.getFloat(16) + "\n" +
                        "POSITION_Z : " + c.getInt(17) + "\n" +
                        "THETA : " + c.getInt(18) + "\n" +
                        "TYPE : " + c.getInt(19) + "\n" +
                        "IS_HOME : " + c.getString(20) + "\n" +
                        "IS_CHARGER : " + c.getString(21) + "\n" +
                        "IS_IN_POILIST : " + c.getString(22) + "\n" +
                        "------------------------------------------------------------");
            }
        }
        if (text != null) {
            result.setText(text);
        }
    }

    public boolean existHomeRegistered() {
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[]{ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_HOME + "=?"
                , new String[]{"true"}
                , null);
        if (c != null) {
            boolean homeExist;
            if (c.getCount() > 0) {
                Toast.makeText(this, "Home exist", Toast.LENGTH_LONG).show();
                homeExist = true;
            } else {
                Toast.makeText(this, "Home's not exist", Toast.LENGTH_LONG).show();
                homeExist = false;
            }
            c.close();
            return homeExist;
        }
        return false;
    }

    public boolean existChargerRegistered() {
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[]{ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_CHARGER + "=?"
                , new String[]{"true"}
                , null);
        if (c != null) {
            boolean chargerExist;
            if (c.getCount() > 0) {
                Toast.makeText(this, "Charger exist", Toast.LENGTH_LONG).show();
                chargerExist = true;
            } else {
                Toast.makeText(this, "Charger's not exist", Toast.LENGTH_LONG).show();
                chargerExist = false;
            }
            c.close();
            return chargerExist;
        }
        return false;
    }

    // in POIDataManager. aar
    // 1.
    public void registerHomeByLocation(float x, float y, int z) {
        if (existHomeRegistered()) {
            return;
        }
        //새로운 location 좌표를 입력받아 isHome=true로 db에 row insert

        ContentValues cv = new ContentValues();
        cv.put(POI_ID, ""); // This is set after.
        cv.put(FLOOR_ID, "");
        cv.put(FLOOR_NAME_EN, "");
        cv.put(FLOOR_NAME_KR, "");
        cv.put(FLOOR_ORDER, z);
        cv.put(FLOOR_INDEX, z);
        cv.put(ATTRIBUTE_EL_ID,"");
        cv.put(ATTRIBUTE_EL_VENDER, "");
        cv.put(ATTRIBUTE_EL_FLOOR_LIST, "");
        cv.put(ATTRIBUTE_DESC, "");
        cv.put(ATTRIBUTE_TEL, "");
        cv.put(RADIUS, 0);
        cv.put(IS_RESTRICTED, 0);
        cv.put(NAME_KR, "");
        cv.put(POSITION_X, x);
        cv.put(POSITION_Y, y);
        cv.put(POSITION_Z, z);
        cv.put(THETA, 0);
        cv.put(TYPE, 0);
        cv.put(IS_HOME, "true"); // HOME
        cv.put(IS_CHARGER, "false");
        cv.put(IS_IN_POILIST, "false");
        Uri retUir  = getContentResolver().insert(CONTENT_URI, cv);
        result.setText("registerHomeByLocation return \n" + retUir);
    } // Done

    // 2.
    public void registerHomeByPOI(String poi) { // in POI List
        if (existHomeRegistered()) {
            return;
        }
        //Poi id로 query하여 나온애의 isHome = true로 update
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , POI_ID + "=?"
                , new String[] {poi}
                , null);

        ContentValues updateRowForHomeSet = new ContentValues();
        updateRowForHomeSet.put(IS_HOME, "true");
        getContentResolver().update(CONTENT_URI, updateRowForHomeSet, POI_ID + "= '" + poi + "';", null);
    }

    // 3/
    public void registerCharger(float x, float y, int z, int theta) {
        if (existChargerRegistered()) {
            return;
        }
        //새로운 location 좌표를 입력받아 db에 row insert 및 isCharger = true로 update
        ContentValues cv = new ContentValues();
        cv.put(POI_ID, ""); // This is set after.
        cv.put(FLOOR_ID, "");
        cv.put(FLOOR_NAME_EN, "");
        cv.put(FLOOR_NAME_KR, "");
        cv.put(FLOOR_ORDER, z);
        cv.put(FLOOR_INDEX, z);
        cv.put(ATTRIBUTE_EL_ID,"");
        cv.put(ATTRIBUTE_EL_VENDER, "");
        cv.put(ATTRIBUTE_EL_FLOOR_LIST, "");
        cv.put(ATTRIBUTE_DESC, "");
        cv.put(ATTRIBUTE_TEL, "");
        cv.put(RADIUS, 0);
        cv.put(IS_RESTRICTED, 0);
        cv.put(NAME_KR, "");
        cv.put(POSITION_X, x);
        cv.put(POSITION_Y, y);
        cv.put(POSITION_Z, z);
        cv.put(THETA, theta);
        cv.put(TYPE, 0);
        cv.put(IS_HOME, "false");
        cv.put(IS_CHARGER, "true"); // Charger
        cv.put(IS_IN_POILIST, "false");
        Uri retUir  = getContentResolver().insert(CONTENT_URI, cv);
        result.setText("registerHomeByLocation return \n" + retUir);
    }
    // 4
    public void updateHomeByLocation(float x, float y, int z) {
        if (!existHomeRegistered()) {
            return;
        }
        //기존 isHome = true인 애의 IS_IN_POILIST = true이면,  애의 isHome = false로 update
        //기존 isHome = true인 애의 IS_IN_POILIST = false이면,  애는 delete
        //
        //이후 새로 입력받은 location 좌표를 isHome = true로 db에 row insert

        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_HOME + "=?"
                , new String[] {"true"}
                , null);
        showResult(c);

        if (c != null) {
            if (c.getCount() == 1) {
                // Update as u input.
                if (c.moveToFirst()) {
                    if ("true".equals(c.getString(22))) { // IS_IN_POILIST true -> update
                        // update this row to isHome = false
                        ContentValues updateRow = new ContentValues();
                        updateRow.put(IS_HOME, "false");
                        getContentResolver().update(CONTENT_URI, updateRow, IS_HOME + "= 'true';", null);
                    } else {  // IS_IN_POILIST false -> delete
                        // delete this row
                        getContentResolver().delete(CONTENT_URI, IS_HOME + "= 'true';", null);
                    }

                    //After that
                    registerHomeByLocation(x, y, z);
                }
            } else if (c.getCount() == 0) {
                Toast.makeText(this, "You have to register home before update", Toast.LENGTH_LONG).show();
            } else {
                // Can't happen this case.
                Toast.makeText(this, "Home is set above 1?? This is abnormal", Toast.LENGTH_LONG).show();
            }
            c.close();
        }
    } //Done

    // 5
    public void updateHomeByPOI(String poi) {
        if (!existHomeRegistered()) {
            return;
        }

        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[]{ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_HOME + "=?"
                , new String[]{"true"}
                , null);

        showResult(c);

        //기존 isHome = true인 애의 IS_IN_POILIST = true이면,  애의 isHome = false로 update
        //기존 isHome = true인 애의 IS_IN_POILIST = false이면,  애는 delete
        //
        //Poi id로 query하여 나온애의 isHome = true로 update
        if (c != null) {
            if (c.getCount() == 1) {
                // Update as u input.
                if (c.moveToFirst()) {
                    if ("true".equals(c.getString(22))) { // IS_IN_POILIST true -> update
                        // update this row to isHome = false
                        ContentValues updateRow = new ContentValues();
                        updateRow.put(IS_HOME, "false");
                        getContentResolver().update(CONTENT_URI, updateRow, IS_HOME + "= 'true';", null);
                    } else {  // IS_IN_POILIST false -> delete
                        // delete this row
                        getContentResolver().delete(CONTENT_URI, IS_HOME + "= 'true';", null);
                    }

                    ContentValues updateRowForHomeSet = new ContentValues();
                    updateRowForHomeSet.put(IS_HOME, "true");
                    getContentResolver().update(CONTENT_URI, updateRowForHomeSet, POI_ID + "= '" + poi + "';", null);
                }
            } else if (c.getCount() == 0) {
                // You need to register Home first.
                Toast.makeText(this, "You have to register home before remove", Toast.LENGTH_LONG).show();
            } else {
                // Can't happen this case.
                Toast.makeText(this, "Home is set above 1?? This is abnormal", Toast.LENGTH_LONG).show();
            }
            c.close();
        }
    }

    // 6
    public void updateCharger(float x, float y, int z, int theta) {
        if (!existChargerRegistered()) {
            return;
        }
        //기존 isCharger = true인 애는 delete
        //
        //새로운 location 좌표를 입력받아 db에 row insert 및 isCharger = true로 update
        //   (registerCharger (location) 를 호출해줘도 됨)
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[]{ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_CHARGER + "=?"
                , new String[]{"true"}
                , null);
        showResult(c);
        getContentResolver().delete(CONTENT_URI, IS_CHARGER +  "= 'true';", null);

        registerCharger(x, y, z, theta);
    }

    // 7
    public void removeHome() {
        if (!existHomeRegistered()) {
            return;
        }
        //기존 isHome = true인 애의 IS_IN_POILIST = false이면,  애는 delete
        //기존 isHome = true인 애의 IS_IN_POILIST = true이면,  애의 isHome = false로 update
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_HOME + "=?"
                , new String[] {"true"}
                , null);

        showResult(c);

        if (c != null) {
            if (c.getCount() == 1) {
                // Update as u input.
                if (c.moveToFirst()) {
                    if ("true".equals(c.getString(22))) { // IS_IN_POILIST true -> update
                        // update this row to isHome = false
                        ContentValues updateRow = new ContentValues();
                        updateRow.put(IS_HOME, "false");
                        getContentResolver().update(CONTENT_URI, updateRow, IS_HOME + "= 'true';", null);
                    } else {  // IS_IN_POILIST false -> delete
                        // delete this row
                        getContentResolver().delete(CONTENT_URI, IS_HOME + "= 'true';", null);
                    }
                }
            } else if (c.getCount() == 0) {
                // You need to register Home first.
                Toast.makeText(this, "You have to register home before remove", Toast.LENGTH_LONG).show();
            } else {
                // Can't happen this case.
                Toast.makeText(this, "Home is set above 1?? This is abnormal", Toast.LENGTH_LONG).show();

            }
            c.close();
        }
    }

    // 8
    public void removeCharger() {
        if (!existChargerRegistered()) {
            return;
        }
        //기존 isCharger = true인 row를 delete
        getContentResolver().delete(CONTENT_URI, IS_CHARGER + "= 'true';", null);
    }

    //Just for test
    public void removeAllHome() {
        Cursor c = getContentResolver().query(CONTENT_URI
                , new String[] {ID, POI_ID, FLOOR_ID, FLOOR_NAME_EN, FLOOR_NAME_KR
                        , FLOOR_ORDER, FLOOR_INDEX, ATTRIBUTE_EL_ID, ATTRIBUTE_EL_VENDER, ATTRIBUTE_EL_FLOOR_LIST
                        , ATTRIBUTE_DESC, ATTRIBUTE_TEL, RADIUS, IS_RESTRICTED, NAME_KR
                        , POSITION_X, POSITION_Y, POSITION_Z, THETA, TYPE, IS_HOME, IS_CHARGER, IS_IN_POILIST}
                , IS_HOME + "=?"
                , new String[] {"true"}
                , null);

        if (c != null) {
            if (c.getCount() >= 1) {
                // Update as u input.
                if (c.moveToNext()) {
                    if ("true".equals(c.getString(22))) { // IS_IN_POILIST true -> update
                        // update this row to isHome = false
                        ContentValues updateRow = new ContentValues();
                        updateRow.put(IS_HOME, "false");
                        getContentResolver().update(CONTENT_URI, updateRow, IS_HOME + "= 'true';", null);
                    } else {  // IS_IN_POILIST false -> delete
                        // delete this row
                        getContentResolver().delete(CONTENT_URI, IS_HOME + "= 'true';", null);
                    }
                }
            } else {
                // You need to register Home first.
                Toast.makeText(this, "You have to register home before removeAll", Toast.LENGTH_LONG).show();
            }
            c.close();
        }
    }
    public void getAllPOI() {

    }

    public void getPOIByName(String name) {

    }

    public void getPOIByFloor(String floor) {

    }


}
