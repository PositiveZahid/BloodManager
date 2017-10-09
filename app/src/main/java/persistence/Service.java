package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import business.DonorTO;

/**
 * Created by Rakib & Salim on 9/30/2015.
 */
public class Service extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BloodDB";
    // Donors table name
    private static final String TABLE_DONORS = "donors";
    // Donors Table Columns names
    private static final String KEY_ID = "donorId";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_AGE = "age";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_BLOODGROUP = "bloodGroup";
    private static final String KEY_DONATION_TIMES = "noOfTimesDonated";
    private static final String KEY_DONATION_STATUS = "donationStatus";
    private static final String KEY_IS_DELTED = "isDeleted";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PHONE,
            KEY_ADDRESS, KEY_BLOODGROUP, KEY_AGE, KEY_DONATION_TIMES,
            KEY_DONATION_STATUS, KEY_IS_DELTED};

    private Long insertID;

    public Service(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create donors table
        String CREATE_DONOR_TABLE = "CREATE TABLE "+TABLE_DONORS+ " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, "+
                KEY_EMAIL + " TEXT, "+
                KEY_PHONE + " TEXT, "+
                KEY_ADDRESS + " TEXT, "+
                KEY_BLOODGROUP + " TEXT, "+
                KEY_AGE + " INTEGER, "+
                KEY_DONATION_TIMES + " INTEGER, "+
                KEY_DONATION_STATUS + " BOOLEAN, "+
                KEY_IS_DELTED + " BOOLEAN )";
        // create donors table
        db.execSQL(CREATE_DONOR_TABLE);
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older donors table if existed
        db.execSQL("DROP TABLE IF EXISTS donors");
        // create fresh donors table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) donors
     * + get all donors + delete all donors
     */

    public void persistDonor(DonorTO donorTO) throws Exception{
        Log.d("addDonor", donorTO.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, donorTO.getName());
        values.put(KEY_BLOODGROUP, donorTO.getBloodGroup());
        values.put(KEY_ADDRESS, donorTO.getAddress());
        values.put(KEY_EMAIL, donorTO.getEmail());
        values.put(KEY_PHONE, donorTO.getPhone());
        values.put(KEY_AGE, donorTO.getAge());
        values.put(KEY_DONATION_STATUS, donorTO.getDonationStatus());
        values.put(KEY_DONATION_TIMES, donorTO.getNumberOfTimesDonated());
        // 3. insert
        insertID = db.insert(TABLE_DONORS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        Log.d("persistDonor(): ", ""+insertID);
        // 4. close
        db.close();
    }

    public DonorTO getDonor(int id){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor =
                db.query(TABLE_DONORS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        // 4. build DonorTO object
        DonorTO donorTO = new DonorTO();
        donorTO.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE))));
        donorTO.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        donorTO.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        donorTO.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        donorTO.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOODGROUP)));
        Log.d("getDonors(" + id + ")", donorTO.toString());
        // 5. return donorTO
        return donorTO;
    }

    // Get All donors
//    public List<DonorTO> fetchAllDonors() {
//        List<DonorTO> donors = new ArrayList<DonorTO>();
//        // 1. build the query
//        String query = "SELECT  * FROM " + TABLE_DONORS;
//        // 2. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        // 3. go over each row, build book and add it to list
//        DonorTO donorTO = null;
//        if (cursor.moveToFirst()) {
//            do {
//                donorTO = new DonorTO();
//                donorTO.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
//                donorTO.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
//                donorTO.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
//                donorTO.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
//                donorTO.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOODGROUP)));
//                donorTO.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE))));
//                donorTO.setNumberOfTimesDonated(cursor.getInt(cursor.getColumnIndex(KEY_DONATION_TIMES)));
//                donorTO.setDonationStatus(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_DONATION_STATUS))));
//                //Add donorTO to donors
//                donors.add(donorTO);
//            } while (cursor.moveToNext());
//        }
//        Log.d("getAllDonors()", donors.toString());
//        // return books
//        return donors;
//    }

    public List<DonorTO> fetchDonorsByBloodGroup(String bloodGroup){
        List<DonorTO> donors = new ArrayList<DonorTO>();
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        String query = "select * from "+TABLE_DONORS+" where "
                +KEY_BLOODGROUP +" = ?";
        Cursor cursor = db.rawQuery(query, new String[] {bloodGroup});
        // 3. go over each row, build book and add it to list
        Log.d("Cursor", cursor.toString());
        DonorTO donorTO = null;
        if (cursor.moveToFirst()) {
            do {
                donorTO = new DonorTO();
                donorTO.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE))));
                donorTO.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                donorTO.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                donorTO.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                donorTO.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
                donorTO.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOODGROUP)));
                donorTO.setAge(cursor.getInt(cursor.getColumnIndex(KEY_AGE)));
                donorTO.setNumberOfTimesDonated(cursor.getInt(cursor.getColumnIndex(KEY_DONATION_TIMES)));

                int status = cursor.getInt(cursor.getColumnIndex(KEY_DONATION_STATUS));
                if(status == 0)donorTO.setDonationStatus(false);
                else donorTO.setDonationStatus(true);

                // Add book to books
                donors.add(donorTO);
            } while (cursor.moveToNext());
        }
        Log.d("getAllDonors()", donors.toString());
        // return books
        return donors;
    }

    // Updating single Donor
    public int updateDonor(DonorTO donorTO) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, donorTO.getName());
        values.put(KEY_EMAIL, donorTO.getEmail());
        values.put(KEY_PHONE, donorTO.getPhone());
        values.put(KEY_AGE, donorTO.getAge());
        values.put(KEY_DONATION_STATUS, donorTO.getDonationStatus());
        values.put(KEY_DONATION_TIMES, donorTO.getNumberOfTimesDonated());
        // 3. updating row
        Integer i = db.update(TABLE_DONORS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(donorTO.getId()) }); //selection args
        // 4. close
        db.close();
        return i;
    }

    // Deleting single donor
    public void deleteDonor(DonorTO donorTO) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_DONORS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(donorTO.getId())});
        // 3. close
        db.close();
        Log.d("deleteDonor()", donorTO.toString());
    }
}
