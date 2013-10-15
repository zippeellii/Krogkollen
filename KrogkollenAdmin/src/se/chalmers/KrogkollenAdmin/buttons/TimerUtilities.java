package se.chalmers.KrogkollenAdmin.buttons;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class TimerUtilities {
    private ButtonsPresenter presenter;
    private Timer inputDisabledTimer;
    private Timer notificationTimer;
    private boolean firstTimeNotificationTimer = true;
    private boolean firstTimeInputTimer = true;
    public static final int DISABLE_TIME =
             20000;
            // 1000;
    public static final int NOTIFICATION_TIME =
             1000 * 60 * 30;
            // 5000;
    private static TimerUtilities instance = null;

    protected TimerUtilities() {
        // Exists only to defeat instantiation.
    }

    public static TimerUtilities getInstance() {
        if(instance == null) {
            instance = new TimerUtilities();
        }
        return instance;
    }

    public void setupActivePresenter(ButtonsPresenter presenter) {
        this.presenter = presenter;
        resetNotificationTimer();
        resetInputTimer();
    }

    public void disableAllTimers() {
        disableTimer(inputDisabledTimer);
        disableTimer(notificationTimer);
    }

    public void disableNotificationTimer() {
        disableTimer(notificationTimer);
    }

    private void disableTimer(Timer timer) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * Runs a timer that handles disabling of the buttons. Makes sure that the user doesn't spam the server by disabling more than one click every 20 second.
     */
    private void runInputTimer() {
        inputDisabledTimer = new Timer();
        inputDisabledTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!firstTimeNotificationTimer) {
                    disableTimerFinished();
                } else {
                    firstTimeInputTimer = false;
                }
            }

        }, 0, DISABLE_TIME);
    }

    // Creates a new timer for the notifications, and runs it.
    private void runNotificationTimer() {
        notificationTimer = new Timer();
        notificationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!firstTimeNotificationTimer) {
                    notificationTimerFinished();
                } else {
                    firstTimeNotificationTimer = false;
                }
            }

        }, 0, NOTIFICATION_TIME);
    }

    // This method is called whenever the notification goes up to the time given.
    private void notificationTimerFinished() {
        //This method runs in the same thread as the timer.
        presenter.getView().runOnUiThread(Notification_Timer_Tick);
    }

    // Only called by notificationsTimerFinished and runs on the GUI thread.
    private Runnable Notification_Timer_Tick = new Runnable() {
        public void run() {
            //Changes the UI to let the user know that input isn't allowed for a while.
            //This method runs in the same thread as the GUI.
            presenter.showNotification();
        }
    };

    private void disableTimerFinished() {
        //This method runs in the same thread as the timer.
        presenter.getView().runOnUiThread(Disable_Timer_Tick);
    }

    // This method runs in the same thread as the UI.
    // It activates the buttons and updates the GUI.
    private Runnable Disable_Timer_Tick = new Runnable() {
        public void run() {
            //Changes the UI to let the user know that input isn't allowed for a while.
            //This method runs in the same thread as the GUI.
            presenter.inputAcceptedAgain();
        }
    };

    private void resetInputTimer() {
        disableTimer(inputDisabledTimer);
        firstTimeInputTimer = true;
        runInputTimer();
    }

    public void resetNotificationTimer() {
        disableTimer(notificationTimer);
        firstTimeNotificationTimer = true;
        runNotificationTimer();
    }
}
