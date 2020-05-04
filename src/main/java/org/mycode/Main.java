package org.mycode;

import org.apache.catalina.startup.Tomcat;
import org.mycode.configs.SetupConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SetupConfig.class);
        applicationContext.getBean(SetupConfig.class).setupConfigFile(args);
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.parseInt(webPort));
        tomcat.addWebapp("/", new File("/").getAbsolutePath());
        tomcat.init();
        tomcat.start();
        tomcat.getServer().await();
    }
}
