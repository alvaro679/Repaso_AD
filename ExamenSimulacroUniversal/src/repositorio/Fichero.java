package repositorio;

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

    public void escribirLinea(String dato) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            crearCarpetaPadre();
            fw = new FileWriter(ruta, true);
            bw = new BufferedWriter(fw);
            bw.write(dato);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar fichero.");
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
            System.err.println("Error al leer: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar fichero.");
            }
        }

        return lineas;
    }

    public boolean crearSiNoExiste() {
        boolean creado = false;
        File archivo;

        try {
            crearCarpetaPadre();
            archivo = new File(ruta);

            if (!archivo.exists()) {
                creado = archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear fichero: " + e.getMessage());
        }

        return creado;
    }

    private void crearCarpetaPadre() {
        File archivo = new File(ruta);
        File padre = archivo.getParentFile();

        if (padre != null && !padre.exists()) {
            padre.mkdirs();
        }
    }
}

