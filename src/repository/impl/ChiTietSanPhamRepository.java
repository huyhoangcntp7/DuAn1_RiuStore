/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.impl;

import DomainModel.ChiTietSanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChiTietSanPhamRepository {

    List<ChiTietSanPham> getAll();
    
    String layIdByMa(String ma);
    
    int updateGia(String gia, String id);
    
}
