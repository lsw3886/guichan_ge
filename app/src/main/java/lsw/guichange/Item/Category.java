package lsw.guichange.Item;

import java.util.List;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class Category {

    public String name;
    public int image;
    public List<Bulletin> bItems;
    public int ischecked = 0;

    public List<Bulletin> getbItems() {
        return bItems;
    }

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public void setbItems(List<Bulletin> bItems) {
        this.bItems = bItems;
    }

    public Category(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
