package bot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {

    private int id;
    private int groupId;
    private int teacherId;
    private int roomId;
    private int lessonId;
    private int lessonTimeId;
    private int conflictId;
}
