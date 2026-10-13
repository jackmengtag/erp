package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * 制造企业ERP系统 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ErpApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ErpApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  制造企业ERP系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "  ______  _____  _____  \n" +
                " |  ____|  __  \\|  __ \\ \n" +
                " | |__  | |__) | |__) |\n" +
                " |  __| |  _  /|  ___/ \n" +
                " | |____| | \\ \\| |     \n" +
                " |______|_|  \\_\\_|     \n");
    }
}
