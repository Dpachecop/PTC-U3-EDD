
import java.util.Optional;


public class Inventario {


    // private List<Producto> productos;
    private DoublyLinkedList<Producto> productos;

    public Inventario() {
     
        // this.productos = new ArrayList<>();
        this.productos = new DoublyLinkedList<>();
    }

    // --- MÉTODOS DE GESTIÓN DE PRODUCTOS (MODIFICADO) ---
    public void agregarProducto(Producto producto) {
        // Usamos nuestra propia búsqueda, ya que la binaria no aplica
        if (buscarProducto(producto.getNombre()).isEmpty()) {
            
    
            // this.productos.add(producto);
            this.productos.addLast(producto);
            
        
            // Ya no podemos usar Collections.sort() en nuestra lista manual.
            // La lista estará desordenada hasta que llamemos al método de burbuja.
            // Collections.sort(this.productos); 
            
            System.out.println("Producto '" + producto.getNombre() + "' agregado al inventario.");
        } else {
            System.out.println("Error: El producto '" + producto.getNombre() + "' ya existe.");
        }
    }

  
    // 1. Búsqueda (MODIFICADA - Búsqueda Lineal Manual)
    // Se elimina la búsqueda binaria [Collections.binarySearch]
    public Optional<Producto> buscarProducto(String nombre) {
        // Iteramos manualmente sobre nuestra DoublyLinkedList
        // (Esto funciona porque la hicimos 'Iterable')
        for (Producto p : productos) {
            if (p.getNombre().equals(nombre)) {
                return Optional.of(p);
            }
        }
        return Optional.empty(); // No se encontró
    }


   

    // 2. Ordenamiento de Burbuja (Manual)
    // (Este método se vuelve MUY INEFICIENTE (O(n^3)) con listas enlazadas
    // porque get(j) y set(j) son O(n) cada uno, pero sigue funcionando
    // gracias a que implementamos get() y set() en DoublyLinkedList)
    public void ordenarPorNombreBurbuja() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                // --- CAMBIO (Funcional) ---
                // productos.get(j) ahora llama a nuestro método manual
                if (productos.get(j).getNombre().compareTo(productos.get(j + 1).getNombre()) > 0) {
                    
                    // --- CAMBIO (Funcional) ---
                    Producto temp = productos.get(j);
                    // productos.set(j, productos.get(j + 1)); // Llama a nuestro set()
                    // productos.set(j + 1, temp); // Llama a nuestro set()
                
                    
        
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nInventario ordenado manualmente (Burbuja sobre Lista Doble).");
    }

    // 3. Ordenamiento con Herramienta Nativa de Java (MODIFICADO)
    public void ordenarPorNombreNativo() {
        // --- CAMBIO (DESHABILITADO) ---
        // Collections.sort(productos); // <-- ESTO YA NO COMPILA
        
        System.out.println("\nError: Collections.sort() no puede usarse sobre nuestra lista manual.");
        System.out.println(" (Este paso corresponde a la PARTE B de la actividad)");
    }


    // --- MÉTODOS PARA REGISTRAR MOVIMIENTOS ---
    // (Sin cambios, gracias a la búsqueda secuencial)
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

    public void generarReporteExistencias() {
        System.out.println("\n--- REPORTE DE EXISTENCIAS ---");
        if (productos.isEmpty()) { // Llama a nuestro isEmpty()
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("------------------------------------");
        System.out.printf("%-20s | %s\n", "Producto", "Stock Actual");
        System.out.println("------------------------------------");
        for (Producto p : productos) { // Usa nuestro iterador
            System.out.println(p); 
        }
        System.out.println("------------------------------------\n");
    }

    public void generarReporteMovimientos() {
        System.out.println("\n--- REPORTE DE MOVIMIENTOS ---");
        if (productos.isEmpty()) { // Llama a nuestro isEmpty()
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : productos) { // Usa nuestro iterador
            System.out.println("\n--- Historial de: " + p.getNombre() + " ---");
            
            // Llama a nuestro getHistorialMovimientos()
            if (p.getHistorialMovimientos().isEmpty()) { 
                System.out.println("Sin movimientos registrados.");
            } else {
                // Itera sobre nuestra SinglyLinkedList
                for (String registro : p.getHistorialMovimientos()) { 
                    System.out.println("  - " + registro);
                }
            }
        }
        System.out.println("\n--- FIN DEL REPORTE ---\n");
    }
}