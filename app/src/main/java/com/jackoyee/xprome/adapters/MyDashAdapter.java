package com.jackoyee.xprome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackoyee.xprome.model.Model;
import com.jackoyee.xprome.R;

import java.util.List;

public class MyDashAdapter extends RecyclerView.Adapter<MyDashAdapter.DashViewHolder>{

    private List<Model> ticketsList;
    private Context context;

    private static int currentPosition = 0;

    public MyDashAdapter(List<Model> ticketsList, Context context) {
        this.ticketsList = ticketsList;
        this.context = context;
    }

    @NonNull
    @Override
    public DashViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_feeddback,viewGroup,false);
        return new DashViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final DashViewHolder holder, final int position) {

        Model model=ticketsList.get(position);
        holder.textViewName.setText(model.getName());
        holder.textViewRealName.setText(model.getRealName());
        holder.textViewTeam.setText(model.getTeam());
        holder.textViewFirstAppearance.setText(model.getFirstAppearance());
       // holder.textViewCreatedBy.setText(model.getCreatedBy());
        holder.textViewPublisher.setText(model.getPublisher());
        holder.textViewBio.setText(model.getBio().trim());

        //Glide.with(context).load(model.getImageUrl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=position;
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return ticketsList.size();
    }
   class DashViewHolder extends RecyclerView.ViewHolder{

       TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
               textViewCreatedBy, textViewPublisher, textViewBio;
       ImageView imageView;
       LinearLayout linearLayout;
       public DashViewHolder(@NonNull View itemView) {
           super(itemView);
           textViewName = (TextView) itemView.findViewById(R.id.textViewName);
           textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
           textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
           textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);

           textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
           textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
//           imageView = (ImageView) itemView.findViewById(R.id.imageView);

           linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
       }
   }

}
