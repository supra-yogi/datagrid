package com.yogi.demodatagrid.bean;

import java.io.Serializable;

/**
 * <pre>
 *     com.yogi.demodatagrid.bean.User
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Des 2022 16:09
 */
public class User implements Serializable {
    private String name;
    private String address;

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
