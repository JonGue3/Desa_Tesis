package com.tesis.v1.to;

import com.tesis.v1.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

public class UpdateSelectDto {

    private List<UserEntity> userEntityList;

    private List<UserEntity> userEntityListRecusos;

    private Set<UserEntity> userEntitySet;

    private Set<UserEntity> userEntitySet1;

    private List<UserEntity> userEntityList1;

    public UpdateSelectDto(List<UserEntity> userEntityList, List<UserEntity> userEntityListRecusos, Set<UserEntity> userEntitySet) {
        this.userEntityList = userEntityList;
        this.userEntityListRecusos = userEntityListRecusos;
        this.userEntitySet = userEntitySet;
    }

    public UpdateSelectDto(Set<UserEntity> userEntitySet1, List<UserEntity> userEntityList1) {
        this.userEntitySet1 = userEntitySet1;
        this.userEntityList1 = userEntityList1;
    }

    public Set<UserEntity> getUserEntitySet1() {
        return userEntitySet1;
    }

    public void setUserEntitySet1(Set<UserEntity> userEntitySet1) {
        this.userEntitySet1 = userEntitySet1;
    }

    public List<UserEntity> getUserEntityList1() {
        return userEntityList1;
    }

    public void setUserEntityList1(List<UserEntity> userEntityList1) {
        this.userEntityList1 = userEntityList1;
    }

    public UpdateSelectDto() {
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<UserEntity> getUserEntityListRecusos() {
        return userEntityListRecusos;
    }

    public void setUserEntityListRecusos(List<UserEntity> userEntityListRecusos) {
        this.userEntityListRecusos = userEntityListRecusos;
    }

    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
    }

    @Override
    public String toString() {
        return "UpdateSelectDto{" +
                "userEntityList=" + userEntityList +
                ", userEntityListRecusos=" + userEntityListRecusos +
                ", userEntitySet=" + userEntitySet +
                ", userEntitySet1=" + userEntitySet1 +
                ", userEntityList1=" + userEntityList1 +
                '}';
    }
}
