
import com.alibaba.druid.pool.DruidDataSource;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.AppVersionMapper;
import com.dfbz.dao.QualificationMapper;
import com.dfbz.entity.AppVersion;
import com.dfbz.entity.Qualification;
import com.dfbz.service.AppVersionService;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSSM {

    @Autowired
    QualificationMapper mapper;

    @Autowired
    QualificationService service;


@Test
    public void testQualicationService(){
    Map<String, Object> map = new HashMap<>();
    map.put("type","1");
    map.put("begin","2018-01-11");
    PageInfo<Qualification> qualificationPageInfo = service.selectByCondition(map);
    System.out.println(qualificationPageInfo);
}

}
