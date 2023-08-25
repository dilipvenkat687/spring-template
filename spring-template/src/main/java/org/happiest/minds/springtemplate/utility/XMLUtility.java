package org.happiest.minds.springtemplate.utility;
import lombok.extern.slf4j.Slf4j;
import org.happiest.minds.springtemplate.request.SpringTemplateRequest;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class XMLUtility {
    public static final String SPRING_BOOT_STARTER_WEB = "spring-boot-starter-web";
    public static final String SPRING_BOOT_STARTER_SECURITY = "spring-boot-starter-security";
    public static final String COM_H_2_DATABASE = "com.h2database";
    private static final String GROUP_ID="groupId";
    private static final String ARTIFACT_ID="artifactId";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PACKAGING = "packaging";
    public static final String DEPENDENCIES = "dependencies";
    public static final String DEPENDENCY = "dependency";
    public static final String ORG_SPRINGFRAMEWORK_BOOT = "org.springframework.boot";
    public static final String SPRING_BOOT_STARTER_GRAPHQL = "spring-boot-starter-graphql";
    public static final String SPRING_BOOT_STARTER_THYMELEAF = "spring-boot-starter-thymeleaf";
    public static final String COM_MYSQL = "com.mysql";
    public static final String MYSQL_CONNECTOR_J = "mysql-connector-j";
    public static final String SPRING_BOOT_STARTER_DATA_JDBC = "spring-boot-starter-data-jdbc";
    public static final String H_2 = "h2";
    public static final String SPRING_BOOT_STARTER_VALIDATION = "spring-boot-starter-validation";
    public static final String ORG_PROJECTLOMBOK = "org.projectlombok";
    public static final String LOMBOK = "lombok";
    public static final String VALIDATION = "Validation";
    public static final String H_21 = "H2";
    public static final String MY_SQL = "MySQL";
    public static final String JDBC = "JDBC";
    public static final String SECURITY = "Security";
    public static final String JPA = "Jpa";
    public static final String THYMELEAF = "Thymeleaf";
    public static final String GRAPH_QL = "GraphQL";
    public static final String WEB = "Web";
    public static final String SPRINGFRAMEWORK_BOOT = "org.springframework.boot";
    public static final String SPRING_BOOT_STARTER_DATA_JPA = "spring-boot-starter-data-jpa";

    public Document updateXMLElementValue(String filePath, SpringTemplateRequest springTemplateRequest) {

        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList groupId = doc.getElementsByTagName(GROUP_ID);
            if (groupId.getLength() > 0) {
                Element groupIdElement = (Element) groupId.item(0);
                groupIdElement.setTextContent(springTemplateRequest.getGroupId());

            }
            NodeList artifactId = doc.getElementsByTagName(ARTIFACT_ID);
            if (artifactId.getLength() > 0) {
                Element artifactIdElement = (Element) artifactId.item(0);
                artifactIdElement.setTextContent(springTemplateRequest.getArtifactId());

            }

            NodeList name = doc.getElementsByTagName(NAME);
            if (name.getLength() > 0) {
                Element nameElement = (Element) name.item(0);
                nameElement.setTextContent(springTemplateRequest.getName());

            }

            NodeList description = doc.getElementsByTagName(DESCRIPTION);
            if (description.getLength() > 0) {
                Element descriptionElement = (Element) description.item(0);
                descriptionElement.setTextContent(springTemplateRequest.getDescription());

            }

            NodeList packaging = doc.getElementsByTagName(PACKAGING);
            if (packaging.getLength() > 0) {
                Element packagingElement = (Element) packaging.item(0);
                packagingElement.setTextContent(springTemplateRequest.getPackagingType());

            }

            Set<String> dependencyInput = new HashSet<>(springTemplateRequest.getDependencies());
            Element groupIdOfDependency;
            Element artifactIdOfDependency;
            Element dependency;
            Element dependenciesElement;

            for (String s : dependencyInput) {
                String groupIdValue;
                String artifactIdValue;
                NodeList dependencies = doc.getElementsByTagName(DEPENDENCIES);
                switch (s) {
                    case WEB:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_WEB;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case GRAPH_QL:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_GRAPHQL;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case THYMELEAF:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_THYMELEAF;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case SECURITY:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_SECURITY;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case JPA:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_DATA_JPA;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case JDBC:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_DATA_JDBC;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case MY_SQL:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = COM_MYSQL;
                        artifactIdValue = MYSQL_CONNECTOR_J;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case H_21:

                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = COM_H_2_DATABASE;
                        artifactIdValue = H_2;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case VALIDATION:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_SPRINGFRAMEWORK_BOOT;
                        artifactIdValue = SPRING_BOOT_STARTER_VALIDATION;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case LOMBOK:
                        dependency = doc.createElement(DEPENDENCY);
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement(GROUP_ID);
                        artifactIdOfDependency = doc.createElement(ARTIFACT_ID);
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = ORG_PROJECTLOMBOK;
                        artifactIdValue = LOMBOK;
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    default:
                        break;
                }
            }
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
            Transformer transformer = null;
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
