package vn.fit.nguyennamtrungnguyen.model;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class TinTuc {
    private int MATT;
    private String TIEUDE;
    private String NOIDUNGTT;
    private String LIENKET;
    private int MADM;

    public TinTuc() {
    }

    public TinTuc(int MATT, String TIEUDE, String NOIDUNGTT, String LIENKET, int MADM) {
        this.MATT = MATT;
        this.TIEUDE = TIEUDE;
        this.NOIDUNGTT = NOIDUNGTT;
        this.LIENKET = LIENKET;
        this.MADM = MADM;
    }

    public int getMATT() {
        return MATT;
    }

    public void setMATT(int MATT) {
        this.MATT = MATT;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public String getNOIDUNGTT() {
        return NOIDUNGTT;
    }

    public void setNOIDUNGTT(String NOIDUNGTT) {
        this.NOIDUNGTT = NOIDUNGTT;
    }

    public String getLIENKET() {
        return LIENKET;
    }

    public void setLIENKET(String LIENKET) {
        this.LIENKET = LIENKET;
    }

    public int getMADM() {
        return MADM;
    }

    public void setMADM(int MADM) {
        this.MADM = MADM;
    }
}
