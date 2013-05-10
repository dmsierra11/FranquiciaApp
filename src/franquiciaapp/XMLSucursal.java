/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import ventanas.GestionSucursal;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author gracielalucena
 */
public class XMLSucursal {

    public boolean agregarSucursal(String nombre, String ubicacion, String telefono) {
        Document doc;
        Element root, laSucursal, elNombre, laUbicacion, elTelefono;
        SAXBuilder builder = new SAXBuilder();
        try {
            doc = builder.build("listaSucursales.xml");
            root = doc.getRootElement();
            // Creamos una nueva etiqueta
            laSucursal = new Element("sucursal");
            elNombre = new Element("nombre");
            laUbicacion = new Element("ubicacion");
            elTelefono = new Element("telefono");


            root.addContent(laSucursal);
            laSucursal.addContent(elNombre);
            elNombre.addContent(nombre);
            laSucursal.addContent(laUbicacion);
            laUbicacion.addContent(ubicacion);
            laSucursal.addContent(elTelefono);
            elTelefono.addContent(telefono);

            try {
                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("listaSucursales.xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc, file);
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JDOMParseException e) {
            System.out.println("Error loading XML file - The file is empty1");
            e.printStackTrace();
        } catch (JDOMException e) {
            System.out.println("Error loading XML file - The file is empty2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading XML file - The file is empty3");
            e.printStackTrace();
        }

        return true;
    }

    public void listarSucursales(GestionSucursal ventana, String archivo) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("listaSucursales.xml");
            Element raiz = doc.getRootElement();
            List listaSucursal = raiz.getChildren("sucursal");
            Iterator k = listaSucursal.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element ubicacion = e.getChild("ubicacion");
                Element telefono = e.getChild("telefono");
                // if (archivo.equals(user.getText())) {
                ventana.agregarfila(nombre.getText(), ubicacion.getText(), telefono.getText());
                //}

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean registrarCoordinador(String ip, String puerto, String nombre) {
        try {

            Element root = new Element("root");
            Document doc = new Document(root);
            doc.setRootElement(root);

            Element nodo = new Element("nodo");
            nodo.addContent(new Element("ip").setText(ip));
            nodo.addContent(new Element("puerto").setText(puerto));
            nodo.addContent(new Element("nombre").setText(nombre));

            doc.getRootElement().addContent(nodo);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("nodoCoordinador.xml"));

            System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return true;
    }

    public boolean crearXMLInventario(String nombre) {
        Document doc;
        Element root;
        SAXBuilder builder = new SAXBuilder();
        try {

            root = new Element("root");
            doc = new Document(root);
            doc.setRootElement(root);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(nombre + ".xml"));

            System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return true;
    }
}
