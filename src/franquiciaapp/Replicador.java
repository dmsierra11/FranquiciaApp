package franquiciaapp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class Replicador {
    
    private Socket cliente;
    private int puerto;
    private String ip;
    
    public Replicador(){
        XMLNodoCoordinador nodoCoord = new XMLNodoCoordinador();
        Nodo coordinador = nodoCoord.getCoordinador();
        this.puerto = Integer.parseInt(coordinador.getPuerto());
        this.ip = coordinador.getIp();
    }

    public void enviarXML(String nombreArchivo) {
        try {
            this.cliente = new Socket(this.ip, this.puerto);

            // enviar archivo  
            File myFile = new File(nombreArchivo);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = this.cliente.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            
            this.cliente.close();

        } catch (Exception e) {
            System.out.println("Problemas al enviar el archivo");
            e.printStackTrace();
        } 
    }   
}
