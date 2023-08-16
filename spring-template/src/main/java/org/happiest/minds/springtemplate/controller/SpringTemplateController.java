package org.happiest.minds.springtemplate.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.service.SpringTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/spring/template")
public class SpringTemplateController {

    @Autowired
    SpringTemplateService springTemplateService;

    @GetMapping("download")
    public void downloadTemplate(@RequestBody SpringTemplateRequest springTemplateRequest,
                                 HttpServletResponse response) {
        springTemplateService.downloadTemplate(response, springTemplateRequest);
    }

}
