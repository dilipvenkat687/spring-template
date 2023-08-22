package org.happiest.minds.springtemplate.utility;


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
public class XMLUtility {

    public void updateXMLElementValue(String filePath, SpringTemplateRequest springTemplateRequest) {

        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList groupId = doc.getElementsByTagName("groupId");
            if (groupId.getLength() > 0) {
                Element groupIdElement = (Element) groupId.item(0);
                groupIdElement.setTextContent(springTemplateRequest.getGroupId());

            }
            NodeList artifactId = doc.getElementsByTagName("artifactId");
            if (artifactId.getLength() > 0) {
                Element artifactIdElement = (Element) artifactId.item(0);
                artifactIdElement.setTextContent(springTemplateRequest.getArtifactId());

            }

            NodeList name = doc.getElementsByTagName("name");
            if (name.getLength() > 0) {
                Element nameElement = (Element) name.item(0);
                nameElement.setTextContent(springTemplateRequest.getName());

            }

            NodeList description = doc.getElementsByTagName("description");
            if (description.getLength() > 0) {
                Element descriptionElement = (Element) description.item(0);
                descriptionElement.setTextContent(springTemplateRequest.getDescription());

            }

            NodeList packaging = doc.getElementsByTagName("packaging");
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
                NodeList dependencies = doc.getElementsByTagName("dependencies");
                switch (s) {
                    case "Web":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-web";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "GraphQL":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-graphql";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Thymeleaf":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-thymeleaf";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Security":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-security";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Jpa":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-data-jpa";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "JDBC":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-data-jdbc";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "MySQL":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "com.mysql";
                        artifactIdValue = "mysql-connector-j";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "H2":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "com.h2database";
                        artifactIdValue = "h2";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Validation":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.springframework.boot";
                        artifactIdValue = "spring-boot-starter-validation";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Lombok":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = "org.projectlombok";
                        artifactIdValue = "lombok";
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    default:
                        break;
                }
            }
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
