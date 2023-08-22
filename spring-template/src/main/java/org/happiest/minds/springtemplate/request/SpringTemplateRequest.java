package org.happiest.minds.springtemplate.request;

import lombok.Data;

import java.util.List;

@Data
public class SpringTemplateRequest {

    private String groupId;
    private String artifactId;
    private String name;
    private String description;
    private String packagingType;
    private List<String> dependencies;
}
