package com.example.demo.service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.vo.request.UserRequestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by admin on 2017/7/23.
 */
@Service
@RequestMapping
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存数据
     * @param requestUser
     */
    public User save(UserRequestVo requestUser){

        User user=null;
        if(requestUser.getId()==null||requestUser.getId()<=0){
            user=new User();
            BeanUtils.copyProperties(requestUser,user);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
        }else{
            user=userRepository.findOne(requestUser.getId());
            if(user.getAge()!=requestUser.getAge()){
                user.setAge(requestUser.getAge());
            }

            if(user.getAddress().equals(requestUser.getAddress())==false){
                user.setAddress(requestUser.getAddress());
            }

            if(user.getBirthDate().equals(requestUser.getBirthDate())==false){
                user.setBirthDate(requestUser.getBirthDate());
            }

            user.setUpdateTime(new Date());
        }
        user=userRepository.save(user);
        return user;
    }

}
