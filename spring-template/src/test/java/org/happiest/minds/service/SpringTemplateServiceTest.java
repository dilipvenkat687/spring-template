package org.happiest.minds.service;

import org.happiest.minds.springtemplate.service.SpringTemplateService;
import org.happiest.minds.springtemplate.utility.XMLUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class SpringTemplateServiceTest {

    @Mock

    private XMLUtility xmlUtility;

    private SpringTemplateService springTemplateService;

    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

        springTemplateService = new SpringTemplateService();
    }

    @Test

    void testReplaceText_ValidReplacement() throws IOException {

        String filePath = "path/to/some/file.txt";

        String originalText = "OriginalText";

        String replacementText = "ReplacementText";


        File tempFile = File.createTempFile("testFile", ".txt");

        FileWriter writer = new FileWriter(tempFile);

        writer.write(originalText);

        writer.close();

        springTemplateService.replaceText(tempFile.getAbsolutePath(), originalText, replacementText);

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));

        String fileContent = reader.readLine();

        reader.close();
        assertTrue(fileContent.contains(replacementText));

        tempFile.delete();

    }

}
