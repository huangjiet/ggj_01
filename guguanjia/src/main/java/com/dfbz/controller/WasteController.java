package com.dfbz.controller;

import com.dfbz.entity.Waste;
import com.dfbz.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hjt
 * @description
 * @date 2019/11/24
 */

@RestController
@RequestMapping("manager/waste")
public class WasteController {

    @Autowired
    WasteService service;


    @RequestMapping("selectWaste")
    public List<Waste> selectWaste(long selected ){
        Waste waste = new Waste();
        waste.setParentId(selected);
        return service.select(waste);
    }

}
