/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.service_Impl;

import DomainModel.MaGiamGia;
import repository.impl.GiamGiaRepository;
import repository.GiamGiaRepositoryImpl;
import service.GiamGiaService;
import java.util.List;

/**
 *
 * @author admin
 */
public class GiamGiaServiceImpl implements GiamGiaService {

    private GiamGiaRepository maRepo = new GiamGiaRepositoryImpl();

    @Override
    public List<MaGiamGia> getAll() {
        return maRepo.getAll();
    }

    @Override
    public String addMa(MaGiamGia ma) {
        boolean addMa = maRepo.addMa(ma);
        if (addMa) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String updateMa(MaGiamGia ma) {
        boolean updateMa = maRepo.updateMa(ma);
        if (updateMa) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }


    @Override
    public String layIdByMa(String ma) {
        return maRepo.layIdByMa(ma);
    }

    @Override
    public int delete(String maGG) {
        return maRepo.delete(maGG);
    }

    @Override
    public boolean checkTrungMa(String maGG) {
        return maRepo.checkTrungMa(maGG);
    }
}
