package com.example.md_back;

import com.example.md_back.Handler.ApprovalStatusHandler;
import com.example.md_back.Handler.ApprovalTypeHandler;
import com.example.md_back.Handler.WordTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.example.md_back.mappers")
@SpringBootApplication
public class MdBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdBackApplication.class, args);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
        sessionFactory.setMapperLocations(res);
        sessionFactory.setTypeHandlers(new TypeHandler[]{new ApprovalStatusHandler(), new ApprovalTypeHandler(), new WordTypeHandler()});
        return sessionFactory.getObject();
    }
}
