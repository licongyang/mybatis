/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要视频资料或者咨询课程的可以加若曦老师的QQ：2408349392
 * author：鲁班学院-商鞅老师
 */
public class Test {



  public static void main(String[] args) throws Exception {
    String resource = "mybatis.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //xml解析完成
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    //默认sqlsession不会自动提交
    SqlSession sqlSession = sqlSessionFactory.openSession();

    //从调用者角度来讲 与数据库打交道的对象 SqlSession
    //通过动态代理 去帮我们执行SQL
    //拿到一个动态代理后的Mapper
    TestMapper mapper = sqlSession.getMapper(TestMapper.class);
    Map<String,Object> map = new HashMap<>();
    map.put("id","1");
    //因为一级缓存 这里不会调用两次SQL
    mapper.insert("6","1");
    sqlSession.commit();
    //如果有二级缓存 这里就不会调用两次SQL
    sqlSession.close();

  }
}
