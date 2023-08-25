package org.happiest.minds.springtemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.service.SpringTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("api/spring/template")
@Slf4j
public class SpringTemplateController {

    @Autowired
    SpringTemplateService springTemplateService;

    @PostMapping("download")
    public ResponseEntity<String> downloadTemplate(@Valid @RequestBody SpringTemplateRequest springTemplateRequest,
                                 HttpServletResponse response) {
        log.info("springTemplateRequest: {}", springTemplateRequest);

        if (!springTemplateService.getDependency().containsAll(springTemplateRequest.getDependencies())) {
            return new ResponseEntity<>("No such dependency present", HttpStatus.BAD_REQUEST);
        }

        if (!springTemplateRequest.getPackagingType().matches("(jar|war)")){
            return new ResponseEntity<>("Packaging type should be war or jar", HttpStatus.BAD_REQUEST);
        }

        springTemplateService.downloadTemplate(response, springTemplateRequest);
        return new ResponseEntity<>(HttpStatus.OK);


    }

}
