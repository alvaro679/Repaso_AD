package repositorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fichero {
    private String ruta;

    public Fichero(String ruta) {
        this.ruta = ruta;
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

    public boolean crearSiNoExiste() {
        boolean creado = false;
        File archivo;

        try {
            archivo = new File(ruta);

            if (!archivo.exists()) {
                creado = archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear fichero: " + e.getMessage());
        }

        return creado;
    }
}
