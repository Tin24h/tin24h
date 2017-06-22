package tintuc.diepnvph04430.diep.tintuc;

/**
 * Created by tuandeptrai on 15/10/2016.
 */

public class TinTuc {
    private String Title;
    private String link;
    private String hinhanh;

    public TinTuc(String title, String link, String hinhanh) {
        Title = title;
        this.link = link;
        this.hinhanh = hinhanh;
    }

    public TinTuc(String title) {
        Title = title;
    }

    public TinTuc() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
