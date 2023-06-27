/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import DomainModel.ChiTietSanPham;
import repository.ChiTietGiamGiaRepositoryImpl;
import repository.impl.ChiTietGiamGiaRepository;
import Unility.DBContext;
import Unility.JDBC_Helper_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChiTietGiamGiaRepositoryImpl implements ChiTietGiamGiaRepository {

//    @Override
//    public List<ChiTietGiamGia> getAll() {
//        String query = "SELECT        dbo.GiamGia.maGiamGia, dbo.GiamGia.ngayBatDau, dbo.GiamGia.ngayKetThuc, dbo.ChiTietGiay.tenGiay, dbo.ChiTietGiamGia.soTienConLai, dbo.ChiTietGiamGia.trangThai\n"
//                + "FROM            dbo.ChiTietGiamGia INNER JOIN\n"
//                + "                         dbo.ChiTietGiay ON dbo.ChiTietGiamGia.idChiTietGiay = dbo.ChiTietGiay.id INNER JOIN\n"
//                + "                         dbo.GiamGia ON dbo.ChiTietGiamGia.idGiamGia = dbo.GiamGia.id";
//        try ( Connection con = DB_Context.getContext();  PreparedStatement ps = con.prepareStatement(query);) {
//            ResultSet rs = ps.executeQuery();
//            List<ChiTietGiamGia> listctgg = new ArrayList<>();
//            while (rs.next()) {
//                ChiTietGiamGia ctgg = new ChiTietGiamGia(rs.getString(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getInt(6));
//                listctgg.add(ctgg);
//            }
//            return listctgg;
//        } catch (SQLException e) {
//            e.printStackTrace(System.out);
//        }
//        return null;
//    }
    @Override
    public List<ChiTietGiamGia> getAllChiTietGiamGia() {
        List<ChiTietGiamGia> listCTGG = new ArrayList<>();
        String sql = "SELECT  maGiamGia, maGiay,tenGiay, ngayBatDau, ngayKetThuc, soTienConLai, ChiTietGiamGia.trangThai tt\n"
                + "FROM     dbo.ChiTietGiamGia join ChiTietGiay on ChiTietGiamGia.idChiTietGiay=ChiTietGiay.id\n"
                + "							join GiamGia on ChiTietGiamGia.idGiamGia=GiamGia.id ";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String magg = rs.getString("maGiamGia");
                String maGiay = rs.getString("maGiay");
                String tengiay = rs.getString("tenGiay");
                String ngayBatDau = rs.getString("ngayBatDau");
                String ngayKetThuc = rs.getString("ngayKetThuc");
                double tienConLai = rs.getDouble("soTienConLai");
                int trangThai = rs.getInt("tt");
                ChiTietGiamGia add = new ChiTietGiamGia(magg, maGiay, tengiay, ngayBatDau, ngayKetThuc, tienConLai, trangThai);
                listCTGG.add(add);
            }
            return listCTGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIdChiTiet(String maGG, String maCT) {
        String sql = "select ChiTietGiamGia.id from ChiTietGiamGia  join ChiTietGiay on ChiTietGiamGia.idChiTietGiay=ChiTietGiay.id\n"
                + "	join GiamGia on ChiTietGiamGia.idGiamGia=GiamGia.id  where maGiamGia = ? and maGiay = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, maGG, maCT);
        try {
            String id = null;
            while (rs.next()) {
                id = rs.getString("id");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateChiTiet(AddChiTiet add, String giamGia, String sanPham, String id) {
        String sql = "update chiTietGiamGia set "
                + "idGiamGia = ?"
                + ",idChiTietGiay = ?"
                + ",soTienConLai = ?"
                + ",trangThai = ? where id = ?";
        return JDBC_Helper_1.updateTong(sql, giamGia, sanPham, add.getSoTienConLai(), add.getTrangThai(), id);
    }

    @Override
    public boolean addMaGiamGia(AddChiTiet add) {
        int check = 0;
        String query = "INSERT INTO [dbo].[ChiTietGiamGia]\n"
                + "           ([idGiamGia]\n"
                + "           ,[idChiTietGiay]"
                + "           ,[soTienConLai]"
                + "           ,[trangThai])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, add.getIdMaGiamGia());
            ps.setObject(2, add.getIdChiTietGiay());
            ps.setObject(3, add.getSoTienConLai());
            ps.setObject(4, add.getTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public int deleteMaGiamGia(String id) {
        String sql = "DELETE FROM [dbo].[ChiTietGiamGia]\n"
                + "      WHERE id = ?";
        return JDBC_Helper_1.updateTong(sql, id);
    }
    
}
