package com.spoton.serveio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spoton.serveio.R;
import com.spoton.serveio.model.MessageModel;
import com.spoton.serveio.model.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    ArrayList<MessageModel> array_messages;
    Context context;

    public MessageAdapter(Context context,ArrayList<MessageModel> array_messages) {
        this.array_messages = array_messages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(array_messages.get(position).getType().equals("s")){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case 0 : View view1 = inflater.inflate(R.layout.layout_message_sent,parent,false);
                return new MessageAdapter.MyViewHolder(view1);
            case 1: View view2 = inflater.inflate(R.layout.layout_message_received,parent,false);
                return new MessageAdapter.MyViewHolder(view2);
            default: View view3 = inflater.inflate(R.layout.layout_message_sent,parent,false);
                return new MessageAdapter.MyViewHolder(view3);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        holder.message.setText(array_messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return array_messages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.tv_message);
        }
    }
}
