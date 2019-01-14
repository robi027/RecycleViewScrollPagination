package com.example.robi.recycleviewscrollpagination.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robi on 1/14/2019.
 */

public class LaporanResponse extends BaseResponse {

    private int page, rowperpage;

    @SerializedName("data")
    private List<Laporan> laporans;

    public LaporanResponse(boolean error, String message) {
        super(error, message);
    }

    public List<Laporan> getLaporans() {
        return laporans;
    }

    public void setLaporans(List<Laporan> laporans) {
        this.laporans = laporans;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowperpage() {
        return rowperpage;
    }

    public void setRowperpage(int rowperpage) {
        this.rowperpage = rowperpage;
    }
}
