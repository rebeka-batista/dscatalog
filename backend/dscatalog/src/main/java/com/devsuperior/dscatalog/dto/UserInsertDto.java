package com.devsuperior.dscatalog.dto;

import java.io.Serializable;

public class UserInsertDto extends UserDto implements Serializable {

    private String password;

    public UserInsertDto() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
