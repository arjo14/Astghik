package bot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private int id;
    private Group group;
    private Teacher teacher;
    private Room room;
    private Lesson lesson;
    private LessonTime lessonTime;
    private Conflict conflict;
}
