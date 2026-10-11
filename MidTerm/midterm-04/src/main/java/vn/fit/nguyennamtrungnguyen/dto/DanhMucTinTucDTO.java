package vn.fit.nguyennamtrungnguyen.dto;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public class DanhMucTinTucDTO {
    private int madm;
    private String tenDanhMuc;
    private String nguoiQuanLy;
    private String ghiChu;
    private int matt;
    private String tieuDe;
    private String noidungTT;
    private String lienKet;

    public DanhMucTinTucDTO(int madm, String tenDanhMuc, int matt, String tieuDe) {
        this.madm = madm;
        this.tenDanhMuc = tenDanhMuc;
        this.matt = matt;
        this.tieuDe = tieuDe;
    }

    public DanhMucTinTucDTO(int matt, String tieuDe, String tenDanhMuc) {
        this.matt = matt;
        this.tieuDe = tieuDe;
        this.tenDanhMuc = tenDanhMuc;
    }

    public DanhMucTinTucDTO(int matt, String tieuDe, String tenDanhMuc, String noidungTT) {
        this.matt = matt;
        this.tieuDe = tieuDe;
        this.tenDanhMuc = tenDanhMuc;
        this.noidungTT = noidungTT;
    }

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getNguoiQuanLy() {
        return nguoiQuanLy;
    }

    public void setNguoiQuanLy(String nguoiQuanLy) {
        this.nguoiQuanLy = nguoiQuanLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoidungTT() {
        return noidungTT;
    }

    public void setNoidungTT(String noidungTT) {
        this.noidungTT = noidungTT;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }
}
