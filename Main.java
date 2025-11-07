
public class Main {
    public static void main(String[] args) {

        Inventario miInventario = new Inventario();

        System.out.println("--- Cargando productos iniciales ---");
        miInventario.agregarProducto(new Producto("Teclado Mecánico", 25));
        miInventario.agregarProducto(new Producto("Laptop Gamer", 10));
        miInventario.agregarProducto(new Producto("Mouse Inalámbrico", 50));
        miInventario.agregarProducto(new Producto("Monitor Curvo", 15));
        System.out.println();

        System.out.println("--- Estado Inicial del Inventario (Sin Ordenar) ---");
        miInventario.generarReporteExistencias();

    
        miInventario.generarReporteExistencias();
        
        // Se desordena para probar el siguiente método
        miInventario = new Inventario();
        miInventario.agregarProducto(new Producto("Teclado Mecánico", 25));
        miInventario.agregarProducto(new Producto("Laptop Gamer", 10));
        miInventario.agregarProducto(new Producto("Mouse Inalámbrico", 50));
        miInventario.agregarProducto(new Producto("Monitor Curvo", 15));
        
        // Demostración de Ordenamiento Nativo (Collections.sort)
        miInventario.ordenarPorNombreNativo();
        miInventario.generarReporteExistencias();

        System.out.println("\n--- Realizando operaciones de inventario ---");
        miInventario.registrarEntrada("Laptop Gamer", 5);
        miInventario.registrarSalida("Mouse Inalámbrico", 15);
        miInventario.registrarSalida("Teclado Mecánico", 30); // Generará error de stock
        miInventario.registrarSalida("Webcam HD", 5); // Generará error de producto no encontrado
        System.out.println();

        System.out.println("\n--- Estado Final del Inventario ---");
        miInventario.generarReporteExistencias();

        // Generar reporte final de movimientos
        miInventario.generarReporteMovimientos();
    }
}