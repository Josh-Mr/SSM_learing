import com.josh.dao.IUserDAO;
import com.josh.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Josh
 * @version 1.0.0
 * @ClassName UserTest.java
 * @Description TODO
 * @createTime 2020-04-20 23:05:00
 */
public class UserTest {

    private InputStream inputStream;
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }
    @After
    public void destroy() throws IOException {
        // 手动提交
        sqlSession.commit();
        inputStream.close();
        sqlSession.close();
    }


    @Test
    public void TestFindAll(){
        IUserDAO mapper = sqlSession.getMapper(IUserDAO.class);
        List<User> all = mapper.findAll();
        for (User user : all){
            System.out.println("-----------");
            System.out.println(user.toString());
        }
    }

    /**
     * 使用延迟加载测试
     */
    @Test
    public void TestFindById(){
        IUserDAO iUserDAO = sqlSession.getMapper(IUserDAO.class);
        List<User> byId = iUserDAO.findById(46);
        for (User user : byId){
            System.out.println("哈哈哈--");
            System.out.println(user.getId());
            /**/
//            System.out.println(user.getAccounts());
        }
    }
}
