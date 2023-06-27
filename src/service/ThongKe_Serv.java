/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewModel.ThongKe_ViewModel;
import viewModel.TT_Thongke_ViewModel;

/**
 *
 * @author NHH
 */
public interface ThongKe_Serv {

    List<ThongKe_ViewModel> getAllTheoNgayHomNay();

    List<ThongKe_ViewModel> getAll();

    List<ThongKe_ViewModel> getAllTheoKhoangNgay(String n1, String n2);

    List<ThongKe_ViewModel> getTop5SoLuongBan();

    List<ThongKe_ViewModel> getTop5DoanhThu();

    List<ThongKe_ViewModel> getMinSoLuongTon(int sl);

    int getTongSoHDTheoNgay(String n1, String n2);

    int getTongSoHD();

    List<ThongKe_ViewModel> getAllTHeoNam(String nam);

    int getSoGiaySapHet();

    int getSoGiayHet();

    int getSoGiayKD();

    int getSoHDGiao();

    int getSoHDHuy();

    double getDoanhThu();

    double getDoanhThuTheoNgay(String n1, String n2);

    int getSoHDHuyTheoNgay(String n1, String n2);

    int getSoHDGiaoTheoNgay(String n1, String n2);

}
