package tacos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class TaqueriaManager {

    public List<Taqueria> getTaqueriasRatingOrder(DataSource dataSource) {
        ArrayList<Taqueria> taquerias = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM taquerias order by rating desc, name asc");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Taqueria newTaqueria = new Taqueria();

                newTaqueria.setId(resultSet.getInt("id"));
                newTaqueria.setName(resultSet.getString("name"));
                newTaqueria.setRating(resultSet.getInt("rating"));

                taquerias.add(newTaqueria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taquerias;
    }

    public List<Taqueria> getTaqueriasNameOrder(DataSource dataSource) {
        ArrayList<Taqueria> taquerias = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM taquerias order by name asc");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Taqueria newTaqueria = new Taqueria();

                newTaqueria.setId(resultSet.getInt("id"));
                newTaqueria.setName(resultSet.getString("name"));
                newTaqueria.setRating(resultSet.getInt("rating"));

                taquerias.add(newTaqueria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taquerias;
    }

    public Taqueria getTaqueriaById(DataSource dataSource, int id) {
        Taqueria taqueria = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from taquerias where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                taqueria = new Taqueria();

                taqueria.setId(resultSet.getInt("id"));
                taqueria.setName(resultSet.getString("name"));
                taqueria.setRating(resultSet.getInt("rating"));
            }

            // omg - I'm not closing my connection!!!
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return taqueria;
    }
}
