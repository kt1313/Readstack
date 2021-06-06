package pl.javastart.readstack.domain.discovery;

import pl.javastart.readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao {

    //reszta bez zmian

    public List<Discovery> findByCategory(int categoryId) {
        final String query = """
                SELECT
                    id, title, url, description, date_added, category_id
                FROM
                    discovery
                WHERE
                    category_id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Discovery> discoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}