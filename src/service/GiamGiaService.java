/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import DomainModel.MaGiamGia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface GiamGiaService {
    List<MaGiamGia> getAll();

    String layIdByMa(String ma);
    
    String addMa(MaGiamGia ma);

    String updateMa(MaGiamGia ma);

    int delete(String maGG);
    
    boolean checkTrungMa(String maGG);
}
