package controlador;

import modelo.ListaNumeros;
import modelo.Numero;
import servicio.ServicioNumeros;
import vista.Escaner;

public class ControladorNumeros {

    public static void iniciar() {
        ListaNumeros introducidos = new ListaNumeros();
        ListaNumeros validos = new ListaNumeros();
        String texto = Escaner.pedirString("Introduce numero o Fin:");
        int valor;
        Numero numero;

        while (!texto.equalsIgnoreCase("Fin")) {
            try {
                valor = Integer.parseInt(texto);
                numero = new Numero(valor);
                introducidos.addNumero(numero);

                if (ServicioNumeros.estaEnRango(numero)) {
                    validos.addNumero(numero);
                }
            } catch (NumberFormatException e) {
                System.err.println("Valor no numerico: " + texto);
            }

            texto = Escaner.pedirString("Introduce numero o Fin:");
        }

        ServicioNumeros.guardarResultado(validos, introducidos);
        System.out.println("Resultado guardado en datos/numeros.txt");
    }
}

