/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.ChiTietSanPham;
import DomainModel.MaGiamGia;
import repository.impl.GiamGiaRepository;
import Unility.DBContext;
import Unility.DBContext_1;
import Unility.JDBC_HELPER;
import Unility.JDBC_Helper_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class GiamGiaRepositoryImpl implements GiamGiaRepository {

    @Override
    public List<MaGiamGia> getAll() {
        String query = "SELECT [maGiamGia]\n"
                + "      ,[phanTramGiam]\n"
                + "      ,[soLuong]\n"
                + "      ,[ngayBatDau]\n"
                + "      ,[ngayKetThuc]\n"
                + "      ,[dieuKienGiamGia]\n"
                + "      ,[trangThai]\n"
                + "  FROM [dbo].[GiamGia]";
        try ( Connection con = DBContext_1.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<MaGiamGia> listMa = new ArrayList<>();
            while (rs.next()) {
                MaGiamGia ma = new MaGiamGia(rs.getString(1),
                        rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getInt(7));
                listMa.add(ma);
            }
            return listMa;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    @Override
    public MaGiamGia getAllByMa(String maGG) {
        MaGiamGia gg = null;
        String query = "SELECT [maGiamGia]\n"
                + "      ,[phanTramGiam]\n"
                + "      ,[soLuong]\n"
                + "      ,[ngayBatDau]\n"
                + "      ,[ngayKetThuc]\n"
                + "      ,[dieuKienGiamGia]\n"
                + "      ,[trangThai]\n"
                + "  FROM [dbo].[GiamGia] where maGiamGia = ?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(query, maGG);
        try {
            while(rs.next()){
                String ma = rs.getString("maGiamGia");
                int phanTram = rs.getInt("phanTramGiam");
                int soLuong = rs.getInt("soLuong");
                Date NBD = rs.getDate("ngayBatDau");
                Date NKT = rs.getDate("ngayKetThuc");
                String dieuKien = rs.getString("dieuKienGiamGia");
                int trangThai = rs.getInt("trangThai");
                gg = new MaGiamGia(ma, phanTram, soLuong, NBD, NKT, dieuKien, trangThai);
            }
            return gg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addMa(MaGiamGia ma) {
        int check = 0;
        String query = "INSERT INTO [dbo].[GiamGia]\n"
                + "           ([maGiamGia]\n"
                + "           ,[phanTramGiam]\n"
                + "           ,[soLuong]\n"
                + "           ,[ngayBatDau]\n"
                + "           ,[ngayKetThuc]\n"
                + "           ,[dieuKienGiamGia]\n"
                + "           ,[trangThai])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma.getMaGiamGia());
            ps.setObject(2, ma.getPhanTramGiam());
            ps.setObject(3, ma.getSoLuong());
            ps.setObject(4, ma.getNgayBatDau());
            ps.setObject(5, ma.getNgayketThuc());
            ps.setObject(6, ma.getDieuKienGiamGia());
            ps.setObject(7, ma.getTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean updateMa(MaGiamGia ma) {
        int check = 0;
        String sql = "UPDATE [dbo].[GiamGia]\n"
                + "   SET [phanTramGiam] = ?\n"
                + "      ,[soLuong] = ?\n"
                + "      ,[ngayBatDau] = ?\n"
                + "      ,[ngayKetThuc] = ?\n"
                + "      ,[dieuKienGiamGia] = ?\n"
                + "      ,[trangThai] = ?\n"
                + " WHERE maGiamGia = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma.getPhanTramGiam());
            ps.setObject(2, ma.getSoLuong());
            ps.setObject(3, ma.getNgayBatDau());
            ps.setObject(4, ma.getNgayketThuc());
            ps.setObject(5, ma.getDieuKienGiamGia());
            ps.setObject(6, ma.getTrangThai());
            ps.setObject(7, ma.getMaGiamGia());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public int delete(String maGG) {
        String sql = "DELETE FROM [dbo].[GiamGia]\n"
                + "      WHERE maGiamGia = ?";
        return JDBC_Helper_1.updateTong(sql, maGG);
        
    }

    @Override
    public String layIdByMa(String ma) {
        ChiTietSanPham sp = null;
        String sql = "select id from GiamGia where maGiamGia = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, ma);
        
        try {
            String id =null;
            while(rs.next()){
                id = rs.getString("id");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrungMa(String maGG) {
        MaGiamGia gg = getAllByMa(maGG);
        if (gg == null) {
            return false;
        } else {
            return true;
        }
    }

}
