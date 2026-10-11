package vn.fit.nguyennamtrungnguyen.dto;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public class DanhMucTinTucDTO {

    private int maDM;
    private String tenDanhMuc;
    private int maTT;
    private String tieuDe;

    public DanhMucTinTucDTO(int maDM, String tenDanhMuc, int maTT, String tieuDe) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
        this.maTT = maTT;
        this.tieuDe = tieuDe;
    }

    public DanhMucTinTucDTO() {
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

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }
}
