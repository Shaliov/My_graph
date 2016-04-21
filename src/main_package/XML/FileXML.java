package main_package.XML;

import main_package.controller.GraphService;
import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;

import java.io.FileInputStream;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by Andrey on 4/21/2016.
 */
public class FileXML {
    private Graph graph;
    private String message;
    private GraphService graphService;

    public void FileXML() {
        graph = null;
        message = null;
    }
    public Graph getGraph() {
        return graph;
    }
    public boolean openFile() {
        boolean success = false;
        try{
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                if (getExtension(fc.getSelectedFile().getName()).equals("gwf")) {
                    success = openXML(fc.getSelectedFile().getPath());
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Can't open file", "ERROR", JOptionPane.ERROR_MESSAGE);
            message = null;
            success = false;
        }
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean openXML(String fileName) {
        try {
            message = fileName;
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));
            XMLStreamReader reader = xmlStreamReader;
            String name = null;
            double x = 0;
            double y = 0;
            int weight = 0;
            Arc arc = new Arc();
            while (reader.hasNext()) {
                reader.next();
                if(reader.isStartElement()) {
                    graph = new Graph();
                } else if (reader.getLocalName().equals(AttributesXML.node.toString())) {
                    name = reader.getAttributeValue(null, AttributesXML.name.toString());
                    x = Double.valueOf(reader.getAttributeValue(null, AttributesXML.x.toString()));
                    y = Double.valueOf(reader.getAttributeValue(null, AttributesXML.y.toString()));
                } else if (reader.getLocalName().equals(AttributesXML.arc.toString())) {
                    weight = Integer.valueOf(reader.getAttributeValue(null, AttributesXML.weight.toString()));
                } else if (reader.getLocalName().equals(AttributesXML.startNode.toString())) {
                    x = Double.valueOf(reader.getAttributeValue(null, AttributesXML.x.toString()));
                    y = Double.valueOf(reader.getAttributeValue(null, AttributesXML.y.toString()));
                } else if (reader.getLocalName().equals(AttributesXML.endNode.toString())) {
                    x = Double.valueOf(reader.getAttributeValue(null, AttributesXML.x.toString()));
                    y = Double.valueOf(reader.getAttributeValue(null, AttributesXML.y.toString()));
                } else if (reader.isEndElement()) {
                    if (reader.getLocalName().equals(AttributesXML.node.toString())) {
                        Node newNode = new Node();
                        newNode.setNodeName(name);
                        newNode.setNodeX(x);
                        newNode.setNodeY(y);
                        graph.add(newNode);

                    } else if (reader.getLocalName().equals(AttributesXML.arc.toString())) {
                        Arc newArc = new Arc();
                        newArc.setName(name);
                        newArc.setArcStartNode(arc.getArcStartNode());
                        newArc.setArcEndNode(arc.getArcEndNode());
                        newArc.setWeight(weight);
                        graph.add(arc);
                    } else if (reader.getLocalName().equals(AttributesXML.startNode.toString())) {
                        Node node = new Node();
                        node.setNodeX(x);
                        node.setNodeY(y);
                        arc.setArcStartNode(node);
                    } else if (reader.getLocalName().equals(AttributesXML.endNode.toString())) {
                        Node node = new Node();
                        node.setNodeX(x);
                        node.setNodeY(y);
                        arc.setArcEndNode(node);
                    }

                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Can't open file", "ERROR", JOptionPane.ERROR_MESSAGE);
            message = null;
            return false;
        }
        return true;
    }
    private void writeData(XMLStreamWriter w) {
        try {
            w.writeStartDocument("UTF-8", "1.0");
            w.writeStartElement(AttributesXML.graph.toString());
            for(Node node : graph.getNodeList()) {
                w.writeStartElement(AttributesXML.node.toString());
                w.writeAttribute(AttributesXML.name.toString(), node.getNodeName());
                w.writeAttribute(AttributesXML.x.toString(), Integer.toString((int) node.getNodeX()));
                w.writeAttribute(AttributesXML.y.toString(), Integer.toString((int) node.getNodeY()));
                w.writeEndElement();
            }
            for(Arc arc : graph.getArcList()){
                w.writeStartElement(AttributesXML.arc.toString());
                w.writeAttribute(AttributesXML.weight.toString(), Integer.toString(arc.getWeight()));
                w.writeStartElement(AttributesXML.startNode.toString());
                Node arcStartNode = arc.getArcStartNode();
                w.writeAttribute(AttributesXML.name.toString(), arcStartNode.getNodeName());
                w.writeAttribute(AttributesXML.x.toString(), Integer.toString((int) arcStartNode.getNodeX()));
                w.writeAttribute(AttributesXML.y.toString(), Integer.toString((int) arcStartNode.getNodeY()));
                w.writeEndElement();
                w.writeStartElement(AttributesXML.endNode.toString());
                Node arcEndNode = arc.getArcEndNode();
                w.writeAttribute(AttributesXML.name.toString(), arcEndNode.getNodeName());
                w.writeAttribute(AttributesXML.x.toString(), Integer.toString((int) arcEndNode.getNodeX()));
                w.writeAttribute(AttributesXML.y.toString(), Integer.toString((int) arcEndNode.getNodeY()));
                w.writeEndElement();
                w.writeEndElement();
            }
            w.writeEndElement();
            w.writeEndDocument();
            w.flush();

        } catch (Exception save) {
            JOptionPane.showMessageDialog
                    (null, "Can't save ", "ERROR", JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
            message = null;
        }

    }
    public void saveFile() {
        try {
            graph = Graph.getInstance();
            XMLStreamWriter streamWriter;
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            streamWriter = outputFactory.createXMLStreamWriter(new FileWriter(message));
            writeData(streamWriter);
        } catch (Exception save) {
            JOptionPane.showMessageDialog(null, "Can`t save","ERROR",JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
            message = null;
        }
    }
    public void saveAsFile() {
        try {
            graph = Graph.getInstance();
            JFileChooser fc = new JFileChooser();
            XMLStreamWriter writer;
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                message = fc.getSelectedFile() + ".gwf";
                writer = output.createXMLStreamWriter(new FileWriter(message));
                writeData(writer);
            }
        } catch (Exception eSave) {
            JOptionPane.showMessageDialog
                    (null, "Can't save file", "ERROR", JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
            message = null;
        }

    }

    private static String getExtension(String fileName) {
        String extension = null;
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1) {
            extension = fileName.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}

