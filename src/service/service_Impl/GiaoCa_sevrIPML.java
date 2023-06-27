/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.service_Impl;

import DomainModel.giaoCa;
import java.util.List;
import repository.GiaoCa_reps;
import service.Giaoca_Serv;

/**
 *
 * @author ADMIN
 */
public class GiaoCa_sevrIPML implements Giaoca_Serv {

    @Override
    public int add(giaoCa gc) {
        return GiaoCa_reps.add(gc);
    }

    @Override
    public String getIdByEmail(String email) {
        return GiaoCa_reps.getIdByEmail(email);
    }

    @Override
    public String getTenByEmail(String email) {
        return GiaoCa_reps.getTenByEmail(email);
    }

    @Override
    public int getSLCa() {
        return GiaoCa_reps.getSLCa();
    }

    @Override
    public double getdoanhThubyThoiGian(String tgbd, String tgkt) {
        return GiaoCa_reps.getdoanhThubyThoiGian(tgbd, tgkt);
    }

    @Override
    public double getTienmatbythoigian(String bd, String kt, String ngaytao) {
        return GiaoCa_reps.getTienkhacbythoigian(bd, kt, ngaytao);
    }

    @Override
    public double getTienkhacbythoigian(String bd, String kt, String ngaytao) {
        return GiaoCa_reps.getTienkhacbythoigian(bd, kt, ngaytao);

    }

    @Override
    public String getThoigianbatdauca() {
        return GiaoCa_reps.getThoigianbatdauca();
    }

 

    @Override
    public giaoCa getAllGC() {
        return GiaoCa_reps.getAllGC();
    }

    @Override
    public String getIdByMa(String ma) {
        return GiaoCa_reps.getIdByMa(ma);
    }
    @Override
    public List<String> getallMaNV() {
        return GiaoCa_reps.getallMaNV();
    }

    @Override
    public int update(String thoigiaoca, String idnhanviencatieptheo, double tiendoanhthu, double tongtienkhac, double tongtienMat, double tongtientrongca, double tienphatsinh, String ghichu) {
        return GiaoCa_reps.update(thoigiaoca, idnhanviencatieptheo, tiendoanhthu, tongtienkhac, tongtienMat, tongtientrongca, tienphatsinh, ghichu);
    }

    @Override
    public int updateCCH(String idcch, double tienrut) {
        return GiaoCa_reps.updateCCH(idcch, tienrut);
    }

    @Override
    public boolean checkHoaDonTreo() {
       return GiaoCa_reps.checkHoaDonTreo();   
    }

    @Override
    public List<giaoCa> getGiaoCaTK(int thang, String id) {
        return GiaoCa_reps.getGiaoCaTK(thang, id);
    }

    @Override
    public boolean checkTonTaiCaLam() {
        return GiaoCa_reps.checkTonTaiCaLam();
    }

}
