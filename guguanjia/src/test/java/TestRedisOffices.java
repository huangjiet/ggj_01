import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysOfficeMapper;
import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author hjt
 * @description
 * @date 2019/11/26
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestRedisOffices {

    @Autowired
    SysOfficeService service;

    @Autowired
    SysOfficeMapper mapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    @Test
    public  void  testSelectAll(){
        List<SysOffice> sysOffices = service.selectAll();

        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();

        opsForValue.set("sysOffices",sysOffices);

        System.out.println(".......................");

    }


    @Test
    public void testSelectAll2(){
        List<SysOffice> sysOffices = service.selectAll();
        System.out.println(sysOffices);

        List<SysOffice> sysOffices2 = service.selectAll();
        System.out.println(sysOffices2);

        List<SysOffice> sysOffices3 = service.selectAll();
        System.out.println(sysOffices3);
    }


    @Test
    public void testSelectByOid(){
        SysOffice sysOffice = service.selectByOid(2);
        System.out.println(sysOffice);

        SysOffice sysOffice2 = service.selectByOid(8);
        System.out.println(sysOffice2);

        SysOffice sysOffice3 = service.selectByOid(5);
        System.out.println(sysOffice3);
    }

    /**
     * 测试设置全部清除的操作
     */
    @Test
    public void testUpdate(){
        SysOffice sysOffice = service.selectByOid(2);
        sysOffice.setEmail("2@qq.com");
        service.updateByPrimaryKey(sysOffice);
    }

}
