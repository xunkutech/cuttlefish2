package com.example.demo.service.excel;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.bean.StudentBean;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author : Sarah Xu
 * @date : 2019-06-12
 */
@Service
public class ExportExcelService {
  private final StudentDao studentDao;

  @Autowired
  public ExportExcelService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }
  /**
   * @Description: 导出excel到指定位置
   * @Param: [destination]
   *
   * @return: void @Author: Sarah Xu @Date: 2019/6/5
   */
  public void exportExcel(OutputStream destination) {

    // 创建新工作簿
    HSSFWorkbook workBook = new HSSFWorkbook();
      // 新建工作表
    final HSSFSheet sheet = workBook.createSheet("Students");
    sheet.setColumnWidth(0, 7000);
    sheet.setColumnWidth(1, 3100);
    sheet.setColumnWidth(2, 5200);
    sheet.setColumnWidth(3, 13300);
    HSSFRow row0 = sheet.createRow(0);
    HSSFCell title = row0.createCell(0);
    title.setCellValue("ID");
    title = row0.createCell(1);
    title.setCellValue("姓名");
    title = row0.createCell(2);
    title.setCellValue("性别");
    title = row0.createCell(3);
    title.setCellValue("创建时间");
    final List<StudentBean> studentBeans =
        studentDao.getAllStudents();
    IntStream.range(0, studentBeans.size())
        .forEach(
            index -> {
              HSSFRow row = sheet.createRow(index + 1);

              StudentBean studentBean = studentBeans.get(index);
              HSSFCell cell = row.createCell(0);
              cell.setCellValue(studentBean.getId().toString());//此处转成String传入，否则单元格数据类型为数字，会产生精度
              cell = row.createCell(1);
              cell.setCellValue(studentBean.getName());
              cell = row.createCell(2);
              cell.setCellValue(studentBean.getGender().toString());
              cell = row.createCell(3);
              cell.setCellValue(
                  ZonedDateTime.ofInstant(
                          Instant.ofEpochMilli(studentBean.getCreationDate()),
                          ZoneId.systemDefault())
                      .toLocalDateTime()
                      .toString());
            });

    // 创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
    // 创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
    // 设置单元格的值,即C1的值(第一行,第三列)
    // 输出到磁盘中
    try {
      workBook.write(destination);
      workBook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
