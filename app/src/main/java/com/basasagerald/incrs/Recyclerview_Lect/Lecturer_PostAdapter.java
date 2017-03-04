package com.basasagerald.incrs.Recyclerview_Lect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.basasagerald.incrs.R;

import java.util.List;

/**
 * Created by basasagerald on 3/2/2017.
 */

public class Lecturer_PostAdapter extends RecyclerView.Adapter<Lecturer_PostAdapter.MyViewHolder> {

    private List<Lecturer_Model> postsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, question, answer;
        public ImageView postIcon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            question = (TextView) view.findViewById(R.id.question);
            answer = (TextView) view.findViewById(R.id.answer);
            postIcon = (ImageView) view.findViewById(R.id.post_icon);
        }
    }

    public Lecturer_PostAdapter(List<Lecturer_Model> postsList) {
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_lecturer, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lecturer_Model post = postsList.get(position);
        holder.title.setText(post.getTitle());
        holder.question.setText(post.getQuestion());
        holder.answer.setText("Answer");

        if(post.getTitle().startsWith("a") || post.getTitle().startsWith("A"))
            holder.postIcon.setImageResource(R.mipmap.a);
        else if (post.getTitle().startsWith("b") || post.getTitle().startsWith("B"))
            holder.postIcon.setImageResource(R.mipmap.b);
        else if (post.getTitle().startsWith("c") || post.getTitle().startsWith("C"))
            holder.postIcon.setImageResource(R.mipmap.c);
        else if (post.getTitle().startsWith("d") || post.getTitle().startsWith("D"))
            holder.postIcon.setImageResource(R.mipmap.d);

        else if (post.getTitle().startsWith("e") || post.getTitle().startsWith("E"))
            holder.postIcon.setImageResource(R.mipmap.e);
        else if (post.getTitle().startsWith("f") || post.getTitle().startsWith("F"))
            holder.postIcon.setImageResource(R.mipmap.f);
        else if (post.getTitle().startsWith("g") || post.getTitle().startsWith("G"))
            holder.postIcon.setImageResource(R.mipmap.g);
        else if (post.getTitle().startsWith("h") || post.getTitle().startsWith("H"))
            holder.postIcon.setImageResource(R.mipmap.h);
        else if (post.getTitle().startsWith("i") || post.getTitle().startsWith("I"))
            holder.postIcon.setImageResource(R.mipmap.i);
        else if (post.getTitle().startsWith("j") || post.getTitle().startsWith("J"))
            holder.postIcon.setImageResource(R.mipmap.j);
        else if (post.getTitle().startsWith("k") || post.getTitle().startsWith("K"))
            holder.postIcon.setImageResource(R.mipmap.k);
        else if (post.getTitle().startsWith("l") || post.getTitle().startsWith("L"))
            holder.postIcon.setImageResource(R.mipmap.l);
        else if (post.getTitle().startsWith("m") || post.getTitle().startsWith("M"))
            holder.postIcon.setImageResource(R.mipmap.m);
        else if (post.getTitle().startsWith("n") || post.getTitle().startsWith("N"))
            holder.postIcon.setImageResource(R.mipmap.n);
        else if (post.getTitle().startsWith("o") || post.getTitle().startsWith("O"))
            holder.postIcon.setImageResource(R.mipmap.o);
        else if (post.getTitle().startsWith("p") || post.getTitle().startsWith("P"))
            holder.postIcon.setImageResource(R.mipmap.p);
        else if (post.getTitle().startsWith("q") || post.getTitle().startsWith("Q"))
            holder.postIcon.setImageResource(R.mipmap.q);
        else if (post.getTitle().startsWith("r") || post.getTitle().startsWith("R"))
            holder.postIcon.setImageResource(R.mipmap.r);
        else if (post.getTitle().startsWith("s") || post.getTitle().startsWith("S"))
            holder.postIcon.setImageResource(R.mipmap.s);
        else if (post.getTitle().startsWith("t") || post.getTitle().startsWith("T"))
            holder.postIcon.setImageResource(R.mipmap.t);
        else if (post.getTitle().startsWith("u") || post.getTitle().startsWith("U"))
            holder.postIcon.setImageResource(R.mipmap.u);
        else if (post.getTitle().startsWith("v") || post.getTitle().startsWith("V"))
            holder.postIcon.setImageResource(R.mipmap.v);
        else if (post.getTitle().startsWith("w") || post.getTitle().startsWith("W"))
            holder.postIcon.setImageResource(R.mipmap.w);
        else if (post.getTitle().startsWith("x") || post.getTitle().startsWith("X"))
            holder.postIcon.setImageResource(R.mipmap.x);
        else if (post.getTitle().startsWith("y") || post.getTitle().startsWith("Y"))
            holder.postIcon.setImageResource(R.mipmap.y);
        else if (post.getTitle().startsWith("z") || post.getTitle().startsWith("Z"))
            holder.postIcon.setImageResource(R.mipmap.z);
        else
            holder.postIcon.setImageResource(R.mipmap.a);
    }
    @Override
    public int getItemCount() {
        return postsList.size();
    }

}
