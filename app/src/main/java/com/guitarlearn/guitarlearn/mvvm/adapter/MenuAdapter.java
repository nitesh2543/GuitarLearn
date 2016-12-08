package com.guitarlearn.guitarlearn.mvvm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guitarlearn.guitarlearn.R;
import com.guitarlearn.guitarlearn.common.RecyclerViewEvents;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nitesh on 12/8/2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holder> {

    private final LayoutInflater layoutInflater;
    private final List<String> list;
    private final RecyclerViewEvents.Listener<String> listener;
    private int selectedPosition = -1;


    public MenuAdapter(Context context, List<String> itemList, RecyclerViewEvents.Listener<String> listener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = itemList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_menu_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String item = list.get(position);
        holder.title.setText(item);
        holder.itemView.setActivated((position == selectedPosition) ? true : false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.menu_title);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(list.get(getAdapterPosition()), v, getAdapterPosition());
        }

    }

    public String getItem(int position) {
        return list.get(position);
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}




