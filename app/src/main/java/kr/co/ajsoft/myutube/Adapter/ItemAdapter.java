package kr.co.ajsoft.myutube.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    String newStr,result,numresult2;
    double numresult1;

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
        vh.date.setText("등록일 "+newDate(item.getDate()));
        vh.views.setText("조회수 "+count(item.getViews()));
        vh.publisher.setText(item.getPublisher());



        if(item.getImgUrl()==null){
            vh.itemIv.setVisibility(View.GONE);
        }else{
            vh.itemIv.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImgUrl()).into(vh.itemIv);

        }

    }

    String newDate(String str){

        newStr=str.replace('T',' ');
        result=newStr.substring(0,16);

        return result;
    }

    String count(String str){
        int num=Integer.parseInt(str);
        if(num<100){
            numresult2=num+"회";
            return numresult2;
        }else if(num>=100 && num<1000) {
            numresult1 = num * 0.001;
            numresult2 = String.format("%.1f", numresult1);
            return numresult2 + "천회";
        }else if(num>=1000 && num<10000){
            numresult1=num*0.0001;
            numresult2 = String.format("%.1f", numresult1);
            return numresult2 + "만회";

        }else if(num>=10000){
            numresult1=num*0.0001;
            numresult2=String.format("%.0f",numresult1);
            return numresult2+"만회";
        }else {
            return num+"";
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
        ImageButton addList;


        public VH(@NonNull View itemView) {
            super(itemView);

            itemTitle=itemView.findViewById(R.id.item_title);
            itemIv=itemView.findViewById(R.id.item_iv);
            date=itemView.findViewById(R.id.item_date);
            views=itemView.findViewById(R.id.item_views);
            publisher=itemView.findViewById(R.id.item_publisher);
            addList=itemView.findViewById(R.id.iv_add_list);

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
