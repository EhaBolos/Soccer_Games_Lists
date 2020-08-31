package com.example.projectsofsoccerplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdapterDB {
    DB myDb;

    public AdapterDB(Context context) {
        myDb = new DB(context);
    }

    public long insertData(String City, String Date, String A, String B) {
        SQLiteDatabase dbb = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.Col_1, City);
        contentValues.put(DB.DCol_2, Date);
        contentValues.put(DB.Col_3, A);
        contentValues.put(DB.Col_4, B);

        return dbb.insert(DB.TABLE_NAME, null, contentValues);
    }

    public String getData()
    {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String[] columns = {DB.ID, DB.Col_1, DB.DCol_2, DB.Col_3, DB.Col_4};
        Cursor cursor = db.query(DB.TABLE_NAME, columns, null , null, null, null, null);
        StringBuilder buffer = new StringBuilder();
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(cursor.getColumnIndex(DB.ID));
            String City = cursor.getString(cursor.getColumnIndex(DB.Col_1));
            String Date = cursor.getString(cursor.getColumnIndex(DB.DCol_2));
            String A = cursor.getString(cursor.getColumnIndex(DB.Col_3));
            String B = cursor.getString(cursor.getColumnIndex(DB.Col_4));

            buffer.append(cid).append("   ").append(City).append("   ").append(Date).append("   ").append(A).append("   ").append(B).append(" \n");
        }
        return buffer.toString();
    }
    public String getData(String Date)
    {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String[] columns = {DB.ID, DB.Col_1, DB.DCol_2, DB.Col_3, DB.Col_4};
        Cursor cursor = db.rawQuery("SELECT * FROM GamesList_Table WHERE Date = ? ",new String[] {Date},  null);
        StringBuilder buffer = new StringBuilder();
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(cursor.getColumnIndex(DB.ID));
            String City = cursor.getString(cursor.getColumnIndex(DB.Col_1));
            String D = cursor.getString(cursor.getColumnIndex(DB.DCol_2));
            String A = cursor.getString(cursor.getColumnIndex(DB.Col_3));
            String B = cursor.getString(cursor.getColumnIndex(DB.Col_4));

            buffer.append(cid).append("   ").append(City).append("   ").append(D).append("   ").append(A).append("   ").append(B).append(" \n");
           }


        if(buffer==null)
        {
            buffer.append("No info at this date");
        }
        return buffer.toString();
    }
    public String getDataForTeam(String Name)
    {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String[] columns = {DB.ID, DB.Col_1, DB.DCol_2, DB.Col_3, DB.Col_4};
        Cursor cursor = db.rawQuery("SELECT * FROM GamesList_Table WHERE TeamA = ? OR TeamB = ?",new String[] {Name},  null);
        StringBuilder buffer = new StringBuilder();
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(cursor.getColumnIndex(DB.ID));
            String City = cursor.getString(cursor.getColumnIndex(DB.Col_1));
            String D = cursor.getString(cursor.getColumnIndex(DB.DCol_2));
            String A = cursor.getString(cursor.getColumnIndex(DB.Col_3));
            String B = cursor.getString(cursor.getColumnIndex(DB.Col_4));

            buffer.append(cid).append("   ").append(City).append("   ").append(D).append("   ").append(A).append("   ").append(B).append(" \n");
        }
        if(buffer==null)
        {
            buffer.append("No info at this date");
        }
        return buffer.toString();
    }
    public  int delete(String ID) {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String[] whereArgs ={ID};
        return db.delete(DB.TABLE_NAME ,DB.ID+" = ?",whereArgs);
    }
    public int updateDate(String oldD, String newD,String ID)
    {
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.DCol_2,newD);
        String[] whereArgs= {oldD,ID};
        int count =db.update(DB.TABLE_NAME,contentValues, DB.DCol_2+" = ? And "+ DB.ID+ "= ?",whereArgs);
        return count;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public class DB extends SQLiteOpenHelper {
        public static  final String DB_Name ="soccerGames";
        public static  final String TABLE_NAME ="GamesList_Table";
        private static final int DATABASE_Version = 1;
        public static final String ID="ID";
        public static  final String Col_1 ="City";
        public static  final String DCol_2 ="Date";
        public static  final String Col_3 ="TeamA";
        public static  final String Col_4 ="TeamB";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Col_1+" VARCHAR(225), "+DCol_2+" VARCHAR(255) ,"+ Col_3+" VARCHAR(225), "+Col_4+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME; private Context context;

        public DB(Context context) {
            super(context,DB_Name,null,DATABASE_Version);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try { db.execSQL(CREATE_TABLE); } catch (Exception e) {
                ShowMessage.message(context,""+e); }
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try { ShowMessage.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                ShowMessage.message(context,""+e);
            }
        }

    }

}
