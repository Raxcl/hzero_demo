package org.hand.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 * @author 33265
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String HAND_MESSAGE = "Hand Message";
    public static final String HAND_INTERFACE = "Hand Interface";
    public static final String HAND_REPORT = "Hand Report";
    public static final String HAND_WORKFLOW = "Hand Workflow";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(EXAMPLE, "EXAMPLE"),
                new Tag(HAND_MESSAGE, "Hand Message"),
                new Tag(HAND_INTERFACE, "Hand Interface"),
                new Tag(HAND_REPORT, "Hand Report"),
                new Tag(HAND_WORKFLOW, "Hand Workflow")
        );
    }
}
