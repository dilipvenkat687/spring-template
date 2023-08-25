package org.happiest.minds.springtemplate.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.utility.XMLUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class SpringTemplateService {

    public static final String DOWNLOAD_PIPIMPROVEMENT_SRC_MAIN_JAVA = "download/pipimprovement/src/main/java/";
    public static final String SLASH = "/";
    public static final String DOWNLOAD_PIPIMPROVEMENT_SRC_TEST_JAVA = "download/pipimprovement/src/test/java/";
    @Autowired
    XMLUtility xmlUtility;

    public void downloadTemplate(HttpServletResponse response, SpringTemplateRequest springTemplateRequest) {
        try {

            /* Copy sample template from reference folder to download folder */
            FileUtils.copyDirectory(new File("reference"), new File("download"));


            /* Delete folders in src/main. Deleting in order to make directories according to the group id and artifact id */
            FileUtils.deleteDirectory(new File("download/pipimprovement/src/main"));
            FileUtils.deleteDirectory(new File("download/pipimprovement/src/test"));


            /* Replace "." with "//" in group id to make directories */
            String groupIdPath = springTemplateRequest.getGroupId().replaceAll("[.]", "//");


            /* Replace non alphabets from artifact id to create directory */
            String artifactId = springTemplateRequest.getArtifactId().replaceAll("[^a-zA-Z]", "");


            /* Path where Application Main class present */
            String mainClassPath = DOWNLOAD_PIPIMPROVEMENT_SRC_MAIN_JAVA + groupIdPath + SLASH + artifactId.toLowerCase();
            String testClassPath = DOWNLOAD_PIPIMPROVEMENT_SRC_TEST_JAVA + groupIdPath + SLASH + artifactId.toLowerCase();


            /* Create subdirectories according to group id and artifact id */
            Files.createDirectories(Path.of(mainClassPath));
            Files.createDirectories(Path.of(testClassPath));



            /* Application Main class path in reference folder */
            String mainFileReference = "reference/pipimprovement/src/main/java/com/hm";
            String testFileReference = "reference/pipimprovement/src/test/java/com/hm";


            /* Copy Application Main class from reference folder to download folder */
            FileUtils.copyDirectory(new File(mainFileReference), new File(mainClassPath));
            FileUtils.copyDirectory(new File(testFileReference), new File(testClassPath));


            /* Path of Application Main class */
            String mainClassFileName = mainClassPath + "/PipImprovementApplication.java";
            String testClassFileName = testClassPath + "/PipImprovementApplicationTests.java";


            /* Replace class Name in Main class */
            replaceText(mainClassFileName, "PipImprovementApplication", StringUtils.capitalize(artifactId) + "Application");
            replaceText(testClassFileName, "PipImprovementApplicationTests", StringUtils.capitalize(artifactId) + "ApplicationTests");


            /* Replace package name with new package name */
            replaceText(mainClassFileName, "com.hm", springTemplateRequest.getGroupId() + "." + artifactId.toLowerCase());
            replaceText(testClassFileName, "com.hm", springTemplateRequest.getGroupId() + "." + artifactId.toLowerCase());


            /* New Main class name */
            String mainClassNewFileName = mainClassPath + SLASH + StringUtils.capitalize(artifactId) + "Application.java";
            String testClassNewFileName = testClassPath + SLASH + StringUtils.capitalize(artifactId) + "ApplicationTests.java";


            /* Renaming the Main class name */
            FileUtils.moveFile(new File(mainClassFileName), new File(mainClassNewFileName));
            FileUtils.moveFile(new File(testClassFileName), new File(testClassNewFileName));


            /* Updating the pom.xml file */
            xmlUtility.updateXMLElementValue("download/pipimprovement/pom.xml", springTemplateRequest);


            /* Project directory name */
            File file = new File("download/pipimprovement");


            /* Project directory new name */
            String newFolderName = "download/" + springTemplateRequest.getArtifactId();

            /* Renaming the project directory name */
            boolean b = file.renameTo(new File(newFolderName));
            log.info(String.valueOf(b));

            /* Zip the project and let the user download the zip */
            zipAndDownloadTemplate(response, springTemplateRequest, newFolderName);


            /* Delete the project and zip file from download folder */
            FileUtils.cleanDirectory(new File("download"));

        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());

        }
    }

    public void zipAndDownloadTemplate(HttpServletResponse response, SpringTemplateRequest springTemplateRequest, String newFolderName) throws Exception {

        /* Zip name */
        String projectZipFolder = "download/" + springTemplateRequest.getArtifactId() + ".zip";


        /* Zip the project directory */
        zipFolder(Path.of(newFolderName), Path.of(projectZipFolder));

        Path path = Paths.get(projectZipFolder);

        String contentType;

        contentType = Files.probeContentType(path);

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        response.setContentType(contentType);
        response.setContentLengthLong(Files.size(path));
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(path.getFileName().toString())
                .build()
                .toString());
        Files.copy(path, response.getOutputStream());
    }


    public static void replaceText(String filePath, String text, String replacement) {

        Path path = Paths.get(filePath);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String> list = stream.map(line -> line.replace(text, replacement)).collect(Collectors.toList());
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Exception occurred : " + e.getMessage());
        }
    }


    private void zipFolder(Path sourceFolderPath, Path zipPath) {

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {

            Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {

                @Override

                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));

                    Files.copy(file, zos);

                    zos.closeEntry();

                    return FileVisitResult.CONTINUE;

                }

            });

        } catch (IOException e) {

            log.error("Exception occurred: " + e.getMessage());

        }

    }
    public List<String> getDependency() {
        return List.of("Web", "GraphQL", "Thymeleaf", "Security", "Jpa", "JDBC", "MySQL", "H2", "Validation", "Lombok");
    }


}
