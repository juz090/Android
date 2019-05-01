package com.example.marcus.grocerylist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);

        }

    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, viewGroup, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder groceryViewHolder, int i) {
        if (!mCursor.moveToPosition(i)){
            return;
        }

        final String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        final int quantity = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        final String catagory = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_CATAGORY));
        final long id = mCursor.getLong(mCursor.getColumnIndex(GroceryContract.GroceryEntry._ID));

        groceryViewHolder.nameText.setText(name.trim());
        groceryViewHolder.countText.setText(String.valueOf(quantity));
        groceryViewHolder.itemView.setTag(id);

        groceryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent showClass = new Intent(mContext, ViewItem.class);

                //Sending data to another Activity
                showClass.putExtra("name", name);
                showClass.putExtra("quantity",quantity);
                showClass.putExtra("catagory", catagory);
                showClass.putExtra("id", id);
                mContext.startActivity(showClass);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){

        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }

}
