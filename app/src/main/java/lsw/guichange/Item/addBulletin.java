package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-15.
 */

public class addBulletin {
    public String bulletinName;
    public String bulletinAddress;

    public String getBulletinName() {
        return bulletinName;
    }

    public void setBulletinName(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    public String getBulletinAddress() {
        return bulletinAddress;
    }

    public void setBulletinAddress(String bulletinAddress) {
        this.bulletinAddress = bulletinAddress;
    }

    public addBulletin() {
    }

    public addBulletin(String bulletinName, String bulletinAddress) {
        this.bulletinName = bulletinName;
        this.bulletinAddress = bulletinAddress;
    }
}
