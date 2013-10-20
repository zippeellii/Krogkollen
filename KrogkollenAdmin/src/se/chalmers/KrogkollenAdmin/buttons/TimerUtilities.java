package se.chalmers.KrogkollenAdmin.buttons;

import java.util.Timer;
import java.util.TimerTask;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A singleton utility class holding all the timers and the logic surrounding them.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class TimerUtilities {
    private ButtonsPresenter        presenter;
    private Timer                   inputDisabledTimer;
    private Timer                   notificationTimer;
    private boolean                 firstTimeNotificationTimer  = true;
    private boolean                 firstTimeInputTimer         = true;
    public static final int         DISABLE_TIME                = 20000;
    public static final int         NOTIFICATION_TIME           = 1000 * 60 * 30;
    private static TimerUtilities   instance                    = null;

    protected TimerUtilities() {
        // Exists only to defeat instantiation.
    }

    /**
     * Gets an instance of the class. If it already exists 1 instance of it, it returns that one.
     * @return The instance.
     */
    public static TimerUtilities getInstance() {
        if(instance == null) {
            instance = new TimerUtilities();
        }
        return instance;
    }

    /**
     * Associates a presenter with the TimerUtilties class.
     * This is to prevent that more than one presenter uses the timer class, and thus multiplicates the timers.
     * @param presenter The presenter to be associated with.
     */
    public void setupActivePresenter(ButtonsPresenter presenter) {
        this.presenter = presenter;
        resetNotificationTimer();
        resetInputTimer();
    }

    /**
     * Disables all the timers active.
     */
    public void disableAllTimers() {
        disableTimer(inputDisabledTimer);
        disableTimer(notificationTimer);
    }

    /**
     * Disables the notification timer.
     */
    public void disableNotificationTimer() {
        disableTimer(notificationTimer);
    }

    private void disableTimer(Timer timer) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void runInputTimer() {
        inputDisabledTimer = new Timer();
        inputDisabledTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!firstTimeInputTimer) {
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

    /**
     * Resets the notification timer.
     */
    public void resetNotificationTimer() {
        disableTimer(notificationTimer);
        firstTimeNotificationTimer = true;
        runNotificationTimer();
    }
}
