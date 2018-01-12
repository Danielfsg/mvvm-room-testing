package pt.dw.room_livedata_viewmodel.listitems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import pt.dw.room_livedata_viewmodel.R;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<BorrowModel> borrowModelList;
    private View.OnLongClickListener longClickListener;
    private View.OnClickListener onClickListener;

    RecyclerViewAdapter(List<BorrowModel> borrowModelList,
                        View.OnLongClickListener longClickListener,
                        View.OnClickListener onClickListener) {
        this.borrowModelList = borrowModelList;
        this.longClickListener = longClickListener;
        this.onClickListener = onClickListener;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        BorrowModel borrowModel = borrowModelList.get(position);

        DateFormat format = DateFormat.getDateInstance();

        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getPersonName());
        holder.dateTextView.setText(format.format(borrowModel.getBorrowDate()));
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnLongClickListener(longClickListener);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return borrowModelList == null ? 0 : borrowModelList.size();
    }

    void addItems(List<BorrowModel> borrowModelList){
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTextView, nameTextView, dateTextView;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
