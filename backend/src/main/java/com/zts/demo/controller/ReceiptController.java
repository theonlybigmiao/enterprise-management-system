package com.zts.demo.controller;
import com.zts.demo.biz.ReceiptBiz;
import com.zts.demo.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Controller
@ResponseBody
public class ReceiptController {
    @Autowired
    private ReceiptBiz biz;

    public ReceiptController(ReceiptBiz biz) {
        this.biz = biz;
    }

    @RequestMapping("/receipt/addReceipt")
    @ResponseBody
    public Map addReceipt(@RequestBody Receipt receipt){
        System.out.println(receipt.toString());
        int flag=biz.addReceipt(receipt);
        Map res=new HashMap<>();
        if(flag>0){
            res.put("isOK",true);
            res.put("msg","发送成功！");
        }
        res.put("isOK",true);
        return res;
    }


}
