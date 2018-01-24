package com.example.dell.contentproviderdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Dell on 23-Jan-18.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Contact> mListConTact;

    public ContactAdapter(Context context, ArrayList<Contact> listConTact) {
        mContext = context;
        mListConTact = listConTact;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mListConTact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName , mTextViewPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_Name);
            mTextViewPhone = itemView.findViewById(R.id.textview_phone);
        }

        public void setData(int position){
            Contact contact = mListConTact.get(position);
            mTextViewName.setText(contact.getName());
            mTextViewPhone.setText(contact.getPhoneNumber());
        }
    }
}
