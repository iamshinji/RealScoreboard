package josegamerpt.realscoreboard.utils;

public class TPS implements Runnable {
    public static int TICK_COUNT = 0;
    public static long[] TICKS = new long['?'];
    public static long LAST_TICK = 0L;

    public static double getTPS() {
        return getTPS(100);
    }

    public static double getTPS(int ticks) {
        if (TICK_COUNT < ticks) {
            return 20.0D;
        }
        int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
        long elapsed = getElapsed(ticks);

        return ticks / (elapsed / 1000.0D);
    }

    public static long getElapsed(int tickID) {
        long time = TICKS[(tickID % TICKS.length)];
        return System.currentTimeMillis() - time;
    }

    public static String pegarTps() {
        double tps = getTPS();
        return String.valueOf(Math.round(tps * 100.0D) / 100.0D);
    }

    public void run() {
        TICKS[(TICK_COUNT % TICKS.length)] = System.currentTimeMillis();
        TICK_COUNT += 1;
    }
}
