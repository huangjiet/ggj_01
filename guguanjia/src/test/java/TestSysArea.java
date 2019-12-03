import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysAreaMapper;
import com.dfbz.entity.SysArea;
import com.dfbz.listener.SysAreaListener;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author hjt
 * @description
 * @date 2019/11/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysArea {

    @Autowired
    SysAreaMapper mapper;

    @Autowired
    SysAreaService service;

    @Test
    public  void testSelectPage(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("aid",1);
        PageInfo<SysArea> pageInfo = service.selectByPage(map);
        System.out.println(pageInfo);
    }

    @Test

    public  void testWrite(){
        List<SysArea> sysAreas = mapper.selectByPid(1L);
        ExcelWriter excelWriter = EasyExcel.write("D:\\excel\\area.xlsx",SysArea.class).build();

        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();

        excelWriter.write(sysAreas,writeSheet);
        excelWriter.finish();
    }

    @Test

    public void testRead(){
        ExcelReader excelReader = EasyExcel.read("D:\\excel\\area.xlsx",SysArea.class,new SysAreaListener()).build();

        ReadSheet readSheet = EasyExcel.readSheet(0).build();

        excelReader.read(readSheet);
        excelReader.finish();
    }





    @Test
    public void testRead2(){
        ExcelReader excelReader = EasyExcel.read("D:\\excel\\area.xlsx",SysArea.class,new SysAreaListener(mapper)).build();

        ReadSheet readSheet = EasyExcel.readSheet(0).build();

        excelReader.read(readSheet);
        excelReader.finish();
    }

    @Test
    public void testInsert(){
        List<SysArea> sysAreas = mapper.selectAll();
        int i = mapper.insertBatch(sysAreas);
    }

}
