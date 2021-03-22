package com.example.demo.vo.result;

import java.util.List;

/**
 * Created by admin on 2017/7/23.
 */
public class UserListResultVo {

    Integer total;

    List<UserResultVo> userResultVoList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UserResultVo> getUserResultVoList() {
        return userResultVoList;
    }

    public void setUserResultVoList(List<UserResultVo> userResultVoList) {
        this.userResultVoList = userResultVoList;
    }
}
