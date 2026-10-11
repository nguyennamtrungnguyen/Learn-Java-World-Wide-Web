package vn.fit.nguyennamtrungnguyen.dto;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public class ThongKeDanhMucDTO {
    private int maDM;
    private String tenDanhMuc;
    private long soLuongTinTuc;

    public ThongKeDanhMucDTO(int maDM, String tenDanhMuc, long soLuongTinTuc) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
        this.soLuongTinTuc = soLuongTinTuc;
    }

    public ThongKeDanhMucDTO() {
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public long getSoLuongTinTuc() {
        return soLuongTinTuc;
    }

    public void setSoLuongTinTuc(long soLuongTinTuc) {
        this.soLuongTinTuc = soLuongTinTuc;
    }
}
