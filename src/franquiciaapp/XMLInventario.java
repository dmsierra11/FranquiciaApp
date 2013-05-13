/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import Sockets.Replicador;
import ventanas.GestionInventario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
                        crearProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), node.getChildText("imagen"), sucursal);
                        cont = 1;
                    } 
                      else  
                          agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), node.getChildText("imagen"), sucursal);               
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
    public boolean crearProductoInventario(String nombre, String descripcion, String costo, String imagenI, String sucursal) {
        Element elProducto, elNombre, laDescripcion, elPrecio, laCantidad, elStatus, laImagen;
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
            elStatus = new Element("status");
            laImagen = new Element("imagen");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("0");
             elProducto.addContent(elStatus);
            elStatus.addContent("Activo");
             elProducto.addContent(laImagen);
            laImagen.addContent(imagenI);
           
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
    
    public boolean borrarProducto(String archivo, String producto) {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = null;
        try {
            doc = builder.build(archivo+".xml");
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
            aux = this.buscarEliminar(Productos, archivo, producto);
            if (aux != null) {
                Productos.remove(aux);
                resultado = updateDocument(archivo);
            }
        }
        return resultado;
    }
    
      private boolean updateDocument(String archivo) {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(archivo+".xml");
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
    
     public static Element buscarEliminar(List raiz, String archivo, String producto) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element elemento = (Element) i.next();
            if (producto.equals(elemento.getChild("nombre").getText())) {
                return elemento;
            }
        }
        return null;
    }

    
    public boolean actualizarInventario(String nombreOrig, String nombreI, String cantidadI, String stadoI, String descI, String imagI, String costI) {
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
                    Element status = e.getChild("status");
                    status.setText(stadoI);
                    
                    if (stadoI.equals("Activo")){
                        System.out.println("CAMBIE A ACTIVO DEBO AGREGAR PRODUTO");
                         XMLProducto xml = new XMLProducto();
                         xml.agregarProducto(nombreI, descI, costI, imagI);
                    }

                    new Thread(new Replicador(nombreOrig+".xml")).start();

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
    
    public boolean actualizarStatus(String nombreOrig, String nombreI, String status) {
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
                   
                    Element cantidad = e.getChild("status");
                    cantidad.setText(status);
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
                Element status = e.getChild("status");
                 Element imagen = e.getChild("imagen");
                // if (archivo.equals(user.getText())) {
                ventana.agregarfila(nombre.getText(), descripcion.getText(), costo.getText(), cantidad.getText(), status.getText(), imagen.getText());
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
    
    
    public void verificarInactivos(String sucursal, String nombreP){
                SAXBuilder builder = new SAXBuilder(false);

                Document doc2 = null;
        try {
            doc2 = builder.build(sucursal+".xml");
        } catch (JDOMException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        root2 = doc2.getRootElement();
        Element raiz2 = doc2.getRootElement();
        List listaInventario2 = raiz2.getChildren("producto");
        for (int i = 0; i < listaInventario2.size(); i++) 
        {
                Element node2 = (Element) listaInventario2.get(i);
                //System.out.println("producto nuevo " + node.getChildText("nombre"));
                boolean aux2 = this.buscar(nombreP, "listaProductos");
                String aux3 = node2.getChildText("cantidad");
                //System.out.println(aux);
                if (aux2 == false)
                {
                    //System.out.println("dentro del if" + node.getChildText("nombre"));
                    System.out.println("NO ENCONTRE EN LISTA PRODUCTO"+ nombreP);
                    System.out.println(aux3+ " esto es la cantidad");
                        if (!aux3.equalsIgnoreCase("0")){
                            
                            System.out.println("cantidad no cero");
                    //this.actualizarStatus(sucursal, node2.getChildText("nombre"));
                    JOptionPane.showMessageDialog(null, "Se ha inactivado "+ nombreP, "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                        } 
                       else
                        {
                                                        System.out.println("cantidad 0");

                        this.borrarProducto(sucursal, node2.getChildText("nombre"));
                        }
                    //this.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), sucursal);
                }
}
        
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
                    this.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"),node.getChildText("imagen"), sucursal);
                }
                else{
                    
                    this.buscarActualizaciones(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), node.getChildText("imagen"), node.getChildText("cantidad") , sucursal);
                    //System.out.println(node.getChildText("descripcion"));
                }
         }
        Document doc2 = null;
        try {
            doc2 = builder.build(sucursal+".xml");
        } catch (JDOMException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        root2 = doc2.getRootElement();
        Element raiz2 = doc2.getRootElement();
        List listaInventario2 = raiz2.getChildren("producto");
        for (int i = 0; i < listaInventario2.size(); i++) 
        {
                Element node2 = (Element) listaInventario2.get(i);
                //System.out.println("producto nuevo " + node.getChildText("nombre"));
                boolean aux2 = this.buscar(node2.getChildText("nombre"), "listaProductos");
                String aux3 = node2.getChildText("cantidad");
                //System.out.println(aux);
                if (aux2 == false)
                {
                    //System.out.println("dentro del if" + node.getChildText("nombre"));
                    System.out.println("NO ENCONTRE EN LISTA PRODUCTO"+ node2.getChildText("nombre"));
                    System.out.println(aux3+ " esto es la cantidad");
                        if (!aux3.equalsIgnoreCase("0")){
                            
                            System.out.println("cantidad no cero");
                    this.actualizarStatus(sucursal, node2.getChildText("nombre"), "Inactivo");
                        } 
                       else
                        {
                                                        System.out.println("cantidad 0");

                        this.borrarProducto(sucursal, node2.getChildText("nombre"));
                        }
                    //this.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"), sucursal);
                }
}
    }
 
      public void buscarActualizaciones(String name, String desc, String cost, String imag, String cant, String sucursal) {
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
            if (desc.equals(node.getChildText("descripcion")) == false)   {
                
                        try {
          
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                if (nombre.getText().equalsIgnoreCase(name)) {
                   
                    Element descripcion = e.getChild("descripcion");
                    descripcion.setText(desc);
                }

                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(doc, new FileOutputStream(sucursal+".xml"));

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        }catch (Exception e) {
            e.printStackTrace();
        }
                
                
                
            }  
            
            if (cost.equals(node.getChildText("costo")) == false)   {
                
                        try {
          
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                if (nombre.getText().equalsIgnoreCase(name)) {
                   
                    Element costo = e.getChild("costo");
                    costo.setText(cost);
                }

                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(doc, new FileOutputStream(sucursal+".xml"));

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        }catch (Exception e) {
            e.printStackTrace();
        }
                        
                        
        if (imag.equals(node.getChildText("imagen")) == false)   {
                
                        try {
          
            List listaProducto = raiz.getChildren("producto");
            Iterator k = listaProducto.iterator();
            while (k.hasNext()) {
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                if (nombre.getText().equalsIgnoreCase(name)) {
                   
                    Element imagen = e.getChild("imagen");
                    imagen.setText(imag);
                }

                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(doc, new FileOutputStream(sucursal+".xml"));

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        }catch (Exception e) {
            e.printStackTrace();
        }
                        
        
                
                
                
            }  
                
                
                
            }  
              
           // actualizarInventario(sucursal, sucursal, producto, producto, sucursal);
        }   
    }
    
    public boolean agregarProductoInventario(String nombre, String descripcion, String costo, String imagenI, String sucursal) {
        Document doc;
        Element root, elProducto, elNombre, laDescripcion, elPrecio, laCantidad, elStatus, laImagen;
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
            laImagen = new Element("imagen");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("0");
            elProducto.addContent(elStatus);
            elStatus.addContent("Activo");
            elProducto.addContent(laImagen);
            laImagen.addContent(imagenI);
         

            
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
