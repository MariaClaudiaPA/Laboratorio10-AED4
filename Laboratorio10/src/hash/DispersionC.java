package hash;

public class DispersionC {

    public static void main(String[] args) {
        String archivoEmpleados = "D:\\CLAUDIA\\catolica\\5 - Quinto Semestre\\04 - ALGORITMOS Y ESTRUCTURA DE DATOS\\Fase3\\Laboratorio10_1\\src\\hash\\EMPLEADOS.txt";
        String method = "quadratic";
        String dressHash = "hash";
        
        int cantidadElementos = HashC.obtenerCantidadElementos(archivoEmpleados);
     
        HashC<Empleado> hashTable = new HashC<>(cantidadElementos);
        hashTable.leerArchivo(archivoEmpleados, method, dressHash);
        System.out.println("Tabla Hash después de la dispersión:");
        System.out.println(hashTable);
    }
}
