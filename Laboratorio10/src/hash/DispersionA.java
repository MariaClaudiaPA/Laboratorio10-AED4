package hash;

public class DispersionA {

    public static void main(String[] args) {
        String archivo = "D:\\CLAUDIA\\catolica\\5 - Quinto Semestre\\04 - ALGORITMOS Y ESTRUCTURA DE DATOS\\Fase3\\Laboratorio10\\src\\hash\\EMPLEADOS.txt";
        HashA<Empleado> hashTable = new HashA<>();
        hashTable.leerArchivo(archivo);
        System.out.println(hashTable);
    }
}