package tacos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class TaqueriaManager extends DBManager {

    @Resource(lookup = "java:app/jdbc/nachoDB")
    DataSource dataSource;

    private Taqueria taqueriaFromDB(ResultSet resultSet) throws SQLException {
        Taqueria taqueria = new Taqueria();

        taqueria.setId(resultSet.getInt("id"));
        taqueria.setName(resultSet.getString("name"));
        taqueria.setRating(resultSet.getInt("rating"));

        return taqueria;
    }

    public List<Taqueria> getTaqueriasRatingOrder() {
        ArrayList<Taqueria> taquerias = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM taquerias order by rating desc, name asc");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                taquerias.add(taqueriaFromDB(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return taquerias;
    }

    public List<Taqueria> getTaqueriasNameOrder() {
        ArrayList<Taqueria> taquerias = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM taquerias order by name asc");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taquerias.add(taqueriaFromDB(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return taquerias;
    }

    public Taqueria getTaqueriaById(int id) {
        Taqueria taqueria = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from taquerias where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                taqueria = taqueriaFromDB(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // this would be logged

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return taqueria;
    }
}
