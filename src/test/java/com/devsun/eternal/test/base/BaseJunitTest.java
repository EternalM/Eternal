package com.devsun.eternal.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试的基础controller类
 * @author devsun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext*.xml","classpath:spring-mvc.xml"})
public class BaseJunitTest {

}
