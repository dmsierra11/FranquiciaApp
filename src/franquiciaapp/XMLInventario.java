/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import ventanas.GestionInventario;
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
    private String nombreSucrusal;
    
    public XMLInventario (String sucursal){
        this.nombreSucrusal = sucursal;  
        
  
    }
    
  
    
    
    
    public void CrearInventario(String sucursal) {
        int cont = 0;
        SAXBuilder builder = new SAXBuilder();
        //XMLInventario inventario = new XMLInventario();
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
                        crearProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), sucursal);
                        cont = 1;
                    } 
                      else  
                          agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), sucursal);               
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
    public boolean crearProductoInventario(String nombre, String descripcion, String costo, String sucursal) {
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
                FileOutputStream file = new FileOutputStream(sucursal+".xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc, file);
                System.out.println("Guardado crear");
                System.out.println(sucursal);
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return true;
    }
    
    public boolean actualizarInventario(String nombreOrig, String nombreI, String cantidadI) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build(nombreOrig+".xml");
            Element raiz = doc.getRootElement();
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                if (nombre.getText().equalsIgnoreCase(nombreI)) {
                   
                    Element cantidad = e.getChild("cantidad");
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
                xmlOutputter.output(doc, new FileOutputStream(nombreOrig+".xml"));

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
    public void listarInventario(GestionInventario ventana, String sucursal) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build(sucursal+".xml");
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
    public boolean buscar(String producto, String sucursal) {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = null;
       try {
            doc = builder.build(sucursal+".xml");
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
              
           // actualizarInventario(sucursal, sucursal, producto, producto, sucursal);
            return true;
        }   
        System.out.println("NO LO ENCONTRE");
        return false;
    }
    
    /**
     * Lee el XML de inventario
     *
     * @return
     */
    public void LeerInventario(String sucursal) {
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
                boolean aux = this.buscar(node.getChildText("nombre"), sucursal);
                //System.out.println(aux);
                if (aux == false)
                {
                    //System.out.println("dentro del if" + node.getChildText("nombre"));
                    this.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), sucursal);
                }
        }
}
 
    
    public boolean agregarProductoInventario(String nombre, String descripcion, String costo, String sucursal) {
        Document doc;
        Element root, elProducto, elNombre, laDescripcion, elPrecio, laCantidad, elStatus;
        SAXBuilder builder = new SAXBuilder();
        try {
            doc = builder.build(sucursal+".xml");
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
                FileOutputStream file = new FileOutputStream(sucursal+".xml");
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
