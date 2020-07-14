package com.yaorange.ssm.controller;

import com.yaorange.ssm.pojo.ErrPhone;
import com.yaorange.ssm.pojo.Phone;
import com.yaorange.ssm.service.PhoneService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 11-07-08 11:11:58
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Resource(name = "phoneService")
    private PhoneService phoneService;

    // 上传文件
    @PostMapping("/importFile")
    public Map<String,Object> importPhone(@RequestParam("file") MultipartFile file) throws IOException {
        // 创建用户返回数据的集合
        List<Phone> trueList=new ArrayList<>();  // 有效数据
        List<ErrPhone> falseList=new ArrayList<>();  // 无效数据
//        List<Object> falseList=new ArrayList<>();
        Map<String,Object> phoneList = new HashMap<>();
        // 判断上传文件是否为空
        if (file.isEmpty()){
            phoneList.put("status","上传文件为空");
            return phoneList;
        }
        // 获取文件上传输入流
        InputStream inputStream = file.getInputStream();
        // 获取缓冲流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        // 定义正则表达式判断String中的数据是否为数字
        String reg = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        // 读取文件中的数据
        String readLine = null;
        while ((readLine=bufferedReader.readLine())!=null) {
            String[] arrPhone = readLine.split(",");
            Phone phone = new Phone();
            ErrPhone errPhone = new ErrPhone();
            // 判断数据是否有四条
            if (arrPhone.length==3) {
                if (arrPhone[2].matches(reg)){ // 判断字符串中数据是否为数字
                    phone.setName(arrPhone[1]);
                    phone.setPrice(Double.parseDouble(arrPhone[2]));
                    phone.setRemark("有效数据");
                    trueList.add(phone);
                } else {
                    errPhone.setName(arrPhone[1]);
                    errPhone.setPrice(arrPhone[2]);
                    errPhone.setRemark("价格栏不是数字");
                    // 将数据放入无效数据集合
                    falseList.add(errPhone);
                }
            } else {
                if (arrPhone[1]!=null){
                    errPhone.setName(arrPhone[1]);
                }
                errPhone.setRemark("数据无效");
                // 将数据放入无效数据集合
                falseList.add(errPhone);
//                for (int i=0;i<arrPhone.length;i++){
//                    falseList.add(arrPhone[i]);
//                }
            }
        }
        phoneList.put("rightList",trueList);
        phoneList.put("errList",falseList);
        return phoneList;
    }

    //数据库写入文件
    @PostMapping("/saveFile")
    public String savePhone(@RequestBody Phone[] phones){
        String str= "";
        if (phones.length>0){
            // 判断需要导入的数据是否已经存在
            for (Phone phone: phones) {
                // 判断该数据在数据库中是否存在
                int count=phoneService.findCount(phone);
                if (count>0){
                    // 数据库中存在该数据，无法导入
                    return  "商品"+phone.getName()+"已经存在，请修改数据";
                }
            }
            // 将数据批量写入数据库
            phoneService.savePhone(phones);
            str= "数据导入成功";
        }else{
            str="导入数据为空";
        }
        return str;
    }

    // 删除已导入数据
    @PostMapping("/deletephone")
    public String deletePhone(@RequestBody Phone[] phones){
        String str = "";
        if (phones.length>0){
            for (Phone phone: phones) {
                // 判断该数据在数据库中是否存在
                /* TODO
                查询数据库中所有数据，返回集合
                判断前端传入集合与现有数据集合是否有交集
                使用list的retainAll方法判断集合是否有交集
                 */
                int count = phoneService.findCount(phone);
                if (count==0){
                    // 数据库中不存在该数据，无法删除
                    return "商品"+phone.getName()+"不存在，无法取消导入";
                }
                phoneService.daletePhone(phones);
                str= "所选商品已取消导入";
            }
        }else{
            str="请选择需要取消导入的数据";
        }
        return str;
    }

}

