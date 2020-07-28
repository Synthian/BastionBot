package com.iancordle.bb.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;

import static com.iancordle.bb.tasks.SpeedrunTasks.formatDuration;
import static com.iancordle.bb.tasks.SpeedrunTasks.ordinalOf;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeedrunTasksStaticTest {

    @Test
    public void duration_format() {
        Duration d = Duration.ofHours(1);
        String r = formatDuration(d);
        assertEquals("1:00:00", r);

        d = Duration.ofMinutes(23).plusSeconds(54);
        r = formatDuration(d);
        assertEquals("23:54", r);

        d = Duration.ofMinutes(23).plusSeconds(54).plusMillis(743);
        r = formatDuration(d);
        assertEquals("23:54.743", r);
    }

    @Test
    public void ordinal_logic() {
        String r = ordinalOf(100);
        assertEquals("100th", r);

        r = ordinalOf(101);
        assertEquals("101st", r);

        r = ordinalOf(2);
        assertEquals("2nd", r);

        r = ordinalOf(3);
        assertEquals("3rd", r);

        r = ordinalOf(12);
        assertEquals("12th", r);

        r = ordinalOf(22);
        assertEquals("22nd", r);
    }

}
