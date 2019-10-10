package com.codebillionz.android.hng_internship_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.codebillionz.android.hng_internship_app.Models.Members;
import com.codebillionz.android.hng_internship_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersVH> {

    private List<Members> membersList;
    private OnItemClickListener mListener;
    private Context context;

    public MembersAdapter(List<Members> membersList, Context context) {
        this.membersList = membersList;
        this.context = context;
    }

    @NonNull
    @Override
    public MembersAdapter.MembersVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.members_list_item, parent, false);
        return new MembersAdapter.MembersVH(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MembersAdapter.MembersVH holder, final int position) {
//        Picasso.get()
//                .load("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS")
//                .into(holder.membersPic);
        holder.membersName.setText(membersList.get(position).getUsersName());
        holder.memberOD.setText(membersList.get(position).getWhatYouDo());
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    public void setOnItemClickListener(MembersAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void clickedMe(int position);
    }

    class MembersVH extends RecyclerView.ViewHolder {
        ConstraintLayout memberClicked = itemView.findViewById(R.id.clickedMember);
        TextView membersName, memberOD;
        CircleImageView membersPic;

        MembersVH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            membersPic = itemView.findViewById(R.id.members_image);
            membersName = itemView.findViewById(R.id.members_name);
            memberOD = itemView.findViewById(R.id.members_od);


            memberClicked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.clickedMe(position);
                        }
                    }
                }
            });

        }

    }

}
