package cn.yhs.learn.dao;

import cn.yhs.learn.domain.OneList;
import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.TwoList;
import cn.yhs.learn.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.DaoTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 12:07
 * @Description: Dao层测试
 **/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private OneListDao oneListDao;
    @Autowired
    private TwoListDao twoListDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;
    @Test
    public void testAddPermission(){
      permissionDao.addPermission(new Permission(null, null, "/test", "测试用的"));


    }

    @Test
    public void testUserFindAll() {
        List<Users> usersList = userDao.findAll();
        for (Users users : usersList) {
            System.out.println("users = " + users);
        }
    }

    @Test
    public void testOneListFindAll() {
        List<OneList> oneLists = oneListDao.findAllWithTwoList();
        for (OneList oneList : oneLists) {
            System.out.println("oneList = " + oneList);
        }
    }

    @Test
    public void testTwoList() {
        List<TwoList> twoLists = twoListDao.findTwoListByOneId(3);
        for (TwoList twoList : twoLists) {
            System.out.println("twoList = " + twoList);
        }

    }

    @Test
    public void test() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }

    }
}
