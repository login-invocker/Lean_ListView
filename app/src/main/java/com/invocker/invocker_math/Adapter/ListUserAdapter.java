package com.invocker.invocker_math.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.invocker.invocker_math.Model.UserScope;
import com.invocker.invocker_math.R;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UserHodler> {
    private Context mcontext;
    private List<UserScope> mlistUser;

    public ListUserAdapter(Context mcontext, List<UserScope> mlistUser) {
        this.mcontext = mcontext;
        this.mlistUser = mlistUser;
    }

    @NonNull
    @Override
    public UserHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_list_user, parent, false);
        return new UserHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHodler holder, int position) {
        UserScope userScope = mlistUser.get(position);
        holder.txtRank.setText(String.valueOf(getItemCount()));
        holder.txtPoint.setText(String.valueOf(userScope.getScope()));
        holder.txtName.setText(userScope.getName());
    }

    @Override
    public int getItemCount() {
        if (mlistUser != null)
            return mlistUser.size();
        else return 0;
    }

    class UserHodler extends RecyclerView.ViewHolder {
        private TextView txtRank;
        private TextView txtName;
        private TextView txtPoint;

        public UserHodler(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_play_name);
            txtPoint = itemView.findViewById(R.id.txt_points);
            txtRank = itemView.findViewById(R.id.txt_rank);
        }
    }
}
