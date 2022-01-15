package dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.R;
import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.model.Member;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Member> members;
    private Context context;
    public MainAdapter(Context context, ArrayList<Member> members)  {
        this.context=context;
        this.members=members;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call,parent,false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member =members.get(position);
        if (holder instanceof CallViewHolder){
            ((CallViewHolder) holder).profileImage.setImageResource(member.getImage());
            ((CallViewHolder) holder).textViewName.setText(member.getName());
            ((CallViewHolder) holder).textViewPhone.setText(member.getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class CallViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public RelativeLayout view_background,view_foreground;
        ImageView profileImage;
        TextView textViewName,textViewPhone;
        public CallViewHolder(@NonNull View view) {
            super(view);
            this.view=view;
            profileImage=view.findViewById(R.id.img_profile);
            textViewName=view.findViewById(R.id.tv_name);
            textViewPhone=view.findViewById(R.id.tv_phone);
            view_background=view.findViewById(R.id.view_background);
            view_foreground=view.findViewById(R.id.view_foreground);
        }
    }
}
