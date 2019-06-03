package com.limcg.mvvmsample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.limcg.mvvmsample.R;
import com.limcg.mvvmsample.models.People;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<People> peopleList;

    public MyRecyclerViewAdapter(Context context, List<People> peopleList)
    {
        this.context = context;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof ViewHolder)
        {
            // Set the profile name
            ((ViewHolder)viewHolder).mName.setText(peopleList.get(i).getProfileName());

            // Set the image
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.ic_launcher_background);
            Glide.with(context)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(peopleList.get(i).getProfileImgUrl())
                    .into(((ViewHolder)viewHolder).mImage);

        }

    }

    @Override
    public int getItemCount() {

        return peopleList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
