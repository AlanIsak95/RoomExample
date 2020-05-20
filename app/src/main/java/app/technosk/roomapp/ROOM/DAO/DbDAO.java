package app.technosk.roomapp.ROOM.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;


//Clase CRUD dentro de la base de datos.
//Data Access Object
//se usa Room y los objetos en los QUERIES son con la clase Entity Table que esta configurada para trabajar con ROOM
    @Dao
    public interface DbDAO {
        @Insert
        void insertItem(EntityTable... entityTables);//uno o varios

        @Query("SELECT * from EntityTable")
        LiveData< List<EntityTable> > getAllItems();//se agrega LIVEDATA para hacer que se actualice la lista cuando se agregue un nuevo ITEM

        @Query("SELECT COUNT(*) from  EntityTable")
        LiveData< Integer > countItems();

        @Delete
        void deleteItems(EntityTable entityTable);
    }
