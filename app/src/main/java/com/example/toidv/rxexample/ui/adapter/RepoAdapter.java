package com.example.toidv.rxexample.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toidv.rxexample.R;
import com.example.toidv.rxexample.data.pojo.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TOIDV on 5/25/2016.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
    private static final List<Repo> repoList = new ArrayList<Repo>();

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.txtRepoName.setText(repo.getFullName());
        holder.txtRepoLanguage.setText(repo.getLanguage());
    }

    public void addRepo(Repo repo) {
        if (!repoList.contains(repo)) {
            repoList.add(repo);
            notifyItemInserted(repoList.size() - 1);
        } else {
            repoList.set(repoList.indexOf(repo), repo);
            notifyItemChanged(repoList.indexOf(repo));
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_repo_name)
        AppCompatTextView txtRepoName;

        @BindView(R.id.txt_repo_language)
        AppCompatTextView txtRepoLanguage;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
