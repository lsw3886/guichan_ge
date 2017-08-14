package lsw.guichange.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lsw38 on 2017-08-13.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int version = 1;

    public DBHelper(Context context){
        super(context, "guichangeDB", null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String choicedBulletin= "CREATE TABLE RecentBulletin(img INTEGER, category VARCHAR(20), name VARCHAR(20));";
        String bookMark = "CREATE TABLE Bookmark(sitename VARCHAR(20), link VARCHAR(100), comment VARCHAR(3), postnum VARCHAR(10), title VARCHAR(10), date VARCHAR(20), bimg INTEGER, btitle VARCHAR(5));";


        db.execSQL(choicedBulletin);
        db.execSQL(bookMark);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXSITS member");
        //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
        onCreate(db);
    }




}
