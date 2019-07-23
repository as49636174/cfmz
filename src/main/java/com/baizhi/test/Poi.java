package com.baizhi.test;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Poi {


    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        for(int i=0;i<=10;i++){
            User user = new User();
            user.setId(i+"");
            user.setCity("哈哈");
            list.add(user);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("信息");
        //合并第一行的前几行
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 3);
    }
}
