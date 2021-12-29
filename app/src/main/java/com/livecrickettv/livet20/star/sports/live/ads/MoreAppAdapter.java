package com.livecrickettv.livet20.star.sports.live.ads;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.livecrickettv.livet20.star.sports.live.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MoreAppAdapter extends RecyclerView.Adapter<MoreAppAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MoreApp> gunList;

    int code;
    OnItemSelectListener onItemSelectListener;


    public void loadData(ArrayList<MoreApp> records ,int value) {
        this.gunList = records;
        this.code=value;
        notifyDataSetChanged();
    }

    public interface OnItemSelectListener {

        void click(View view, MoreApp category);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public MoreAppAdapter(Context context, ArrayList<MoreApp> gunList) {
        this.context = context;
        this.gunList = gunList;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        final MoreApp moreApp = gunList.get(position);



        holder.tvName.setText(moreApp.getName());
        holder.tvName.setSelected(true);
//        holder.install_now.startAnimation(AnimationUtils.loadAnimation(context, R.anim.blink));


        Glide.with(context)
                .load(moreApp.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivThumbnail);

        holder.iv_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemSelectListener.click(view, moreApp);

            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = null;
        if (code==1) {
            view = LayoutInflater.from(context).inflate(R.layout.more_layout, parent, false);
            Log.e("12", "1: " );
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.more_layout_two, parent, false);
            Log.e("12", "2: " );
        }
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return gunList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName,install_now;
        ImageView iv_full;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            tvName = itemView.findViewById(R.id.tv_title);

            iv_full = itemView.findViewById(R.id.iv_full);
            install_now = itemView.findViewById(R.id.install_now);


        }
    }


}
