package hash;

public class DispersionC {

    public static void main(String[] args) {
        HashC<String> hashTable = new HashC<>(100); 

        String archivoEmpleados = "D:\\CLAUDIA\\catolica\\5 - Quinto Semestre\\04 - ALGORITMOS Y ESTRUCTURA DE DATOS\\Fase3\\Laboratorio10\\src\\hash\\EMPLEADOS.txt";

        String method = "linear";
        String dressHash = "hash";

        hashTable.leerArchivo(archivoEmpleados, method, dressHash);

        System.out.println("Tabla Hash después de la dispersión:");
        System.out.println(hashTable);
    }
    
}
