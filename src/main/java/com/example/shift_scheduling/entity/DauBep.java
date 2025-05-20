package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiMon;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class DauBep extends NhanVien {

    private int kinhNghiem;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<LoaiMon> dsLoaiMonAn;

    public DauBep(int kinhNghiem, List<LoaiMon> dsLoaiMonAn) {
        this.kinhNghiem = kinhNghiem;
        this.dsLoaiMonAn = dsLoaiMonAn;
    }

    public DauBep(String hoTen, String soDienThoai, String diaChi, Gender gioiTinh, int kinhNghiem, List<LoaiMon> dsLoaiMonAn) {
        super(hoTen, soDienThoai, diaChi, gioiTinh);
        this.kinhNghiem = kinhNghiem;
        this.dsLoaiMonAn = dsLoaiMonAn;
    }

    public int getKinhNghiem() {
        return kinhNghiem;
    }

    public List<LoaiMon> getDsLoaiMonAn() {
        return dsLoaiMonAn;
    }

    public void setKinhNghiem(int kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public void setDsLoaiMonAn(List<LoaiMon> dsLoaiMonAn) {
        this.dsLoaiMonAn = dsLoaiMonAn;
    }
}
