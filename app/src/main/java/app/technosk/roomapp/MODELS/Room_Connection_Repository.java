package app.technosk.roomapp.MODELS;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.technosk.roomapp.ROOM.DataBase.RoomDataBase;
import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;


//Clase que se conecta con la base de datos.
public class Room_Connection_Repository {

    private Context context;

    public Room_Connection_Repository(Context context) {
        this.context = context.getApplicationContext();
    }


    //CRUD de la tabla Entity
    public void addNewItem(EntityTable entityTable){

        //new Runneable(){} se cambia por lambda expresion
        //se ejecuta en Hilo background
        //se obtiene una instancia por Singleton que nos permitira usar su clase AccessToDAO y a su vez a inserItem.
        AsyncTask.execute(()-> RoomDataBase.getInstance(context).AccessToDAO().insertItem(entityTable));

    }

    public LiveData<List<EntityTable>>getItems(){

        //LiveData corre en background, por eso no es necesario agregarlo a un Asyntask
        return RoomDataBase.getInstance(context).AccessToDAO().getAllItems();

    }

    public LiveData<Integer>getItemCount(){

        return RoomDataBase.getInstance(context).AccessToDAO().countItems();

    }

    public void deleteItem(EntityTable entityTable){

        AsyncTask.execute(()-> RoomDataBase.getInstance(context).AccessToDAO().deleteItems(entityTable));

    }

}
