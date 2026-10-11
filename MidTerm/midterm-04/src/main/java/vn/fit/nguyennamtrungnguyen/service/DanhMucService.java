package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.dto.ThongKeDanhMucDTO;
import vn.fit.nguyennamtrungnguyen.model.DanhMuc;
import vn.fit.nguyennamtrungnguyen.model.TinTuc;
import vn.fit.nguyennamtrungnguyen.repository.DanhMucRepository;
import vn.fit.nguyennamtrungnguyen.repository.TinTucRepository;
import vn.fit.nguyennamtrungnguyen.repository.impl.DanhMucRepositoryImpl;
import vn.fit.nguyennamtrungnguyen.repository.impl.TinTucRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class DanhMucService {
    private final DanhMucRepository danhMucRepository = new DanhMucRepositoryImpl();
    public List<DanhMuc> findAll() throws SQLException {
        return danhMucRepository.findAll();
    }

    // Tìm tin tức theo mã
    public DanhMuc findById(int maDM) throws SQLException {
        return danhMucRepository.findById(maDM);
    }

    // Thêm tin tức
    public boolean create(DanhMuc danhMuc) throws SQLException {
        return danhMucRepository.create(danhMuc);
    }

    // Cập nhật tin tức
    public boolean update(DanhMuc danhMuc) throws SQLException {
        return danhMucRepository.update(danhMuc);
    }

    // Xóa tin tức theo mã
    public boolean delete(int maDM) throws SQLException {
        return danhMucRepository.delete(maDM);
    }

    public List<ThongKeDanhMucDTO> thongKeSoLuongTinTucTheoDanhMuc() throws SQLException{
        return danhMucRepository.thongKeSoLuongTinTucTheoDanhMuc();
    }

    public List<ThongKeDanhMucDTO> lietKeCacDanhMucSLTTTu3TroLen() throws SQLException{
        return danhMucRepository.lietKeCacDanhMucSLTTTu3TroLen();
    }

    public List<ThongKeDanhMucDTO> findDanhMucCoNhieuTinTucNhat() throws SQLException {
        return danhMucRepository.findDanhMucCoNhieuTinTucNhat();
    }
}
