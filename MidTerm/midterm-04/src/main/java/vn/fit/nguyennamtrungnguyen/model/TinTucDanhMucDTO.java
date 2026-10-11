package vn.fit.nguyennamtrungnguyen.model;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public class TinTucDanhMucDTO {
    private int maTT;
    private String tieuDe;
    private String tenDanhMuc;
    private String noiDung;

    public TinTucDanhMucDTO() {
    }

    public TinTucDanhMucDTO(int maTT, String tieuDe, String tenDanhMuc) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.tenDanhMuc = tenDanhMuc;
    }

    public TinTucDanhMucDTO(int maTT, String tieuDe, String tenDanhMuc, String noiDung) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.tenDanhMuc = tenDanhMuc;
        this.noiDung = noiDung;
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

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
