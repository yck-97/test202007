package com.yaorange.ssm.service.impl;

import com.yaorange.ssm.mapper.PhoneMapper;
import com.yaorange.ssm.pojo.Phone;
import com.yaorange.ssm.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 16-07-08 16:11:28
 */
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

    @Resource
    private PhoneMapper phoneMapper;

    @Override
    public void savePhone(Phone[] phones) {
        for (Phone phone: phones) {
            phoneMapper.insert(phone);
        }
    }

    @Override
    public int findCount(Phone phone) {
        return phoneMapper.selectCount(phone);
    }

    @Override
    public void daletePhone(Phone[] phones) {
        for (Phone phone: phones) {
            phoneMapper.delete(phone);
        }
    }

    @Override
    public List<Phone> selectAll() {
        return phoneMapper.selectAll();
    }


}
