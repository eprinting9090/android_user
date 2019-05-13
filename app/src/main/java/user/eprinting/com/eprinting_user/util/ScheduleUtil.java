package user.eprinting.com.eprinting_user.util;

import android.os.Handler;

import user.eprinting.com.eprinting_user.listener.ScheduleListener;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */

public class ScheduleUtil {
    private Handler handler;
    private Runnable runnable;
    private boolean active = false;
    private long interval;

    public ScheduleUtil(final ScheduleListener listener, final int requestCode) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (listener.onRun(requestCode)) {
                        listener.onDone(requestCode);
                        runAgain();
                    } else {
                        listener.onFail(requestCode);
                        runAgain();
                    }
                } catch (Exception e) {
                    listener.onFail(requestCode);
                    runAgain();
                    e.printStackTrace();
                }
            }
        };
    }

    private void runAgain() {
        if (active) {
            ScheduleUtil.this.run(interval);
        }
    }

    public ScheduleUtil always(boolean active) {
        this.active = active;
        return this;
    }

    public void run(long interval) {
        this.interval = interval;
        handler.postDelayed(runnable, this.interval);
    }

    public void end() {
        always(false);
        handler.removeCallbacks(runnable);
    }
}
