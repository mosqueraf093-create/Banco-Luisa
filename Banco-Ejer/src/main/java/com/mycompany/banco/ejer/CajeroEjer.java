
package com.mycompany.banco.ejer;
import javax.swing.JOptionPane; import java.util.Random;

public class CajeroEjer {
  



private int saldo = 7_000_000;
private int saldoCN = 20_000_000;
private boolean continuar = true;

public  void CajeroNequi() {
}

public int getSaldo() {
    return saldo;
}

public void setSaldo(int saldo) {
    this.saldo = saldo;
}

public int getSaldoCN() {
    return saldoCN;
}

public void setSaldoCN(int saldoCN) {
    this.saldoCN = saldoCN;
}

public String generarIdOperacion() {
    Random id = new Random();
    int numero = id.nextInt(9000) + 1000;
    return "ID de la operación # " + numero + "\n";
}

public void mostrarMenu() {
    while (continuar) {
        try {
            StringBuilder menu = new StringBuilder("MENU CAJERO AUTOMATICO \n\n");
            menu.append("Seleccione una opción:\n")
                .append("1. Consultar saldo\n")
                .append("2. Consignar dinero\n")
                .append("3. Retirar dinero\n")
                .append("4. Salir\n");

            String opcion = JOptionPane.showInputDialog(null, menu, "Cajero Automático", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) {
                if (confirmarSalida()) {
                    continuar = false;
                }
                continue;
            }

            int opc = Integer.parseInt(opcion);

            switch (opc) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    consignarDinero();
                    break;
                case 3:
                    retirarDinero();
                    break;
                case 4:
                    if (confirmarSalida()) {
                        continuar = false;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un número del 1 al 4", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public boolean confirmarSalida() {
    int confirmar = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
    return confirmar == JOptionPane.YES_OPTION;
}

public void consultarSaldo() {
    String validacion = generarIdOperacion();
    String mensaje = "CONSULTAR SALDO\n" + validacion + "Saldo actual: $" + String.format("%,d", saldo);
    JOptionPane.showMessageDialog(null, mensaje, "Consultar saldo", JOptionPane.INFORMATION_MESSAGE);
}

public void consignarDinero() {
    try {
        String entrada = JOptionPane.showInputDialog(null, "Ingrese el valor a consignar (mínimo $10,000):", "Consignar dinero", JOptionPane.QUESTION_MESSAGE);
        if (entrada != null) {
            int valor = Integer.parseInt(entrada);
            if (valor >= 10000) {
                saldo += valor;
                String mensaje = "CONSIGNAR DINERO\n" + generarIdOperacion() + "Nuevo saldo: $" + String.format("%,d", saldo);
                JOptionPane.showMessageDialog(null, mensaje, "Consignar dinero", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Solo se aceptan billetes de $10,000 en adelante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void retirarDinero() {
    try {
        String entrada = JOptionPane.showInputDialog(null, "Ingrese el valor a retirar:", "Retirar dinero", JOptionPane.QUESTION_MESSAGE);
        if (entrada != null) {
            int valor = Integer.parseInt(entrada);
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                String mensaje = "RETIRAR DINERO\n" + generarIdOperacion() + "Nuevo saldo: $" + String.format("%,d", saldo);
                JOptionPane.showMessageDialog(null, mensaje, "Retirar dinero", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente o valor inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

