package org.happiest.minds.springtemplate.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.service.SpringTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/spring/template")
@Slf4j
public class SpringTemplateController {

    @Autowired
    SpringTemplateService springTemplateService;

    @GetMapping("download")
    public ResponseEntity<?> downloadTemplate(@RequestBody SpringTemplateRequest springTemplateRequest,
                                 HttpServletResponse response) {
        log.info("springTemplateRequest: {}", springTemplateRequest);

        if (!springTemplateService.getDependency().containsAll(springTemplateRequest.getDependencies())) {
            return new ResponseEntity<>("No such dependency present", HttpStatus.BAD_REQUEST);
        }

        springTemplateService.downloadTemplate(response, springTemplateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
