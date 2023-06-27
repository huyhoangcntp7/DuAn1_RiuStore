package repository;

import Unility.JDBC_HELPER;
import java.util.ArrayList;
import java.util.List;
import viewModel.ThongKe_ViewModel;
import java.sql.*;

import viewModel.TT_Thongke_ViewModel;

public class ThongKe_Resp {

    public static List<ThongKe_ViewModel> getAllTheoNgayHomNay() {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select maGiay,tenGiay,sum(HoaDonChiTiet.soLuong)as'So luong ban',tienMat,tienKhac,giamGiaThem,sum((donGia*HoaDonChiTiet.soLuong)-(donGia*HoaDonChiTiet.soLuong*(giamGiaThem/100.0))) as 'Doanh thu',ngayTao,donGia,HoaDon.trangThai,mahoadon,lidohuy from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "                          inner join Size on ChiTietGiay.idSize=Size.id\n"
                + "                       inner join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "                        inner join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "                      inner join HoaDon on HoaDonChiTiet.idHoaDon=HoaDon.id\n"
                + "                        where ngayTao=CONCAT(Year(GETDATE()),'-',Month(GETDATE()),'-',DAY(GETDATE())) \n"
                + "                          Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,ngayTao,tienMat,tienKhac,tienGiamGia,giamGiaThem,HoaDon.trangThai,mahoadon,lidohuy\n"
                + "                     order by mahoadon";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setSlBan(rs.getInt(3));
                tk.setTienmat(rs.getDouble(4));
                tk.setTienkhac(rs.getDouble(5));
                tk.setTiengiamgia(rs.getDouble(6));
                tk.setDoanhThu(rs.getDouble(7));
                tk.setNgaytao(rs.getString(8));
                tk.setDonGia(rs.getDouble(9));
                tk.setTt(rs.getInt(10));
                tk.setMaHD(rs.getString(11));
                tk.setLidoHuy(rs.getString(12));
                list.add(tk);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getAllTHeoKhoangNgay(String n1, String n2) {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select maGiay,tenGiay,sum(HoaDonChiTiet.soLuong)as'So luong ban',tienMat,tienKhac,giamGiaThem,sum((donGia*HoaDonChiTiet.soLuong)-(donGia*HoaDonChiTiet.soLuong*(giamGiaThem/100.0))) as 'Doanh thu',ngayTao,donGia,HoaDon.trangThai,mahoadon,lidohuy from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "                          inner join Size on ChiTietGiay.idSize=Size.id\n"
                + "                       inner join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "                        inner join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "                      inner join HoaDon on HoaDonChiTiet.idHoaDon=HoaDon.id\n"
                + "                       where ngayTao>=? and ngayTao <=?\n"
                + "                          Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,ngayTao,tienMat,tienKhac,tienGiamGia,giamGiaThem,HoaDon.trangThai,mahoadon,lidohuy order by mahoadon";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, n1, n2);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setSlBan(rs.getInt(3));
                tk.setTienmat(rs.getDouble(4));
                tk.setTienkhac(rs.getDouble(5));
                tk.setTiengiamgia(rs.getDouble(6));
                tk.setDoanhThu(rs.getDouble(7));
                tk.setNgaytao(rs.getString(8));
                tk.setDonGia(rs.getDouble(9));
                tk.setTt(rs.getInt(10));
                tk.setMaHD(rs.getString(11));
                tk.setLidoHuy(rs.getString(12));
                list.add(tk);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getAllTHeoNam(String nam) {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select maGiay,tenGiay,sum(HoaDonChiTiet.soLuong)as'So luong ban',tienMat,tienKhac,giamGiaThem,sum((donGia*HoaDonChiTiet.soLuong)-(donGia*HoaDonChiTiet.soLuong*(giamGiaThem/100.0))) as 'Doanh thu',ngayTao,donGia,HoaDon.trangThai,mahoadon,lidohuy from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "                          inner join Size on ChiTietGiay.idSize=Size.id\n"
                + "                       inner join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "                        inner join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "                      inner join HoaDon on HoaDonChiTiet.idHoaDon=HoaDon.id\n"
                + "                        where year(ngaytao)=?\n"
                + "                          Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,ngayTao,tienMat,tienKhac,tienGiamGia,giamGiaThem,HoaDon.trangThai,mahoadon,lidohuy order by mahoadon";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, nam);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setSlBan(rs.getInt(3));
                tk.setTienmat(rs.getDouble(4));
                tk.setTienkhac(rs.getDouble(5));
                tk.setTiengiamgia(rs.getDouble(6));
                tk.setDoanhThu(rs.getDouble(7));
                tk.setNgaytao(rs.getString(8));
                tk.setDonGia(rs.getDouble(9));
                tk.setTt(rs.getInt(10));
                tk.setMaHD(rs.getString(11));
                tk.setLidoHuy(rs.getString(12));
                list.add(tk);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getAll() {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,sum(HoaDonChiTiet.soLuong) as'So luong ban',giaNhap,sum(donGia*HoaDonChiTiet.soLuong) as 'Doanh thu',namBaoHanh  from ChiTietGiay\n"
                + "                 left join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "                 left join Size on ChiTietGiay.idSize=Size.id\n"
                + "                 left  join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "                 left join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "                 Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,giaNhap,namBaoHanh";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setMau(rs.getString(3));
                tk.setSize(rs.getString(4));
                tk.setChatLieu(rs.getString(5));
                tk.setSlTon(rs.getInt(6));
                tk.setDonGia(rs.getDouble(7));
                tk.setSlBan(rs.getInt(8));
                tk.setGianhap(rs.getDouble(9));
                tk.setDoanhThu(rs.getDouble(10));
                tk.setNamBH(rs.getString(11));

                list.add(tk);

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getTop5SoLuongBan() {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select top 5  maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,sum(HoaDonChiTiet.soLuong) as'So luong ban',giaNhap,sum(donGia*HoaDonChiTiet.soLuong) as 'Doanh thu',namBaoHanh from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "              inner join Size on ChiTietGiay.idSize=Size.id\n"
                + "              inner join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "              inner join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "		Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,giaNhap,namBaoHanh\n"
                + "              Order by [So luong ban] desc";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setMau(rs.getString(3));
                tk.setSize(rs.getString(4));
                tk.setChatLieu(rs.getString(5));
                tk.setSlTon(rs.getInt(6));
                tk.setDonGia(rs.getDouble(7));
                tk.setSlBan(rs.getInt(8));
                tk.setGianhap(rs.getDouble(9));
                tk.setDoanhThu(rs.getDouble(10));
                tk.setNamBH(rs.getString(11));
                list.add(tk);

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getTop5DoanhThu() {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select top 5 maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,sum(HoaDonChiTiet.soLuong) as'So luong ban',giaNhap,sum(donGia*HoaDonChiTiet.soLuong) as 'Doanh thu',namBaoHanh from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "              inner join Size on ChiTietGiay.idSize=Size.id\n"
                + "              inner join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "              inner join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "		Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,giaNhap,namBaoHanh\n"
                + "             Order by [Doanh thu] desc";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setMau(rs.getString(3));
                tk.setSize(rs.getString(4));
                tk.setChatLieu(rs.getString(5));
                tk.setSlTon(rs.getInt(6));
                tk.setDonGia(rs.getDouble(7));
                tk.setSlBan(rs.getInt(8));
                tk.setGianhap(rs.getDouble(9));
                tk.setDoanhThu(rs.getDouble(10));
                tk.setNamBH(rs.getString(11));
                list.add(tk);

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static List<ThongKe_ViewModel> getMinSoLuongTon(int soLuong) {
        List<ThongKe_ViewModel> list = new ArrayList<>();
        String sql = "select maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,sum(HoaDonChiTiet.soLuong) as'So luong ban',gianhap,sum(donGia*HoaDonChiTiet.soLuong) as 'Doanh thu',namBaoHanh from ChiTietGiay inner join ChatLieu on ChiTietGiay.idChatLieu=ChatLieu.id\n"
                + "              left join Size on ChiTietGiay.idSize=Size.id\n"
                + "              \n"
                + "              left join MauSac on ChiTietGiay.idMauSac=MauSac.id\n"
                + "              left join HoaDonChiTiet on ChiTietGiay.id=HoaDonChiTiet.idChiTietGiay\n"
                + "			   where ChiTietGiay.soLuong<=?\n"
                + "			  Group by maGiay,tenGiay,tenMauSac,Size,tenChatLieu,ChiTietGiay.soLuong,donGia,namBaoHanh,giaNhap";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, soLuong);
        try {
            while (rs.next()) {
                ThongKe_ViewModel tk = new ThongKe_ViewModel();
                tk.setMaSp(rs.getString(1));
                tk.setTenSp(rs.getString(2));
                tk.setMau(rs.getString(3));
                tk.setSize(rs.getString(4));
                tk.setChatLieu(rs.getString(5));
                tk.setSlTon(rs.getInt(6));
                tk.setDonGia(rs.getDouble(7));
                tk.setSlBan(rs.getInt(8));
                tk.setGianhap(rs.getDouble(9));
                tk.setDoanhThu(rs.getDouble(10));
                tk.setNamBH(rs.getString(11));
                list.add(tk);

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static int getTongSoHDTheoNgay(String n1, String n2) {

        int sum = 0;
        String sql = "select COUNT(id) from HoaDon  where ngayTao>=? and ngayTao <=?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, n1, n2);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoGiayKD() {

        int sum = 0;
        String sql = "	select count(id) as N'Số giày hd' from ChiTietGiay\n";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoGiaySapHet() {

        int sum = 0;
        String sql = "select count(id) as N'Số giày hd' from ChiTietGiay where soLuong>0 and soLuong<=10";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoGiayHet() {

        int sum = 0;
        String sql = "select count(id) as N'Số giày hd' from ChiTietGiay where soLuong=0";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getTongSoHD() {
        int sum = 0;
        String sql = "select COUNT(id) from HoaDon where ngaytao=CONCAT(Year(GETDATE()),'-',Month(GETDATE()),'-',DAY(GETDATE()))";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoHDHuy() {
        int sum = 0;
        String sql = "select COUNT(id) from HoaDon where trangThai=4 and ngaytao=CONCAT(Year(GETDATE()),'-',Month(GETDATE()),'-',DAY(GETDATE()))";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoHDHuyTheoNgay(String n1, String n2) {
        int sum = 0;
        String sql = "select COUNT(id) from HoaDon where trangThai=4 and ngayTao>=? and ngayTao <=?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, n1, n2);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoHDGiaoTheoNgay(String n1, String n2) {
        int sum = 0;
        String sql = "select COUNT(id) from HoaDon where trangThai=3 and ngayTao>=? and ngayTao <=?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, n1, n2);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSoHDGiao() {
        int sum = 0;
        String sql = "select COUNT(id) from HoaDon where trangThai=3 and ngaytao=CONCAT(Year(GETDATE()),'-',Month(GETDATE()),'-',DAY(GETDATE()))";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
/// ahihi nha

    public static double getDoanhThu() {
        double sum = 0;
        String sql = "select SUM(soLuong*donGia)-SUM((soLuong*donGia)*(giamGiaThem/100.0)) from HoaDon inner join HoaDonChiTiet on \n"
                + "                HoaDon.id=HoaDonChiTiet.idHoaDon where ngaytao=CONCAT(Year(GETDATE()),'-',Month(GETDATE()),'-',DAY(GETDATE())) and hoadon.trangthai<>4";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static double getDoanhThuTheoNgay(String n1, String n2) {
        double sum = 0;
        String sql = "select SUM(soLuong*donGia)-SUM((soLuong*donGia)*(giamGiaThem/100.0)) from HoaDon inner join HoaDonChiTiet on \n"
                + "               HoaDon.id=HoaDonChiTiet.idHoaDon where ngayTao>=? and ngayTao <=?  and hoadon.trangthai<>4";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, n1, n2);
        try {
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

}
