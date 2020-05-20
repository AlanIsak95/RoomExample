package app.technosk.roomapp.ROOM.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import app.technosk.roomapp.ROOM.DAO.DbDAO;
import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;

@Database(entities = EntityTable.class, version = 1)
public abstract class RoomDataBase extends RoomDatabase {


    private static RoomDataBase roomDataBaseInstance;
    public static synchronized RoomDataBase getInstance(Context context){

        //Singleton para DB
        return (roomDataBaseInstance ==null)? roomDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(), RoomDataBase.class,"DBexample").build() : roomDataBaseInstance;
    }

    //damos el paso al crud de la DB
    //a esta clase se entra desde Repositorio de la clase (Room_Connection_Repository)
    public abstract DbDAO AccessToDAO();


}
