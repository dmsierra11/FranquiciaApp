/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author gracielalucena
 */
public class XMLProducto {
    
    
    public static boolean agregarProducto(String nombre,String descripcion, String costo)
    {
        Document    doc;
        Element     root, elProducto, elNombre, laDescripcion, elPrecio;
        SAXBuilder  builder = new SAXBuilder();
        try
        {
            doc = builder.build("listaProductos.xml");
            root = doc.getRootElement();
            // Creamos una nueva etiqueta
            elProducto = new Element("producto");
            elNombre = new Element("nombre");
            laDescripcion = new Element("descripcion");
            elPrecio = new Element("costo");
            
            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
           
            try
            {
                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("listaProductos.xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc,file);
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println("Error loading XML file - The file is empty1");
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println("Error loading XML file - The file is empty2");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Error loading XML file - The file is empty3");
            e.printStackTrace();
        }

        return true;
    }
    
    
}
