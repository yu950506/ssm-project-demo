package cn.yhs.learn.test;

import cn.yhs.learn.controller.EmployeeController;
import cn.yhs.learn.domain.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.test.ControllerTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/5 8:12
 * @Description: controller层的web测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml"})
@WebAppConfiguration
public class ControllerTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testBeans() {
      /*  String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }*/

    }

    @Test
    public void testEmp() throws Exception {
        // 模拟客户端发送请求
        //  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/emp/findAll?pageNum=2")).andReturn();
        // 发送请求方式2
        //  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/emp/findAll?pageNum=2")).andReturn();
        // 设置参数可以在url中进行拼接。也可以使用方法进行填充
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/emp/findAll").param("pageNum", "9")).andReturn();

    }

    @Test
    public void testSaveEmp() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      //  params.add("name", "喻汉生");
        params.add("gender", "F");
        params.add("email", "502166122@qq.com");
        params.add("birthday", "2020/08/08");
        params.add("deptId", "1");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/emp/saveEmp").params(params)).andReturn();

    }
}
