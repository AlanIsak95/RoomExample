package app.technosk.roomapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.technosk.roomapp.MODELS.Room_Connection_Repository;
import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;


//View model solo maneja datos entre la UI y el Objeto que lo llena, aqui no se deberia de agregar Bussiness Rules
public class MainActivityViewModel extends AndroidViewModel {


    //datos a compartir
    private Room_Connection_Repository db;
    private LiveData<List<EntityTable>> items;
    private LiveData<Integer> itemCount;

    //constructor de AndroidViewModel
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        db = new Room_Connection_Repository(application.getApplicationContext());
        items = db.getItems();
        itemCount= db.getItemCount();
    }


    //CRUD VIEW MODEL

    //como en el constructor creamos nuestra referencia a la base de datos
    public void insertNewItem(EntityTable entityTable){ db.addNewItem(entityTable); }


    public void deleteItem(EntityTable entityTable){    db.deleteItem(entityTable);}
    public LiveData<Integer> getItemCount(){    return  itemCount;}
    public LiveData<List<EntityTable>> getItems(){  return items;}


}
