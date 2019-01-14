package com.example.robi.recycleviewscrollpagination.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robi.recycleviewscrollpagination.R;
import com.example.robi.recycleviewscrollpagination.model.Laporan;

import java.util.List;

/**
 * Created by robi on 1/14/2019.
 */

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>{

    private List<Laporan> laporans;
    private Context context;

    public LaporanAdapter(List<Laporan> laporans, Context context) {
        this.laporans = laporans;
        this.context = context;
    }

    @Override
    public LaporanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        LaporanViewHolder laporanViewHolder = new LaporanViewHolder(view);
        return laporanViewHolder;
    }

    @Override
    public void onBindViewHolder(LaporanViewHolder holder, int position) {
        holder.tvId.setText(laporans.get(position).getId());
        holder.tvDeskripsi.setText(laporans.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return laporans.size();
    }

    public class LaporanViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvDeskripsi;

        public LaporanViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
        }
    }

    public void addLaporan(List<Laporan> laporanList){
        for (Laporan laporan : laporanList){
         laporans.add(laporan);
        }
        notifyDataSetChanged();
    }
}
