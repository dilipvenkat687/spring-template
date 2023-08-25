package org.happiest.minds.controller;

import org.happiest.minds.springtemplate.controller.SpringTemplateController;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.service.SpringTemplateService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class SpringTemplateControllerTest {
    @Mock

    private SpringTemplateService springTemplateService;
    @InjectMocks

    private SpringTemplateController springTemplateController;
    private MockHttpServletResponse response;

    @BeforeEach

     void setUp() {

        MockitoAnnotations.initMocks(this);

        response = new MockHttpServletResponse();
    }
    @Test

     void testDownloadTemplate_ValidRequest() {

        SpringTemplateRequest request = new SpringTemplateRequest();

        request.setDependencies(Arrays.asList("dependency1", "dependency2"));

        request.setPackagingType("jar");

        when(springTemplateService.getDependency()).thenReturn(Arrays.asList("dependency1", "dependency2"));

        ResponseEntity<?> responseEntity = springTemplateController.downloadTemplate(request, response);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(springTemplateService, times(1)).downloadTemplate((response), (request));

    }
    @Test

     void testDownloadTemplate_InvalidDependency() {

        SpringTemplateRequest request = new SpringTemplateRequest();

        request.setDependencies(Arrays.asList("dependency1", "dependency2"));

        request.setPackagingType("jar");

        when(springTemplateService.getDependency()).thenReturn(Arrays.asList("dependency1"));

        ResponseEntity<?> responseEntity = springTemplateController.downloadTemplate(request, response);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        assertEquals("No such dependency present", responseEntity.getBody());

        verify(springTemplateService, never()).downloadTemplate(any(), any());

    }
    @Test

     void testDownloadTemplate_InvalidPackagingType() {
        SpringTemplateRequest request = new SpringTemplateRequest();
        request.setDependencies(Arrays.asList("dependency1", "dependency2"));
        request.setPackagingType("invalidType");
        when(springTemplateService.getDependency()).thenReturn(Arrays.asList("dependency1", "dependency2"));
        ResponseEntity<?> responseEntity = springTemplateController.downloadTemplate(request, response);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Packaging type should be war or jar", responseEntity.getBody());
        verify(springTemplateService, never()).downloadTemplate(any(), any());

    }

}