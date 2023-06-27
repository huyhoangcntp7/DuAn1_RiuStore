/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.service_Impl;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import repository.impl.ChiTietGiamGiaRepository;
import repository.ChiTietGiamGiaRepositoryImpl;
import service.ChiTietGiamGiaService;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChiTietGiamGiaServiceImpl implements ChiTietGiamGiaService{
    private ChiTietGiamGiaRepository ctggRepo = new ChiTietGiamGiaRepositoryImpl();

//    @Override
//    public List<ChiTietGiamGia> getAll() {
//        return ctggRepo.getAll();
//    }

    @Override
    public String addChiTiet(AddChiTiet ctgg) {
        boolean addMa = ctggRepo.addMaGiamGia(ctgg);
        if (addMa) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public List<ChiTietGiamGia> getAllChiTietGiamGia() {
        return ctggRepo.getAllChiTietGiamGia();
    }

    

    @Override
    public int updateChiTiet(AddChiTiet add,String giamGia, String sanPham,String id) {
        return ctggRepo.updateChiTiet(add,giamGia, sanPham , id);
    }

    @Override
    public int deleteMaGiamGia(String id) {
        return ctggRepo.deleteMaGiamGia( id);
    }

    @Override
    public String getIdChiTiet(String maGG, String maCT) {
        return ctggRepo.getIdChiTiet(maGG, maCT);
    }

    
}
