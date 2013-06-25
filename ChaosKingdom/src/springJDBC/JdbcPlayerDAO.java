package springJDBC;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;

public class JdbcPlayerDAO implements PlayerDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String logIn(String username, String password) {
        String name = null;
        try {
            name = jdbcTemplate.queryForObject("SELECT username FROM users WHERE upper(username) = upper(?) AND password = ?", new Object[]{username, password}, String.class);
        } catch (EmptyResultDataAccessException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return name;
    }
}
