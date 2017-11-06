package taro.rikkeisoft.com.demonetworking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VjrutNAT on 11/6/2017.
 */

public class JObjectAdapter extends RecyclerView.Adapter<JObjectAdapter.JObjectHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<Object> mJObjects;
    private Context mContext;
    private Object mObject;

    public JObjectAdapter(ArrayList<Object> mJObjects, Context mContext) {
        this.mJObjects = mJObjects;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public JObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_objec, parent, false);
        return new JObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(JObjectHolder holder, int position) {
        mObject = mJObjects.get(position);
        holder.tvUserId.setText(String.valueOf(mObject.getUserId()));
        holder.tvTitle.setText(mObject.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mContext, DetailObject.class);
                intent.putExtra(Utils.KEY_OBJECT, mObject);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJObjects.size();
    }


    public class JObjectHolder extends RecyclerView.ViewHolder{

        TextView tvUserId;
        TextView tvTitle;

        public JObjectHolder(View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_user_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
