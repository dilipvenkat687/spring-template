package org.happiest.minds.springtemplate.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.happiest.minds.springtemplate.utility.XMLUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class SpringTemplateService {

    @Autowired
    XMLUtility xmlUtility;

    public void downloadTemplate(HttpServletResponse response, SpringTemplateRequest springTemplateRequest) {
        try {

            /* Copy sample template from reference folder to download folder */
            FileUtils.copyDirectory(new File("reference"), new File("download"));


            /* Delete folders in src/main. Deleting in order to make directories according to the group id and artifact id */
            FileUtils.deleteDirectory(new File("download/spring-template/src/main"));


            /* Replace "." with "//" in group id to make directories */
            String groupIdPath = springTemplateRequest.getGroupId().replaceAll("[.]", "//");


            /* Replace non alphabets from artifact id to create directory */
            String artifactId = springTemplateRequest.getArtifactId().replaceAll("[^a-zA-Z]", "");


            /* Path where Application Main class present */
            String mainClassPath = "download/spring-template/src/main/java/" + groupIdPath + "/" + artifactId;


            /* Create subdirectories according to group id and artifact id */
            Files.createDirectories(Path.of(mainClassPath));


            /* Application Main class path in reference folder */
            String mainFileReference = "reference/spring-template/src/main/java/org/happiest/minds/springtemplate";


            /* Copy Application Main class from reference folder to download folder */
            FileUtils.copyDirectory(new File(mainFileReference), new File(mainClassPath));


            /* Path of Application Main class */
            String mainClassFileName = mainClassPath + "/SpringTemplateApplication.java";


            /* Replace class Name in Main class */
            replaceText(mainClassFileName, "SpringTemplateApplication", StringUtils.capitalize(artifactId));


            /* Replace package name with new package name */
            replaceText(mainClassFileName, "org.happiest.minds.springtemplate", springTemplateRequest.getGroupId() + "." + artifactId);


            /* New Main class name */
            String mainClassNewFileName = mainClassPath + "/" + StringUtils.capitalize(artifactId) + ".java";


            /* Renaming the Main class name */
            FileUtils.moveFile(new File(mainClassFileName), new File(mainClassNewFileName));


            /* Updating the pom.xml file */
            xmlUtility.updateXMLElementValue("download/spring-template/pom.xml", springTemplateRequest);


            /* Project directory name */
            File file = new File("download/spring-template");


            /* Project directory new name */
            String newFolderName = "download/" + springTemplateRequest.getArtifactId();

            /* Renaming the project directory name */
            file.renameTo(new File(newFolderName));


            /* Zip the project and let the user download the zip */
            zipAndDownloadTemplate(response, springTemplateRequest, newFolderName);


            /* Delete the project and zip file from download folder */
            FileUtils.cleanDirectory(new File("download"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void zipAndDownloadTemplate(HttpServletResponse response, SpringTemplateRequest springTemplateRequest, String newFolderName) throws Exception {

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


    private static void replaceText(String filePath, String text, String replacement) {

        Path path = Paths.get(filePath);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String> list = stream.map(line -> line.replace(text, replacement)).collect(Collectors.toList());
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zipFolder(Path sourceFolderPath, Path zipPath) throws Exception {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
        Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
        zos.close();
    }

}
