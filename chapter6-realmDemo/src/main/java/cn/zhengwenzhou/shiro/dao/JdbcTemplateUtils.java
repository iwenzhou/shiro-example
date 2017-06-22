package cn.zhengwenzhou.shiro.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description JDBC工具类
 */

public class JdbcTemplateUtils
{

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate()
    {
        if(jdbcTemplate == null)
        {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate()
    {

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("root");

        return new JdbcTemplate(ds);
    }

}
