package app.technosk.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.technosk.roomapp.Adapters.ItemAdapter;
import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;

public class MainActivity extends AppCompatActivity {

    private  MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaramos el AndroidViewModel
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        Spinner spinner             = findViewById(R.id.spinner);
        TextView txtCount           = findViewById(R.id.txt_count);
        RecyclerView recyclerView   = findViewById(R.id.recyclerView);
        EditText etxtItem           = findViewById(R.id.editTx_new_item);
        Button button               = findViewById(R.id.btn_add);


        //DropDown Array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.item_count_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        //creamos una lista de items de la clase Entity
        List<EntityTable> entityTables = new ArrayList<>();

        //Creamos el Adaptador del Recycler
        ItemAdapter itemAdapter = new ItemAdapter(entityTables);

        //lo asignamos
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //raya below card
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));




        //LiveData que se puede usar gracias al ViewModel y a que declaramos las variables como tal
        mViewModel.getItems().observe(this, dbTables1 -> {
            //se actualiza el adaptador del Recycler
            itemAdapter.setData(dbTables1);
            //se notifica para que se actualice
            itemAdapter.notifyDataSetChanged();
        });


        //Live data para el contador
        mViewModel.getItemCount().observe(this,integer ->
                txtCount.setText(String.valueOf(integer))
        );







        //Onclick boton
        button.setOnClickListener(v -> {

            //obtenemos el valor del editText y del Spiner
            String name = etxtItem.getText().toString();
            String amount  = spinner.getSelectedItem().toString();

            //creamos una instancia de EntityTable
            EntityTable entityTable = new EntityTable();

            //lo inicializamos
            entityTable.item = name;
            entityTable.quantity = Integer.valueOf(amount);

            //eliminamos lo que tenga el Edit Text
            etxtItem.getText().clear();

            //con el ViewModel lo añadimos a la DB
            mViewModel.insertNewItem(entityTable);


        });





    }//OnCreateFin



}
