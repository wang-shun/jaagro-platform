package com.jaagro.microservice.platform.tms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.jaagro.microservice.platform.tms.mapper")
public class JaagroPlatformTmsApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * Mybatis逆向生成测试方法
     * @throws Exception
     */
//    @Test
//    public void mybatisGenerator() throws Exception {
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        File configFile = new File("/Users/tony/Documents/git_repository/Git/jaagro-platform/jaagro-platform-tms/src/main/resources/mybatis/generatorConfig.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//    }
}
