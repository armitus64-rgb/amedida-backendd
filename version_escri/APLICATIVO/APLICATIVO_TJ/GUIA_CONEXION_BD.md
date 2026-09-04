# Cómo conectar la base de datos (para quien haga esa parte)

Ahora mismo la app guarda los trajes en memoria con `RepositorioEnMemoria`
(se pierden al cerrar la aplicación). Para conectar una base de datos real
sin tocar la parte visual (paneles, ventana), sigue estos pasos:

## 1. Crea una clase nueva que implemente `TrajeRepositorio`

Debe vivir en el mismo paquete `com.amedida.dao` y cumplir los dos métodos
de la interfaz: `insertar(Traje)` y `listarTodos()`.

Ejemplo completo usando SQLite (puedes adaptarlo a MySQL, PostgreSQL o
Mongo si el equipo prefiere otra base de datos):

```java
package com.amedida.dao;

import com.amedida.modelo.Traje;
import com.amedida.modelo.TrajeFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSQLite implements TrajeRepositorio {

    private static final String URL = "jdbc:sqlite:amedida.db";

    public RepositorioSQLite() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS trajes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    categoria TEXT NOT NULL,
                    ocasion TEXT,
                    precio REAL NOT NULL,
                    descripcion TEXT,
                    color TEXT,
                    tela TEXT,
                    incluye_forro INTEGER
                )
                """);
        }
    }

    @Override
    public void insertar(Traje traje) throws Exception {
        String sql = "INSERT INTO trajes (nombre, categoria, ocasion, precio, descripcion, color, tela, incluye_forro) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, traje.getNombre());
            ps.setString(2, traje.getCategoria());
            ps.setString(3, traje.getOcasion());
            ps.setDouble(4, traje.getPrecio());
            ps.setString(5, traje.getDescripcion());
            ps.setString(6, traje.getColor());
            ps.setString(7, traje.getTela());
            ps.setInt(8, traje.isIncluyeForro() ? 1 : 0);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Traje> listarTodos() throws Exception {
        List<Traje> lista = new ArrayList<>();
        String sql = "SELECT * FROM trajes ORDER BY id DESC";
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(TrajeFactory.crear(
                        rs.getString("categoria"), rs.getLong("id"), rs.getString("nombre"),
                        rs.getString("ocasion"), rs.getDouble("precio"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getString("tela"), rs.getInt("incluye_forro") == 1
                ));
            }
        }
        return lista;
    }
}
```

## 2. Agrega la dependencia del driver en `pom.xml`

Dentro de `<dependencies>`:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.3.0</version>
</dependency>
```

## 3. Cambia UNA línea en `Main.java`

```java
// Antes:
TrajeRepositorio repositorio = new RepositorioEnMemoria();

// Después:
TrajeRepositorio repositorio = new RepositorioSQLite();
```

Nada más. Ni `PanelRegistro`, ni `PanelCatalogo`, ni `VentanaPrincipal` se tocan —
todos reciben `TrajeRepositorio` como tipo, sin importar qué hay detrás.

## Si usan otra base de datos (MySQL, MongoDB, etc.)

El paso a paso es el mismo: crear una clase que implemente `TrajeRepositorio`
con la lógica de conexión correspondiente, y cambiar esa misma línea en `Main.java`.
