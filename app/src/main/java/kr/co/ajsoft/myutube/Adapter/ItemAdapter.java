package kr.co.ajsoft.myutube.Adapter;

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

import java.util.ArrayList;

import kr.co.ajsoft.myutube.ItemActivity;
import kr.co.ajsoft.myutube.Model.Item;
import kr.co.ajsoft.myutube.R;

public class ItemAdapter extends RecyclerView.Adapter {

    ArrayList<Item> items;
    Context context;

    public ItemAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        VH vh=new VH(itemview);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Item item=items.get(position);
        vh.itemTitle.setText(item.getTitle());
        vh.date.setText(item.getDate());
        vh.views.setText(item.getViews());

        if(item.getImgUrl()==null){
            vh.itemIv.setVisibility(View.GONE);
        }else{
            vh.itemIv.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImgUrl()).into(vh.itemIv);

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

        TextView itemTitle;
        ImageView itemIv;
        TextView date;
        TextView views;
        TextView publisher;

        public VH(@NonNull View itemView) {
            super(itemView);

            itemTitle=itemView.findViewById(R.id.item_title);
            itemIv=itemView.findViewById(R.id.item_iv);
            date=itemView.findViewById(R.id.item_date);
            views=itemView.findViewById(R.id.item_views);
            publisher=itemView.findViewById(R.id.item_publisher);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=items.get(getLayoutPosition()).getId();
                    //String title=items.get(getLayoutPosition()).getTitle();

                    Intent intent=new Intent(context, ItemActivity.class);
                    intent.putExtra("Id",id);
                    //intent.putExtra("Title",title);

                    context.startActivity(intent);
                }
            });

        }
    }

}
