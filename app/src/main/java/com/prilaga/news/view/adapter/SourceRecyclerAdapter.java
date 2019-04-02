//package com.prilaga.news.view.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import com.sonejka.news.R;
//import com.sonejka.news.mvp.model.Source;
//import com.sonejka.news.mvp.view.widget.SourceCardView;
//
//import javax.inject.Inject;
//import java.util.List;
//
///**
// * Created by Oleg Tarashkevich on 01.04.17.
// */
//
//public class SourceRecyclerAdapter extends BaseRecyclerAdapter<Source.Entry, SourceRecyclerAdapter.SourceViewHolder> {
//
//    private final int DEFAULT_POSITION = -1;
//    private int selectedSource = DEFAULT_POSITION;
//
//    @Inject
//    public SourceRecyclerAdapter() {
//    }
//
//    @Override
//    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new SourceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false));
//    }
//
//    @Override
//    protected void onBindViewHolder(SourceViewHolder holder, Source.Entry entry, final int position) {
//        holder.cardView.setSource(entry);
//        holder.cardView.setSelected(selectedSource == position);
//        holder.cardView.setCardSelection(new SourceCardView.CardSelection() {
//            @Override
//            public void onSelected(Source.Entry selectedEntry) {
//                // deselect view
//                notifyItemChanged(selectedSource);
//                selectedSource = position;
//                // select view
//                notifyItemChanged(selectedSource);
//            }
//        });
//    }
//
//    @Override
//    public void setData(List<Source.Entry> data) {
//        selectedSource = DEFAULT_POSITION;
//        super.setData(data);
//    }
//
//    static class SourceViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.source_card_view) SourceCardView cardView;
//
//        public SourceViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//
//
//}