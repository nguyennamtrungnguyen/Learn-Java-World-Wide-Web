package vn.fit.nguyennamtrungnguyen.model;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class DanhMuc {
    private int MADM;
    private String TENDANHMUC;
    private String NGUOIQUANLY;
    private String GHICHU;

    public DanhMuc() {
    }

    public DanhMuc(int MADM, String TENDANHMUC, String NGUOIQUANLY, String GHICHU) {
        this.MADM = MADM;
        this.TENDANHMUC = TENDANHMUC;
        this.NGUOIQUANLY = NGUOIQUANLY;
        this.GHICHU = GHICHU;
    }

    public int getMADM() {
        return MADM;
    }

    public void setMADM(int MADM) {
        this.MADM = MADM;
    }

    public String getTENDANHMUC() {
        return TENDANHMUC;
    }

    public void setTENDANHMUC(String TENDANHMUC) {
        this.TENDANHMUC = TENDANHMUC;
    }

    public String getNGUOIQUANLY() {
        return NGUOIQUANLY;
    }

    public void setNGUOIQUANLY(String NGUOIQUANLY) {
        this.NGUOIQUANLY = NGUOIQUANLY;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }
}
