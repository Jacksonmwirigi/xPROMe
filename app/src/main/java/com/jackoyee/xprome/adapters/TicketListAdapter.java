package com.jackoyee.xprome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jackoyee.xprome.R;
import com.jackoyee.xprome.model.Tickets;

import java.util.List;

public class TicketListAdapter extends  RecyclerView.Adapter<TicketListAdapter.MyViewHolder>{

//    TextView asignertxt,asigneetxt,sttustxt,dispatch_idtxt,req_typetxt,date_asigntxt,clinttxt,phonetxt,residencetxt,comentstxt,
//            sched_datetxt,tickotxt,schd_timetxt,lst_updatetxt;

    private LinearLayout relativeLayout;
    private List<Tickets> dataList;
//    ItemClickListener itemClickListener;
    //private ArrayList<Tickets>dataList;
    private Context context;

    public TicketListAdapter(List<Tickets> dataList){
        this.dataList = dataList;
     }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_list,viewGroup,false);

        final MyViewHolder mViewHolder = new MyViewHolder(view);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // Toast.makeText(context, "Working on onClick Event", Toast.LENGTH_LONG).show();
//                //itemClickListener.onItemClick(v,mViewHolder.getPosition());
//               // Toast.makeText(context, "You clicked"+v.getId(), Toast.LENGTH_LONG).show();
//            }
//        });


        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView asignertxt,asigneetxt,sttustxt,dispatch_idtxt,req_typetxt,date_asigntxt,clinttxt,phonetxt,residencetxt,comentstxt,
                sched_datetxt,tickotxt,schd_timetxt,lst_updatetxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            relativeLayout=(LinearLayout)itemView.findViewById(R.id.relativeLayout);

            asigneetxt=(TextView)itemView.findViewById(R.id.asignee);
            asignertxt=(TextView)itemView.findViewById(R.id.asigner);
            sttustxt=(TextView)itemView.findViewById(R.id.ticket_status);
            dispatch_idtxt=(TextView)itemView.findViewById(R.id.dispatch_id);
            req_typetxt=(TextView)itemView.findViewById(R.id.request_type);
            date_asigntxt=(TextView)itemView.findViewById(R.id.asignment_date);
            clinttxt=(TextView)itemView.findViewById(R.id.client_tname);
            phonetxt=(TextView)itemView.findViewById(R.id.client_phone);
            residencetxt=(TextView)itemView.findViewById(R.id.client_residence);
            comentstxt=(TextView)itemView.findViewById(R.id.tickets_comment);
            sched_datetxt=(TextView)itemView.findViewById(R.id.scheduled_date);
            tickotxt=(TextView)itemView.findViewById(R.id.ticket_number);
            schd_timetxt=(TextView)itemView.findViewById(R.id.scheduled_time);
            lst_updatetxt=(TextView)itemView.findViewById(R.id.last_update);


        }
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        //Tickets ticketsResponse=dataList.


        myViewHolder.tickotxt.setText(dataList.get(position).getTICKET());
        myViewHolder.dispatch_idtxt.setText(dataList.get(position).getDISPATCH_ID());
        myViewHolder.req_typetxt.setText(dataList.get(position).getREQUEST_TYPE());
        myViewHolder.asigneetxt.setText(dataList.get(position).getASSIGNEE());
        myViewHolder.date_asigntxt.setText(dataList.get(position).getASSIGNMENT_DATE());
        myViewHolder.asignertxt.setText(dataList.get(position).getASSIGNER());
        myViewHolder.lst_updatetxt.setText(dataList.get(position).getLAST_UPDATE());
        myViewHolder.sttustxt.setText(dataList.get(position).getSTATUS());
        myViewHolder.comentstxt.setText(dataList.get(position).getCOMMENTS());
        myViewHolder.schd_timetxt.setText(dataList.get(position).getSCHEDULED_TIME_SLOT());
        myViewHolder.clinttxt.setText(dataList.get(position).getCLIENT_NAME());
        myViewHolder.residencetxt.setText(dataList.get(position).getCLIENT_RESIDENCE());
        myViewHolder.phonetxt.setText(dataList.get(position).getCLIENT_PHONE());
        myViewHolder.sched_datetxt.setText(dataList.get(position).getSCHEDULED_DATE());

    }

}
