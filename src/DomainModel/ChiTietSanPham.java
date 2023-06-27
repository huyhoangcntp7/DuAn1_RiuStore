/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author admin
 */
public class ChiTietSanPham {
    private String maGiay;
    private String tenGiay;
    private String tenMauSac;
    private String size;
    private String tenChatLieu;
    private String hangGiay;
    private int soLuong;
    private double giaBan;

    public Object[] rowCTSP(){
        return new Object[]{maGiay,tenGiay, tenMauSac, size,tenChatLieu,hangGiay,soLuong,giaBan};
    }
    
    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maGiay, String tenGiay, String tenMauSac, String size, String tenChatLieu, String hangGiay, int soLuong, double giaBan) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.tenMauSac = tenMauSac;
        this.size = size;
        this.tenChatLieu = tenChatLieu;
        this.hangGiay = hangGiay;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public String getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(String maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getHangGiay() {
        return hangGiay;
    }

    public void setHangGiay(String hangGiay) {
        this.hangGiay = hangGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }


}
