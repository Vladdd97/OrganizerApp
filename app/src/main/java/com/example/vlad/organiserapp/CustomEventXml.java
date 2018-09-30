package com.example.vlad.organiserapp;

import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CustomEventXml {



    public static String fileName = "events.xml";

    public static void createAndWriteToXml(CustomEvent customEvent) {

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("OrganaizerAppEvents");
            doc.appendChild(rootElement);

            // event element
            Element event = doc.createElement("event");
            rootElement.appendChild(event);

            // setting eventId attribute to event element
            Attr attr = doc.createAttribute("eventId");
            attr.setValue("1");
            event.setAttributeNode(attr);


            // id element
            Element id = doc.createElement("id");
            id.appendChild( doc.createTextNode( Integer.toString( customEvent.getId() ) ) );
            event.appendChild(id);

            // title element
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(customEvent.getTitle()));
            event.appendChild(title);

            // description element
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(customEvent.getDescription()));
            event.appendChild(description);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void modifyXml(int modId) {


        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            // find all event tags
            NodeList eventsList = doc.getElementsByTagName("event");

            // loop all events
            for (int i = 0; i < eventsList.getLength(); i++) {
                Node event = eventsList.item(i);
                if (event.getNodeType() == Node.ELEMENT_NODE) {

                    Element eventElement = (Element) event;
                    // find eventId
                    int eventId = Integer.parseInt(eventElement.getAttribute("eventId"));

                    // check if it is the wanted event
                    if (modId == eventId) {

                        NodeList eventChilds = event.getChildNodes();
                        // loop all event's tag
                        for (int j = 0; j < eventChilds.getLength(); j++) {
                            Node node = eventChilds.item(j);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) node;
                                if ("id".equals(eElement.getNodeName()))
                                    eElement.setTextContent("11");
                                if ("title".equals(eElement.getNodeName()))
                                    eElement.setTextContent("modified title");
                            }
                        }
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void addEventXml(CustomEvent customEvent){


        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            // root element
            NodeList rootNoode = doc.getElementsByTagName("OrganaizerAppEvents");
            Element rootElement = (Element) rootNoode.item(0);


            // event element
            Element event = doc.createElement("event");
            rootElement.appendChild(event);

            // setting eventId attribute to event element
            Attr attr = doc.createAttribute("eventId");
            attr.setValue("1");
            event.setAttributeNode(attr);


            // id element
            Element id = doc.createElement("id");
            id.appendChild( doc.createTextNode( Integer.toString( customEvent.getId() ) ) );
            event.appendChild(id);

            // title element
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(customEvent.getTitle()));
            event.appendChild(title);

            // description element
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(customEvent.getDescription()));
            event.appendChild(description);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean checkIfExists(String fileName){
        File file = new File(fileName);
        boolean exists = file.exists();
        return exists;
    }

    // get eventId of the last event
    public static int getLastEventId(){
        int lastEventId = 0;


        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            // find all event tags
            NodeList eventsList = doc.getElementsByTagName("event");

            // get the last event
            Node lastEventNode = eventsList.item(eventsList.getLength()-1);
            Element lastEventElement = (Element) lastEventNode;
            // get the eventId of the last event
            lastEventId = Integer.parseInt(lastEventElement.getAttribute("eventId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastEventId;
    }




}