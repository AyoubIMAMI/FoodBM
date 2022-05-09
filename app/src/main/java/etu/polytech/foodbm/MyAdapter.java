package etu.polytech.foodbm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<VoucherInfo> list;


    public MyAdapter(Context context, ArrayList<VoucherInfo> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       VoucherInfo user = list.get(position);
        holder.couponN.setText(user.getVName());
        holder.coupnDesc.setText(user.getDescription());
        holder.DateEx.setText(user.getVDate());
        if(user.getImageID()!=null) {
            byte[] decodedString = Base64.decode(user.getImageID(), Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.imageV.setImageBitmap(decodedByte);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView couponN, coupnDesc, DateEx;
        ImageView imageV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            couponN = itemView.findViewById(R.id.vName);
            coupnDesc = itemView.findViewById(R.id.vdescription);
            DateEx = itemView.findViewById(R.id.vdate);
            imageV=itemView.findViewById(R.id.imageView8);

        }
    }

}