package org.mycode.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@PropertySource(value = "classpath:/config.properties")
public class SetupConfig {
    private static Properties properties = new Properties();
    private Environment environment;

    @Autowired
    public SetupConfig(Environment environment) {
        this.environment = environment;
        properties.setProperty("jdbc.driver", environment.getProperty("jdbc.driver"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setupConfigFile(String[] args) {
        List<String> argList = Arrays.stream(args).map(String::toLowerCase).collect(Collectors.toList());
        String url = "jdbc:mysql://&1:&2/&3?serverTimezone=UTC";
        for (int i = 0; i < argList.size(); i++) {
            if (i % 2 == 0 && argList.get(i).matches("-((hostname)|(port)|(database)|(username)|(password))") &&
                    argList.get(i + 1).charAt(0) != '-') {
                switch (argList.get(i)) {
                    case "-hostname":
                        url = url.replace("&1", argList.get(i + 1));
                        break;
                    case "-port":
                        url = url.replace("&2", argList.get(i + 1));
                        break;
                    case "-database":
                        url = url.replace("&3", argList.get(i + 1));
                        break;
                    case "-username":
                        properties.setProperty("jdbc.user", argList.get(i + 1));
                        break;
                    case "-password":
                        properties.setProperty("jdbc.password", argList.get(i + 1));
                        break;
                }
            }
        }
        if (!url.contains("&")) {
            properties.setProperty("jdbc.url", url);
        } else {
            properties.setProperty("jdbc.url", environment.getProperty("jdbc.url"));
        }
        if (!properties.containsKey("jdbc.user")) {
            properties.setProperty("jdbc.user", environment.getProperty("jdbc.user"));
        }
        if (!properties.containsKey("jdbc.password")) {
            properties.setProperty("jdbc.password", environment.getProperty("jdbc.password"));
        }
    }
}
