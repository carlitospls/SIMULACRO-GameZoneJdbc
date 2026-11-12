package es.daw.gamezonejdbc.repository;

import es.daw.gamezonejdbc.exception.AccesoDatosException;
import es.daw.gamezonejdbc.model.Plataforma;
import es.daw.gamezonejdbc.model.Juego;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Capa de datos: la única que escribe SQL.
@Repository
@RequiredArgsConstructor
public class JuegoRepositoryJdbc implements JuegoRepository {

    // SELECT común a findById y findAll (JOIN para traer también el nombre de la plataforma)
    private static final String SELECT_BASE = """
            SELECT j.codigo, j.nombre, j.precio, j.año,
                   pf.codigo AS pf_codigo, pf.nombre AS pf_nombre
            FROM juego j
            JOIN plataforma pf ON j.codigo_plataforma = pf.codigo
            """;

    private final DataSource dataSource;

    @Override
    public Optional<Juego> findById(Integer codigo) {
        String sql = SELECT_BASE + "WHERE j.codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar el juego " + codigo, e);
        }
    }

    @Override
    public List<Juego> findAll() {
        String sql = SELECT_BASE + "ORDER BY j.codigo";
        List<Juego> juegos = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                juegos.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar los juegos", e);
        }
        return juegos;
    }

    @Override
    public void save(Juego juego) {
        String sql = "INSERT INTO juego (nombre, precio, año, codigo_plataforma) VALUES (?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, juego.getNombre());
            ps.setDouble(2, juego.getPrecio());
            ps.setInt(3, juego.getAño());
            ps.setInt(4, juego.getPlataforma().getCodigo());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    juego.setCodigo(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al guardar el juego", e);
        }
    }

    @Override
    public void update(Juego juego) {
        String sql = "UPDATE juego SET nombre = ?, precio = ?, año = ?, codigo_plataforma = ? WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, juego.getNombre());
            ps.setDouble(2, juego.getPrecio());
            ps.setInt(3, juego.getAño());
            ps.setInt(4, juego.getPlataforma().getCodigo());
            ps.setInt(5, juego.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar el juego " + juego.getCodigo(), e);
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM juego WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al borrar el juego " + codigo, e);
        }
    }

    @Override
    public boolean existsByPlataformaCodigo(Integer codigoPlataforma) {
        String sql = "SELECT 1 FROM juego WHERE codigo_plataforma = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoPlataforma);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al comprobar los juegos de la plataforma " + codigoPlataforma, e);
        }
    }

    // Fila del ResultSet → objeto Juego
    private Juego mapRow(ResultSet rs) throws SQLException {
        Plataforma plataforma = new Plataforma(
                rs.getInt("pf_codigo"),
                rs.getString("pf_nombre"));

        return new Juego(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                rs.getInt("año"),
                plataforma);

    }
}
