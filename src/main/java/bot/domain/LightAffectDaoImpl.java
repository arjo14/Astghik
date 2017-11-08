package bot.domain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


public class LightAffectDaoImpl implements LightAffectDao {

    private JdbcTemplate jdbcTemplate;

    public LightAffectDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }





}
