/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.service_Impl;

import java.util.List;
import repository.ThongKe_Resp;
import service.ThongKe_Serv;
import viewModel.ThongKe_ViewModel;
import viewModel.TT_Thongke_ViewModel;

/**
 *
 * @author NHH
 */
public class ThongKe_ServImpl implements ThongKe_Serv {

    @Override
    public List<ThongKe_ViewModel> getAll() {
        return ThongKe_Resp.getAll();
    }

    @Override
    public List<ThongKe_ViewModel> getMinSoLuongTon(int sl) {
        return ThongKe_Resp.getMinSoLuongTon(sl);
    }

    @Override
    public List<ThongKe_ViewModel> getAllTheoNgayHomNay() {
        return ThongKe_Resp.getAllTheoNgayHomNay();
    }

    @Override
    public List<ThongKe_ViewModel> getAllTheoKhoangNgay(String n1, String n2) {
        return ThongKe_Resp.getAllTHeoKhoangNgay(n1, n2);
    }

    @Override
    public List<ThongKe_ViewModel> getAllTHeoNam(String nam) {
        return ThongKe_Resp.getAllTHeoNam(nam);
    }

    @Override
    public int getSoGiaySapHet() {
        return ThongKe_Resp.getSoGiaySapHet();
    }

    @Override
    public int getSoGiayHet() {
        return ThongKe_Resp.getSoGiayHet();
    }

    @Override
    public int getSoGiayKD() {
        return ThongKe_Resp.getSoGiayKD();
    }

    @Override
    public List<ThongKe_ViewModel> getTop5SoLuongBan() {
        return ThongKe_Resp.getTop5SoLuongBan();
    }

    @Override
    public List<ThongKe_ViewModel> getTop5DoanhThu() {
        return ThongKe_Resp.getTop5DoanhThu();
    }

    @Override
    public int getSoHDGiao() {
        return ThongKe_Resp.getSoHDGiao();
    }

    @Override
    public int getSoHDHuy() {
        return ThongKe_Resp.getSoHDHuy();
    }

    @Override
    public int getTongSoHD() {
        return ThongKe_Resp.getTongSoHD();
    }

    @Override
    public double getDoanhThu() {
        return ThongKe_Resp.getDoanhThu();
    }

    @Override
    public int getTongSoHDTheoNgay(String n1, String n2) {
        return ThongKe_Resp.getTongSoHDTheoNgay(n1, n2);
    }

    @Override
    public double getDoanhThuTheoNgay(String n1, String n2) {
        return ThongKe_Resp.getDoanhThuTheoNgay(n1, n2);
    }

    @Override
    public int getSoHDHuyTheoNgay(String n1, String n2) {
        return ThongKe_Resp.getSoHDHuyTheoNgay(n1, n2);
    }

    @Override
    public int getSoHDGiaoTheoNgay(String n1, String n2) {
        return ThongKe_Resp.getSoHDGiaoTheoNgay(n1, n2);
    }

}
