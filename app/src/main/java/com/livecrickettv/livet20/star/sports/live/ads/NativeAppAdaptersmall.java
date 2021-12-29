package com.livecrickettv.livet20.star.sports.live.ads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.livecrickettv.livet20.star.sports.live.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class NativeAppAdaptersmall extends RecyclerView.Adapter<NativeAppAdaptersmall.MyViewHolder> {

    private Context context;
    private ArrayList<NativeApp> gunList;

    OnItemSelectListener onItemSelectListener;

    public interface OnItemSelectListener {

        void click(View view, NativeApp category);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public NativeAppAdaptersmall(Context context, ArrayList<NativeApp> gunList) {
        this.context = context;
        this.gunList = gunList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.native_customsmall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        Random random = new Random();
        int index = random.nextInt(gunList.size());
        final NativeApp nativeApp = gunList.get(index);
        Glide.with(context)
                .load(nativeApp.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivThumbnail);

        holder.iv_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemSelectListener.click(view, nativeApp);

            }
        });
        holder.setIsRecyclable(false);

        holder.btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemSelectListener.click(view, nativeApp);

            }
        });

    }

    @Override
    public int getItemCount() {
        return gunList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        Button btn_button;
        RelativeLayout iv_full;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.ads_image);

            iv_full = itemView.findViewById(R.id.iv_full);

            btn_button = itemView.findViewById(R.id.install);


        }
    }


    public void loadData(ArrayList<NativeApp> records) {
        this.gunList = records;
        notifyDataSetChanged();
    }
}
