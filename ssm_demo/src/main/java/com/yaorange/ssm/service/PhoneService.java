package com.yaorange.ssm.service;

import com.yaorange.ssm.pojo.Phone;

import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 11-07-08 11:21:53
 */
public interface PhoneService {

    void savePhone(Phone[] phones);

    int findCount(Phone phone);

    void daletePhone(Phone[] phones);

    List<Phone> selectAll();
}
