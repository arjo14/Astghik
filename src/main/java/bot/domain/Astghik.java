package bot.domain;


import java.util.List;

public interface Astghik {

    List<Schedule> getScheduleByGroupId(int id);

    List<Schedule> getScheduleByWeekday(int weekdayId,int groupId);

    List<Weekday> getWeekdays();

}
