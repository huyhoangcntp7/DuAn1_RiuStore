/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChiTietGiamGiaService {
    String getIdChiTiet(String maGG, String maCT);
    
//    List<ChiTietGiamGia> getAll();

    String addChiTiet(AddChiTiet ctgg);
    
    int deleteMaGiamGia(String id);
    
    int updateChiTiet(AddChiTiet add,String giamGia, String sanPham, String id);
    
    List<ChiTietGiamGia> getAllChiTietGiamGia();
}
