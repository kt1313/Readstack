package pl.javastart.readstack.domain.category;

import pl.javastart.readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao {

    //reszta bez zmian

    public Optional<Category> findById(int categoryId) {
        final String query = """
                SELECT
                    id, name, description
                FROM
                    category
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = mapRow(resultSet);
                return Optional.of(category);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}