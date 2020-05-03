package org.mycode.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebConfig.class, SecurityConfig.class, DatabaseConfig.class})
public class CommonConfig {
}
