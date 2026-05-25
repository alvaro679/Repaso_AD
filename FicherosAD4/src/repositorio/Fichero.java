package repositorio;

// import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fichero {

    private String ruta;

    public Fichero(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Fichero [ruta=" + ruta + "]";
    }


    public void escribirLinea(String dato) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta, true);
            bw = new BufferedWriter(fw);
            bw.write(dato);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero.");
            }
        }
    }


    public List<String> leerFichero() {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> lineas = new ArrayList<>();
        String linea;
        File archivo;

        try {
            archivo = new File(ruta);
            // Sin negar
            if (archivo.exists()) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                linea = br.readLine();

                while (linea != null) {
                    lineas.add(linea);
                    linea = br.readLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero.");
            }
        }

        return lineas;
    }
}
