package com.example.newsbychoice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder> {
private Context context;
private ClickInterface clickInterface;
    private ArrayList<model> data;
    public interface ClickInterface{
        public void onClickopen(int position);
    }

    public myAdapter(Context context, ArrayList<model> data,ClickInterface clickInterface) {
        this.context = context;
        this.data = data;
        this.clickInterface=clickInterface;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new  myAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
model item=data.get(position);
           String Image=item.getImage();
           String Title=item.getTitle();
           String Description=item.getDescription();
           String Source=item.getSource();
           holder.source.setText(Source);
           String link=item.getLink();
           holder.description.setText(Description);
           holder.title.setText(Title);
        Glide.with(context).load(Image).into(holder.image);
        holder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,Title+"\n"+link);
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onClickopen(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
    ImageView image,Share;
    TextView source,title,description;

    public viewHolder(@NonNull View itemView)  {
        super(itemView);
        image=itemView.findViewById(R.id.image);
        source=itemView.findViewById(R.id.source);
        title=itemView.findViewById(R.id.title);
        description=itemView.findViewById(R.id.description);
        Share=itemView.findViewById(R.id.Share);
    }
}
}
