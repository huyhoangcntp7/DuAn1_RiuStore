/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.impl;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChiTietGiamGiaRepository {
    String getIdChiTiet(String maGG, String maCT);
    
//    List<ChiTietGiamGia> getAll();
    boolean addMaGiamGia(AddChiTiet add);
    
    int updateChiTiet(AddChiTiet add, String giamGia, String sanPham, String id);
            
    int deleteMaGiamGia(String id);
    
    List<ChiTietGiamGia> getAllChiTietGiamGia();
}
