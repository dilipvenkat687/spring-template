package org.happiest.minds.springtemplate.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SpringTemplateRequest {

    @NotBlank(message = "groupId cannot be blank")
    private String groupId;
    @NotBlank(message = "artifactId cannot be blank")
    private String artifactId;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotBlank(message = "packagingType cannot be blank")
    private String packagingType;
    @NotNull(message = "dependencies cannot be null")
    private List<String> dependencies;


}
