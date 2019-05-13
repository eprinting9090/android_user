package user.eprinting.com.eprinting_user.listener;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */

public interface ScheduleListener {
    boolean onRun(int requestCode);
    void onDone(int requestCode);
    void onFail(int requestCode);
}
