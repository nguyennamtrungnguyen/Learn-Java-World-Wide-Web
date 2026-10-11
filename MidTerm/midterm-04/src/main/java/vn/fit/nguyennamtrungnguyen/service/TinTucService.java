package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.dto.DanhMucTinTucDTO;
import vn.fit.nguyennamtrungnguyen.model.TinTuc;
import vn.fit.nguyennamtrungnguyen.repository.TinTucRepository;
import vn.fit.nguyennamtrungnguyen.repository.impl.TinTucRepositoryImpl;

import java.sql.*;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class TinTucService {
    private final TinTucRepository tinTucRepository = new TinTucRepositoryImpl();
    public List<TinTuc> findAll() throws SQLException {
        return tinTucRepository.findAll();
    }

    // Tìm tin tức theo mã
    public TinTuc findById(int maTT) throws SQLException {
        return tinTucRepository.findById(maTT);
    }

    // Thêm tin tức
    public boolean create(TinTuc tinTuc) throws SQLException {
        return tinTucRepository.create(tinTuc);
    }

    // Cập nhật tin tức
    public boolean update(TinTuc tinTuc) throws SQLException {
        return tinTucRepository.update(tinTuc);
    }

    // Xóa tin tức theo mã
    public boolean delete(int maTT) throws SQLException {
        return tinTucRepository.delete(maTT);
    }

    public List<DanhMucTinTucDTO> findAllWithNameDanhMuc () throws SQLException{
        return tinTucRepository.findAllWithNameDanhMuc();
    }

    public List<DanhMucTinTucDTO> findAllWithNameDanhMucCuThe(String nameDanhMUC) throws SQLException{
        return tinTucRepository.findAllWithNameDanhMucCuThe(nameDanhMUC);
    }

    public List<DanhMucTinTucDTO> findAllCoTieuDeChuaTuKhoaCongNghe() throws SQLException{
        return tinTucRepository.findAllCoTieuDeChuaTuKhoaCongNghe();
    }
    public List<DanhMucTinTucDTO> lietKeCacTinTucThuocDanhMuc() throws SQLException{
        return tinTucRepository.lietKeCacTinTucThuocDanhMuc();
    }
}
