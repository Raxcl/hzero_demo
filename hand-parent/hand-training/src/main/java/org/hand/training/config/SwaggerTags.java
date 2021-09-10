package org.hand.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String HELLO_WORLD = "HelloWorld";
    public static final String IAM_USER = "IamUserDemo";
    public static final String HAND_MESSAGE = "Hello Message";
    public static final String HAND_WORKFLOW_PLUS = "Hello Workflow Plus";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(EXAMPLE, "EXAMPLE 案例"),
                new Tag(HELLO_WORLD, "hello world"),
                new Tag(IAM_USER, "IamUserDemo"),
                new Tag(HAND_MESSAGE, "Hello Message"),
                new Tag(HAND_WORKFLOW_PLUS, "Hello Workflow Plus")
        );
    }
}
