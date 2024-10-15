package com.example.vediosystem.controller;


import com.example.vediosystem.dao.BarrageDao;
import com.example.vediosystem.domain.Barrage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "barrage")
public class
BarrageController {
    @Autowired
    private BarrageDao barrageDao;

    @Autowired
    private Gson gson;
    @ResponseBody
    @RequestMapping(value = "v3", method = RequestMethod.GET)
    public String getv3(@RequestParam String id) throws Exception {
        System.out.println("get"+id);
        Map map = new HashMap();
        List<Barrage> result = new ArrayList<>();
        result = barrageDao.findAll(id);
        System.out.println(result);
        List data = new ArrayList();
        for (int i=0;i<result.size();i++){
            List re = new ArrayList();
            re.add(result.get(i).getTime());
            re.add(result.get(i).getType());
            re.add(result.get(i).getColor());
            re.add(result.get(i).getAuthor());
            re.add(result.get(i).getText());
            data.add(re);
        }
        System.out.println(data);
        map.put("code", DPlayerConstants.DPLAYER_SUCCESS_CODE);
        map.put("data",data);
        return gson.toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "v3", method = RequestMethod.POST)
    public String postv3(@RequestBody Map<String,String> param, HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        System.out.println("post"+param);
        Barrage danmu = new Barrage(param.get("time"), Integer.valueOf(param.get("type")), param.get("color"), param.get("author"), param.get("text"), param.get("id"));
        barrageDao.addDanMu(danmu);
        map.put("code",DPlayerConstants.DPLAYER_SUCCESS_CODE);
        map.put("data",param);
        return gson.toJson(map);
    }
}
