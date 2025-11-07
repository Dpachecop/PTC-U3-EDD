// import java.util.ArrayList; // (Cambiamos ArrayList por LinkedList)
import java.util.Collections;
import java.util.List;
import java.util.LinkedList; // <--- Se importa la lista nativa
import java.util.Optional;



public class Inventario {


    // private DoublyLinkedList<Producto> productos; // (Manual)
    private List<Producto> productos; // (Nativa)

    public Inventario() {

        // this.productos = new DoublyLinkedList<>(); // (Manual)
        this.productos = new LinkedList<>(); // (Nativa)
    }

    // --- MÉTODOS DE GESTIÓN DE PRODUCTOS (MODIFICADO) ---
    public void agregarProducto(Producto producto) {
        // Usamos la búsqueda binaria (que ahora es posible)
        // Nota: La lista debe estar ordenada para que esto funcione.
        

        // Volvemos a usar la búsqueda binaria.
        // Nota: binarySearch en una LinkedList es LENTO (O(n)), 
        // pero funcional y cumple con la PARTE B.
        // En un escenario real, si la búsqueda binaria es prioritaria,
        // se usaría un ArrayList.
        
        if (buscarProducto(producto.getNombre()).isEmpty()) {
            

            // this.productos.addLast(producto); // (Manual)
            this.productos.add(producto); // (Nativa)
            
          
            // Mantenemos la lista ordenada para la búsqueda binaria.
            Collections.sort(this.productos); 
            
            System.out.println("Producto '" + producto.getNombre() + "' agregado al inventario.");
        } else {
            System.out.println("Error: El producto '" + producto.getNombre() + "' ya existe.");
        }
    }



    // 1. Búsqueda con Búsqueda Binaria (REACTIVADA)
    public Optional<Producto> buscarProducto(String nombre) {
        // Creamos un producto temporal solo con el nombre para usar en la búsqueda.
        int index = Collections.binarySearch(productos, new Producto(nombre, 0));

        if (index < 0) {
            return Optional.empty(); // No se encontró el producto.
        } else {
          
            // return Optional.of(productos.getNode(index).value); // (Manual)
            return Optional.of(productos.get(index)); // (Nativa)
        }
    }





    // 3. Ordenamiento con Herramienta Nativa de Java (REACTIVADO)
    public void ordenarPorNombreNativo() {
        Collections.sort(productos); // ¡Esto vuelve a funcionar!
        System.out.println("\nInventario ordenado con herramienta nativa (Collections.sort).");
    }


    // --- MÉTODOS PARA REGISTRAR MOVIMIENTOS ---

    public void registrarEntrada(String nombreProducto, int cantidad) {
        Optional<Producto> productoOpt = buscarProducto(nombreProducto);
        if (productoOpt.isPresent()) {
            productoOpt.get().registrarEntrada(cantidad);
            System.out.println("Entrada registrada para '" + nombreProducto + "'.");
        } else {
            System.out.println("Error: No se encontró el producto '" + nombreProducto + "'.");
        }
    }

    public void registrarSalida(String nombreProducto, int cantidad) {
        Optional<Producto> productoOpt = buscarProducto(nombreProducto);
        if (productoOpt.isPresent()) {
            if (!productoOpt.get().registrarSalida(cantidad)) {
                System.out.println("Error: Stock insuficiente para '" + nombreProducto + "'.");
            } else {
                System.out.println("Salida registrada para '" + nombreProducto + "'.");
            }
        } else {
            System.out.println("Error: No se encontró el producto '" + nombreProducto + "'.");
        }
    }

    // --- MÉTODOS DE REPORTE ---
    // (Sin cambios, funcionan perfecto)
    public void generarReporteExistencias() {
        System.out.println("\n--- REPORTE DE EXISTENCIAS ---");
        if (productos.isEmpty()) { 
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("------------------------------------");
        System.out.printf("%-20s | %s\n", "Producto", "Stock Actual");
        System.out.println("------------------------------------");
        for (Producto p : productos) { 
            System.out.println(p); 
        }
        System.out.println("------------------------------------\n");
    }

    public void generarReporteMovimientos() {
        System.out.println("\n--- REPORTE DE MOVIMIENTOS ---");
        if (productos.isEmpty()) { 
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : productos) { 
            System.out.println("\n--- Historial de: " + p.getNombre() + " ---");
            
            if (p.getHistorialMovimientos().isEmpty()) { 
                System.out.println("Sin movimientos registrados.");
            } else {
                for (String registro : p.getHistorialMovimientos()) { 
                    System.out.println("  - " + registro);
                }
            }
        }
        System.out.println("\n--- FIN DEL REPORTE ---\n");
    }
}