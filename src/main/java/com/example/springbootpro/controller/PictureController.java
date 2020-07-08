package com.example.springbootpro.controller;

import com.example.springbootpro.entity.Picture;
import com.example.springbootpro.service.PictureService;
import com.example.springbootpro.utils.ExcelUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pic")
public class PictureController {
    @Resource
    private ExcelUtils excelUtils;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/picList")
    public JSONObject getPicListByPage(int current, int pageSize) {
        return pictureService.selectAllPicByPage(current, pageSize);
    }

    @RequestMapping("/addPic")
    public int addPic(String name, String pictureAddress, int lastPage) {
        return pictureService.insertPic(name, pictureAddress, lastPage);
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(int current, int pageSize, HttpServletResponse response) throws IOException {
        List<Picture> pictures = pictureService.exportExcel(current, pageSize);
        String fileName = "图片导出";
       // String[] titles = {"序号", "图片名称", "图片路径"};
        //List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
        //LinkedHashMap<String,Object> data1=new LinkedHashMap<String,Object>();
       // for (int i = 0; i < pictures.size(); i++) {
       //     data1.put(i+"",pictures.get(i));
       // }
      //  data.add(data1);
     //   ExcelUtils.export(response,pictures,titles,fileName);

        excelUtils.writeExcel(response,pictures,Picture.class,fileName);
    }
}
