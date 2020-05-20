package app.technosk.roomapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technosk.roomapp.MODELS.Room_Connection_Repository;
import app.technosk.roomapp.R;
import app.technosk.roomapp.ROOM.EntitysRoom.EntityTable;


//Adaptador de item del RecyclerView
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<EntityTable> shoppingItems;


    //constructor que se llama solo cuando se crea la lista
    public ItemAdapter(List<EntityTable> items) {
        this.shoppingItems = items;
    }

    //Updates
    public void setData(List<EntityTable> items) {
        this.shoppingItems = items;
    }




    //se inicia el View Holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //se pinta el ROW los textos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //obtenemos un item en especifico
        EntityTable entityTable = shoppingItems.get(position);

        //pintamos los datos del nombre y de la cantidad
        holder.txtItemName.setText(entityTable.item);
        holder.txtItemQuantity.setText("Quantity: " + entityTable.quantity);
    }



    //regresa el valor de la lista
    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }



    //controlar los elementos del item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItemName;
        TextView txtItemQuantity;
        ImageView imageView;

        //constructor del item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemQuantity = itemView.findViewById(R.id.txt_item_quantity);
            imageView = itemView.findViewById(R.id.img_delete);

            imageView.setOnClickListener(this);
        }

        //on click en DELETE
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            EntityTable entityTable = shoppingItems.get(position);
            Room_Connection_Repository db = new Room_Connection_Repository(v.getContext());
            db.deleteItem(entityTable);
            Toast.makeText(v.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
        }
    }




}