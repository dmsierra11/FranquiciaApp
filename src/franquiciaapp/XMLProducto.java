/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

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
public class XMLProducto {
    
        private Element root;

    /**
     * escribe en el XML los productos registrados en sistema
     * @param nombre
     * @param descripcion
     * @param costo
     * @return 
     */
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
    
    //Listar productos 
    /**
     * Lista los productos que se encuentran en el .XML
     * @param ventana
     * @param archivo 
     */
    
     public void listarProductos(GestionProducto ventana, String archivo) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("listaProductos.xml");
            Element raiz = doc.getRootElement();
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element descripcion = e.getChild("descripcion");

                Element costo = e.getChild("costo");
               // if (archivo.equals(user.getText())) {
                    ventana.agregarfila(nombre.getText(), descripcion.getText(), costo.getText(), costo.getText());
                //}

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return Varchivo;
    }
     
    /**
     * Modifica el XML
     * @return 
     */
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream("listaProductos.xml");
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
     
    /**
     * Borra el producto del XML
     * @param archivo
     * @param producto
     * @return 
     */
    public boolean borrarProducto(String archivo, String producto) {
        SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
        try {
            doc = builder.build("listaProductos.xml");
        } catch (JDOMException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
            root = doc.getRootElement(); 
       boolean resultado = false;
        Element aux = new Element("producto");
        List Productos = this.root.getChildren("producto");
        while (aux != null) {
            aux = this.buscar(Productos, archivo, producto);
            if (aux != null) {
                Productos.remove(aux);
                resultado = updateDocument();
            }
        }
        return resultado;
    }

       /**
        * Permite la busqueda en el XML
        * @param raiz
        * @param archivo
        * @param producto
        * @return 
        */
   public static Element buscar(List raiz, String archivo, String producto) {
        Iterator i = raiz.iterator(); 
        while (i.hasNext()) {
            Element elemento = (Element) i.next();
            if (producto.equals(elemento.getChild("nombre").getText()) ) {
                return elemento;
            }
        }
        return null;
    }

     
    
}
