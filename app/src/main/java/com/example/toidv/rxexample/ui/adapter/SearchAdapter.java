package com.example.toidv.rxexample.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.toidv.rxexample.R;
import com.example.toidv.rxexample.consts.Consts;
import com.example.toidv.rxexample.data.pojo.Item;
import com.example.toidv.rxexample.ui.repo.ReposActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TOIDV on 5/25/2016.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final List<Item> userList = new ArrayList<Item>();
    private Context context;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = userList.get(position);
        Glide.with(holder.imgUser.getContext()).load(item.getAvatarUrl()).into(holder.imgUser);
        holder.txtUserName.setText(item.getLogin());
        holder.txtScore.setText(item.getScore().toString());
        holder.cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReposActivity.class);
                intent.putExtra(Consts.REPO_URL, item.getReposUrl());
                intent.putExtra(Consts.USER_NAME, item.getLogin());
                context.startActivity(intent);
            }
        });


    }

    public void addItem(Item item) {
        if (!userList.contains(item)) {
            userList.add(item);
            notifyItemInserted(userList.size() - 1);
        } else {
            userList.set(userList.indexOf(item), item);
            notifyItemChanged(userList.indexOf(item));
        }
    }

    public void reset() {
        userList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_user)
        CircleImageView imgUser;

        @BindView(R.id.txt_user_name)
        AppCompatTextView txtUserName;

        @BindView(R.id.txt_score)
        AppCompatTextView txtScore;

        @BindView(R.id.card_user)
        CardView cardUser;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
