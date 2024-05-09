package sg.edu.np.mad.madpractical4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    private ArrayList<User> list_objects;
    private ListActivity activity;
    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_objects = list_objects;
        this.activity = activity;
    }
    //Method to create a view holder for a username
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    // Method to bind username to a viewholder
    public void onBindViewHolder(UserViewHolder holder, int position){
        // get position of a username
        User list_items = list_objects.get(position);
        // set username to the view hodler based on custom_activity_list.xml
        holder.name.setText(list_items.getName());
        // set description to the view holder based on custom_activity_list.xml
        holder.description.setText(list_items.getDescription());
        // if name ends with 7 show largeImage
        if(String.valueOf(list_items.name).endsWith("7")){
            holder.largeImage.setVisibility(View.VISIBLE);
        }
        // configure SetOnClickListener for the the small image on the view holder based on custom_activity_list.xml
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Profile")
                        .setMessage(list_items.name)
                        .setCancelable(true)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent mainAcitvity = new Intent(activity, MainActivity.class);
                                mainAcitvity.putExtra("name", list_items.getName());
                                mainAcitvity.putExtra("description", list_items.getDescription());
                                mainAcitvity.putExtra("followed", list_items.getFollowed());
                                v.getContext().startActivity(mainAcitvity);
                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){return list_objects.size();}
}
