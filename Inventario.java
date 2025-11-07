
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Inventario {

    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // --- MÉTODOS DE GESTIÓN DE PRODUCTOS (MODIFICADO) ---
    public void agregarProducto(Producto producto) {
        // Usamos la búsqueda binaria para verificar si el producto ya existe.
        if (buscarProducto(producto.getNombre()).isEmpty()) {
            this.productos.add(producto);
            // Mantenemos la lista ordenada por nombre para la búsqueda binaria.
            Collections.sort(this.productos);
            System.out.println("Producto '" + producto.getNombre() + "' agregado al inventario.");
        } else {
            System.out.println("Error: El producto '" + producto.getNombre() + "' ya existe.");
        }
    }

    // --- MÉTODOS DE BÚSQUEDA Y MANIPULACIÓN CON STREAMS (MODIFICADO) ---

    // 1. Búsqueda con Búsqueda Binaria (Sustituye a la búsqueda lineal con Stream)
    public Optional<Producto> buscarProducto(String nombre) {
        // Creamos un producto temporal solo con el nombre para usar en la búsqueda.
        int index = Collections.binarySearch(productos, new Producto(nombre, 0));

        if (index < 0) {
            return Optional.empty(); // No se encontró el producto.
        } else {
            return Optional.of(productos.get(index));
        }
    }


    // --- MÉTODOS DE ORDENAMIENTO ---

    // 2. Ordenamiento de Burbuja (Manual)
    public void ordenarPorNombreBurbuja() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productos.get(j).getNombre().compareTo(productos.get(j + 1).getNombre()) > 0) {
                    // Intercambiar productos
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nInventario ordenado manualmente (Burbuja).");
    }

    // 3. Ordenamiento con Herramienta Nativa de Java
    public void ordenarPorNombreNativo() {
        Collections.sort(productos); // Llama al método compareTo de la clase Producto
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
            System.out.println(p); // Llama a toString()
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