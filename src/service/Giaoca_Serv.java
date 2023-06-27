/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import DomainModel.giaoCa;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface Giaoca_Serv {

    giaoCa getAllGC();

    String getIdByEmail(String email);

    String getTenByEmail(String email);

    int getSLCa();

    double getdoanhThubyThoiGian(String tgbd, String tgkt);

    double getTienmatbythoigian(String bd, String kt, String ngaytao);

    double getTienkhacbythoigian(String bd, String kt, String ngaytao);

    String getThoigianbatdauca();

    int add(giaoCa gc);

    String getIdByMa(String ma);

    List<String> getallMaNV();

    int updateCCH(String idcch, double tienrut);

    int update(String thoigiaoca, String idnhanviencatieptheo, double tiendoanhthu, double tongtienkhac, double tongtienMat, double tongtientrongca, double tienphatsinh, String ghichu);

    boolean checkHoaDonTreo();

    List<giaoCa> getGiaoCaTK(int thang, String id);

    boolean checkTonTaiCaLam();
}
