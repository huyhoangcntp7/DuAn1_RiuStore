/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.impl;

import DomainModel.MaGiamGia;
import java.util.List;

/**
 *
 * @author admin
 MaGiamGia*/
public interface GiamGiaRepository {
    List<MaGiamGia> getAll();

    String layIdByMa(String ma);
    
    boolean addMa(MaGiamGia ma);

    boolean updateMa(MaGiamGia ma);

    int delete(String maGG);
    
    MaGiamGia getAllByMa(String maGG);
    
    boolean checkTrungMa(String maGG);
}
