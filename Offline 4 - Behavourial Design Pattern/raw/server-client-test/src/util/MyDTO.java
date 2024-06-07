package util;

import java.io.Serializable;

public class MyDTO implements Serializable {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}