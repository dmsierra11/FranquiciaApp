/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Diego Alienware
 */
public class XMLInventario {
    
    private Element root, root2;
    private String xmlInventario = "inventarioProductos.xml";
    
    public void CrearInventario() {
        int cont = 0;
        SAXBuilder builder = new SAXBuilder();
        XMLInventario inventario = new XMLInventario();
        File xmlFile = new File("listaProductos.xml");

        try {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("producto");

            for (int i = 0; i < list.size(); i++) 
            {
               Element node = (Element) list.get(i);
                      if (cont == 0) 
                    {
                        inventario.crearProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"));
                        cont = 1;
                    } 
                      else  
                          inventario.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"));               
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }
    
    
    /**
     * Crea el XML de inventario con el primer producto
     *
     * @param nombre
     * @param descripcion
     * @param costo
     * @return
     */
    public boolean crearProductoInventario(String nombre, String descripcion, String costo) {
        Element elProducto, elNombre, laDescripcion, elPrecio, laCantidad, elStatus;
        SAXBuilder builder = new SAXBuilder();
        try{ 
            Element root = new Element("root");
            Document doc = new Document(root);
            doc.setRootElement(root);
            
            Element producto = new Element("producto");
            // Creamos una nueva etiqueta
            elProducto = new Element("producto");
            elNombre = new Element("nombre");
            laDescripcion = new Element("descripcion");
            elPrecio = new Element("costo");
            laCantidad = new Element("cantidad");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("0");
           
                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("inventarioProductos.xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc, file);
                System.out.println("Guardado crear");
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return true;
    }
    
    public boolean actualizarInventario(String nombreOrig, String nombreI, String descripcionI, String costoI, String cantidadI) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("inventarioProductos.xml");
            Element raiz = doc.getRootElement();
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                if (nombre.getText().equalsIgnoreCase(nombreOrig)) {
                    Element descripcion = e.getChild("descripcion");
                    Element costo = e.getChild("costo");
                    Element cantidad = e.getChild("cantidad");
                    System.out.println("inicio");
                    System.out.println(nombreI);
                    System.out.println(descripcionI);
                    System.out.println(costoI);
                    System.out.println("fin");
                    nombre.setText(nombreI);
                    descripcion.setText(descripcionI);
                    costo.setText(costoI);
                    cantidad.setText(cantidadI);

                    //if (e.getChild("status") != null) {
                    //    e.getChild("status").setText(status);
                    //} else {
                      //  Element estatus = new Element("status");
                        //estatus.addContent(status);
                        //e.addContent(estatus);
                    //}

                    //System.out.println(descripcion.getText());

                }

                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(doc, new FileOutputStream("inventarioProductos.xml"));

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
    
    
    /**
     * lista los productos del inventario en la lista
     *
     * @param archivo
     * @param ventana 
     * @return
     */
    void listarInventario(GestionInventario ventana, String archivo) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("inventarioProductos.xml");
            Element raiz = doc.getRootElement();
            List listaSucursal = raiz.getChildren("producto");
            Iterator k = listaSucursal.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element costo = e.getChild("costo");
                Element cantidad = e.getChild("cantidad");
                Element descripcion = e.getChild("descripcion");
                // if (archivo.equals(user.getText())) {
                ventana.agregarfila(nombre.getText(), descripcion.getText(), costo.getText(), cantidad.getText());
                //}

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Permite la busqueda en el XML de inventario
     *
     * @param producto
     * @return
     */
    public boolean buscar(String producto) {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = null;
       try {
            doc = builder.build("inventarioProductos.xml");
        } catch (JDOMException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = doc.getRootElement();
        Element raiz = doc.getRootElement();
        List listaInventario = raiz.getChildren("producto");
        for (int i = 0; i < listaInventario.size(); i++) 
        {
            Element node = (Element) listaInventario.get(i);
            //System.out.println("imprimo en buscar: " + producto + " es " + producto.equals(node.getChildText("nombre")));
            if (producto.equals(node.getChildText("nombre")) == true)     
            return true;
        }   
        return false;
    }
    
    /**
     * Lee el XML de inventario
     *
     * @return
     */
    public void LeerInventario() {
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
        Element raiz = doc.getRootElement();
        List listaInventario = raiz.getChildren("producto");
        for (int i = 0; i < listaInventario.size(); i++) 
        {
                Element node = (Element) listaInventario.get(i);
                //System.out.println("producto nuevo " + node.getChildText("nombre"));
                boolean aux = this.buscar(node.getChildText("nombre"));
                //System.out.println(aux);
                if (aux == false)
                {
                    //System.out.println("dentro del if" + node.getChildText("nombre"));
                    this.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"));
                }
        }
}
 
    
    public boolean agregarProductoInventario(String nombre, String descripcion, String costo) {
        Document doc;
        Element root, elProducto, elNombre, laDescripcion, elPrecio, laCantidad, elStatus;
        SAXBuilder builder = new SAXBuilder();
        try {
            doc = builder.build("inventarioProductos.xml");
            root = doc.getRootElement();
            // Creamos una nueva etiqueta
            elProducto = new Element("producto");
            elNombre = new Element("nombre");
            laDescripcion = new Element("descripcion");
            elPrecio = new Element("costo");
            laCantidad = new Element("cantidad");
            elStatus = new Element("status");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("0");

            
            try {
                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("inventarioProductos.xml");
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
    
}