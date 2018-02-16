package Balls;

public class Time implements Runnable{
    static Thread thread = new Thread();
    private int t;
    public Time(int t) throws InterruptedException {
        setTime(t);
    }

    public void setTime(int t) throws InterruptedException {
        for(int i = t;i>=0;i--) {
            thread.sleep(1000);
        }
    }
    public int getTime(){
        return t;
    }

    @Override
    public void run() {
        thread.start();
    }
}
