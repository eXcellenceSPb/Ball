package Balls;

import java.util.Timer;
import java.util.TimerTask;

public class Time extends Timer
{
    private Runnable  task;
    private TimerTask timerTask;

    public void schedule(Runnable runnable, long delay)
    {
        task = runnable;
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                task.run();
            }
        };
        this.schedule(timerTask, delay);
    }
}