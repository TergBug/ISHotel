package org.mycode.dto;

import java.util.Objects;

public class TempInfo {
    private String info;

    public TempInfo() {
        info = "";
    }

    public TempInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TempInfo{" +
                "info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempInfo tempInfo = (TempInfo) o;
        return Objects.equals(info, tempInfo.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
