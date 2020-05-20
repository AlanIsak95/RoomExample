package app.technosk.roomapp.ROOM.EntitysRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Tabla Entity configurada para ser usada con Room
@Entity
public class EntityTable {

    //PK autogenerada
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String item;
    public int quantity;

}
