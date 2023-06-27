/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.ChiTietSanPham;
import repository.ChiTietGiamGiaRepositoryImpl;
import repository.impl.ChiTietSanPhamRepository;
import Unility.DBContext;
import Unility.DBContext_1;
import Unility.JDBC_Helper_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import static repository.ChiTietGiay_Repository.getAllChiTietGiayViewByMa;
import viewModel.ChiTietGiay_View;

/**
 *
 * @author admin
 */
public class ChiTietSanPhamRepositoryImpl implements ChiTietSanPhamRepository {

    @Override
    public List<ChiTietSanPham> getAll() {
        String query = "SELECT dbo.ChiTietGiay.maGiay,dbo.ChiTietGiay.tenGiay, dbo.MauSac.tenMauSac, dbo.Size.Size, dbo.ChatLieu.tenChatLieu, dbo.HangGiay.tenHangGiay, dbo.ChiTietGiay.soLuong, giaBan\n"
                + "FROM ChiTietGiay join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "				join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "				join Size on ChiTietGiay.idSize=Size.id\n"
                + "				join HangGiay on ChiTietGiay.idHangGiay=HangGiay.id";
        try ( Connection con = DBContext_1.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<ChiTietSanPham> listctsp = new ArrayList<>();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8));
                listctsp.add(ctsp);
            }
            return listctsp;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        ChiTietSanPhamRepository ctsp = new ChiTietSanPhamRepositoryImpl();
        for (ChiTietSanPham ct : ctsp.getAll()) {
            System.out.println(ct.getHangGiay());
        }
    }

    public String layIdByMa(String ma) {
        ChiTietSanPham sp = null;
        String sql = "select id from ChiTietGiay where maGiay = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, ma);

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

    @Override
    public int updateGia(String gia, String id) {
        String sql = "update ChiTietGiamGia set soTienConLai ="
                + " (select giaBan from ChiTietGiay where id = ?) where id = ?";
        return JDBC_Helper_1.updateTong(sql, gia, id);
    }

}
