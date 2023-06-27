/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.service_Impl;

import DomainModel.ChiTietGiamGia;
import DomainModel.ChiTietSanPham;
import repository.impl.ChiTietSanPhamRepository;
import repository.ChiTietSanPhamRepositoryImpl;
import service.ChiTietGiamGiaService;
import service.ChiTietSanPhamService;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    private ChiTietSanPhamRepository ctspRepo = new ChiTietSanPhamRepositoryImpl();

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctspRepo.getAll();
    }

    @Override
    public String layIdByMa(String ma) {
        return ctspRepo.layIdByMa(ma);
    }

    @Override
    public int updateGia(String gia, String id) {
        return ctspRepo.updateGia(gia, id);
    }

}
