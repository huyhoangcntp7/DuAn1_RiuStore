/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author admin
 */
public class ChiTietGiamGia {
    private String MaGiamGia;
    private String maGiay;
    private String TenSanPham;
    private String NgayBatDau;
    private String NgayKetThuc;
    private double SoTienConLai;
    private int trangThai;

    public ChiTietGiamGia() {
    }

    public ChiTietGiamGia(String MaGiamGia, String maGiay, String TenSanPham, String NgayBatDau, String NgayKetThuc, double SoTienConLai, int trangThai) {
        this.MaGiamGia = MaGiamGia;
        this.maGiay = maGiay;
        this.TenSanPham = TenSanPham;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.SoTienConLai = SoTienConLai;
        this.trangThai = trangThai;
    }

    public String getMaGiamGia() {
        return MaGiamGia;
    }

    public void setMaGiamGia(String MaGiamGia) {
        this.MaGiamGia = MaGiamGia;
    }

    public String getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(String maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public double getSoTienConLai() {
        return SoTienConLai;
    }

    public void setSoTienConLai(double SoTienConLai) {
        this.SoTienConLai = SoTienConLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
    public Object[] CTGG(){
        return new Object[]{MaGiamGia,TenSanPham, NgayBatDau, NgayKetThuc, SoTienConLai, trangThai};
    };

    

}
