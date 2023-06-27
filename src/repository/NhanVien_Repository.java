/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Unility.JDBC_HELPER;
import viewModel.ChucVu;
import viewModel.NhanVienViewModel;

/**
 *
 * @author Admin
 */
public class NhanVien_Repository {
    public static List<NhanVien> getAllNhanVien(){
        List<NhanVien> listNV = new ArrayList<>();
        ResultSet rs;
        String sql = "select maNhanVien, hoTenNhanVien, ngaySinh, soDienThoai, diaChi, email, NhanVien.trangThai, NhanVien.matKhau, maChucVu, tenChucVu\n" +
                        "from NhanVien join ChucVu on NhanVien.idChucVu=ChucVu.id";
        rs = JDBC_HELPER.selectTongQuat(sql);
        
        try {
            while(rs.next()){
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                String sdt = rs.getString(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                int trangThai = rs.getInt(7);
                String mk = rs.getString(8);
                String maCV = rs.getString(9);
                String tenCV = rs.getString(10);
                ChucVu cv = new ChucVu(maCV, tenCV);
                NhanVien nv = new NhanVien(ma, ten, ngaySinh, sdt, diaChi, email, trangThai, mk, cv);
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static NhanVien getNVByMaNV1(String maNv){
        NhanVien nv = null;
        ResultSet rs;
        String sql = "select maNhanVien, hoTenNhanVien, ngaySinh, soDienThoai, diaChi, email, NhanVien.trangThai, NhanVien.matKhau, maChucVu, tenChucVu\n" +
                        "from NhanVien join ChucVu on NhanVien.idChucVu=ChucVu.id where NhanVien.maNhanVien = ?";
        rs = JDBC_HELPER.selectTongQuat(sql,maNv);
        
        try {
            while(rs.next()){
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                String sdt = rs.getString(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                int trangThai = rs.getInt(7);
                String mk = rs.getString(8);
                String maCV = rs.getString(9);
                String tenCV = rs.getString(10);
                ChucVu cv = new ChucVu(maCV, tenCV);
                nv = new NhanVien(ma, ten, ngaySinh, sdt, diaChi, email, trangThai, mk, cv);
                
            }
            return nv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public static String getNVByMaNV(String maNv) {
        String nv = null;
        ResultSet rs;
        String sql = "select id from nhanvien where maNhanVien=?";
        rs = JDBC_HELPER.selectTongQuat(sql, maNv);

        try {
            while (rs.next()) {
                nv = rs.getString(1);

            }
            return nv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int them(NhanVienViewModel sp){
        String sql = "insert into NhanVien(maNhanVien, hoTenNhanVien, ngaySinh, soDienThoai, diaChi, email, trangthai, matKhau, idChucVu) values (?,?,?,?,?,?,?,?,?)";
        return JDBC_HELPER.updateTongQuat(sql, sp.getMa(), sp.getHoTen(), sp.getNgaySinh(), sp.getSoDienThoai(), sp.getDiaChi(), sp.getEmail(), sp.getTrangThai(), sp.getMatKhau(), sp.getChucVu().getId());
    }
    
    public static int sua(NhanVienViewModel sp){
        String sql = "update NhanVien set hoTenNhanVien=?, ngaySinh=?, soDienThoai=?, diaChi=?, email=?, trangthai=?, matKhau= ?, idChucVu=? where maNhanVien = ?";
        return JDBC_HELPER.updateTongQuat(sql, sp.getHoTen(), sp.getNgaySinh(), sp.getSoDienThoai(), sp.getDiaChi(), sp.getEmail(), sp.getTrangThai(), sp.getMatKhau(), sp.getChucVu().getId(), sp.getMa());
    }
    
    public static int xoa(NhanVienViewModel sp){
        String sql = "delete from NhanVien where maNhanVien = ?";
        return JDBC_HELPER.updateTongQuat(sql, sp.getMa());
    }
    
    public static boolean checkTrungMa(String manv) {
        NhanVien gv = getNVByMaNV1(manv);
        if (gv == null) {
            return false;
        } else {
            return true;
        }
    }
}
