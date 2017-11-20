package bot.domain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;


public class AstghikImpl implements Astghik {

    private JdbcTemplate jdbcTemplate;

    public AstghikImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Schedule> getScheduleByGroupId(int id) {
        String sql = "SELECT * FROM ASTGHIK.SCHEDULE WHERE GROUP_ID = ?";

        List<ScheduleEntity> list = jdbcTemplate.query(sql, ((resultSet, i) -> new ScheduleEntity(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getInt(6),
                resultSet.getInt(7))), id);

        return list.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Schedule> getScheduleByWeekday(int weekdayId, int groupId) {
        String sql = "SELECT * FROM ASTGHIK.SCHEDULE WHERE GROUP_ID = ? AND WEEKDAY_ID = ?";

        List<ScheduleEntity> list = jdbcTemplate.query(sql, ((resultSet, i) -> new ScheduleEntity(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getInt(6),
                resultSet.getInt(7))), groupId,weekdayId);

        return list.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Weekday> getWeekdays() {
        String sql = "SELECT * FROM ASTGHIK.WEEKDAY ";

        return jdbcTemplate.query(sql, ((resultSet, i) -> new Weekday(resultSet.getInt(1),
                resultSet.getString(2))));
    }


    private Schedule entityToDto(ScheduleEntity scheduleEntity) {
        String sql1 = "SELECT * FROM ASTGHIK.TEACHER WHERE ID = ?";
        Teacher teacher = jdbcTemplate.queryForObject(sql1, ((resultSet, i) -> new Teacher(resultSet.getInt(1),
                resultSet.getString(2))), scheduleEntity.getTeacherId());

        String sql2 = "SELECT * FROM ASTGHIK.GROUP WHERE ID = ?";
        Group group = jdbcTemplate.queryForObject(sql2, ((resultSet, i) -> new Group(resultSet.getInt(1),resultSet.getString(2))),
                scheduleEntity.getGroupId());

        String sql3 = "SELECT * FROM ASTGHIK.LESSON_TIME WHERE ID = ?";
        Lesson lesson = jdbcTemplate.queryForObject(sql3, ((resultSet, i) -> new Lesson(resultSet.getInt(1),resultSet.getString(2))),
                scheduleEntity.getLessonId());

        String sql4 = "SELECT * FROM ASTGHIK.ROOM WHERE ID = ?";
        Room room = jdbcTemplate.queryForObject(sql4, ((resultSet, i) -> new Room(resultSet.getInt(1),resultSet.getString(2))),
                scheduleEntity.getRoomId());

        String sql5 = "SELECT * FROM ASTGHIK.LESSON_TIME WHERE ID = ?";
        LessonTime lessonTime = jdbcTemplate.queryForObject(sql5, ((resultSet, i) -> new LessonTime(resultSet.getInt(1),resultSet.getString(2))),
                scheduleEntity.getLessonTimeId());

        String sql6 = "SELECT * FROM ASTGHIK.CONFLICT WHERE ID = ?";
        Conflict conflict = jdbcTemplate.queryForObject(sql6, ((resultSet, i) -> new Conflict(resultSet.getInt(1),resultSet.getString(1))),
                scheduleEntity.getConflictId());

        return new Schedule(scheduleEntity.getId(), group, teacher, room, lesson, lessonTime, conflict);
    }

}
