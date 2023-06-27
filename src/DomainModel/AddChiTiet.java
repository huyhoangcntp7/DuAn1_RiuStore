/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author ACER
 */
public class AddChiTiet {
    private String id;
    private String idMaGiamGia;
    private String idChiTietGiay;
    private double soTienConLai;
    private int trangThai;

    public AddChiTiet() {
    }

    public AddChiTiet(String id, String idMaGiamGia, String idChiTietGiay, double soTienConLai, int trangThai) {
        this.id = id;
        this.idMaGiamGia = idMaGiamGia;
        this.idChiTietGiay = idChiTietGiay;
        this.soTienConLai = soTienConLai;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMaGiamGia() {
        return idMaGiamGia;
    }

    public void setIdMaGiamGia(String idMaGiamGia) {
        this.idMaGiamGia = idMaGiamGia;
    }

    public String getIdChiTietGiay() {
        return idChiTietGiay;
    }

    public void setIdChiTietGiay(String idChiTietGiay) {
        this.idChiTietGiay = idChiTietGiay;
    }

    public double getSoTienConLai() {
        return soTienConLai;
    }

    public void setSoTienConLai(double soTienConLai) {
        this.soTienConLai = soTienConLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
}
