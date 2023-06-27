package DomainModel;

import java.util.Date;

public class MaGiamGia {

//    private String id;
    private String maGiamGia;
    private  int phanTramGiam;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayketThuc;
    private String dieuKienGiamGia;
    private int trangThai;

    public MaGiamGia() {
    }

    public MaGiamGia( String maGiamGia, int phanTramGiam, int soLuong, Date ngayBatDau, Date ngayketThuc, String dieuKienGiamGia, int trangThai) {
//        this.id = id;
        this.maGiamGia = maGiamGia;
        this.phanTramGiam = phanTramGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayketThuc = ngayketThuc;
        this.dieuKienGiamGia = dieuKienGiamGia;
        this.trangThai = trangThai;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayketThuc() {
        return ngayketThuc;
    }

    public void setNgayketThuc(Date ngayketThuc) {
        this.ngayketThuc = ngayketThuc;
    }

    public String getDieuKienGiamGia() {
        return dieuKienGiamGia;
    }

    public void setDieuKienGiamGia(String dieuKienGiamGia) {
        this.dieuKienGiamGia = dieuKienGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    public Object[] toRow() {
        return new Object[]{maGiamGia, phanTramGiam, soLuong, ngayBatDau, ngayketThuc, dieuKienGiamGia, trangThai};
    }
   
}
