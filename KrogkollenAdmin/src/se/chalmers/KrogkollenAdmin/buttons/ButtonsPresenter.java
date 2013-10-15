package se.chalmers.KrogkollenAdmin.buttons;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import se.chalmers.KrogkollenAdmin.R;
import se.chalmers.KrogkollenAdmin.main.MainActivity;

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
 * The presenter class for the ButtonsActivity. It holds all the data and logic for the view.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ButtonsPresenter {

    private int queueTime;
    private ParseObject object;
    private ButtonsActivity view;
    private boolean buttonOnCooldown;
    private Toast toast;
    private boolean notificationsDisabled;
    private TimerUtilities timerUtilities;

    /**
     * Constructor. Gets called when the ButtonsActivity is started, so they know each other.
     *
     * @param butt The activity that should know about this presenter.
     */
    public ButtonsPresenter(ButtonsActivity butt) {
        view = butt;
        timerUtilities = TimerUtilities.getInstance();
        timerUtilities.setupActivePresenter(this);
        setupParseObject();
        setLocalQueueTime();
    }

    public void showNotification() {
        if (notificationsDisabled) {
            return;
        }

        long vibrationPattern[] = new long[10];
        for (int i = 0; i < vibrationPattern.length; i++) {
            vibrationPattern[i] = 1L;

        }
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(view)
                        .setSmallIcon(R.drawable.krogkollen_admin_logo)
                        .setContentTitle(view.getResources().getString(R.string.notification_title))
                        .setContentText(view.getResources().getString(R.string.notification_subtext))
                        .setVibrate(vibrationPattern);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(view, ButtonsActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(view);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ButtonsActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) view.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        Notification notification = mBuilder.build();
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(1, notification);
    }

    /**
     * Calls some methods when a button is clicked.
     *
     * @param newQueueTime An int corresponding to the button clicked.
     */
    public void buttonClicked(int newQueueTime) {
        if (buttonOnCooldown) {
            // Makes so that toasts doesn't stack.
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(view, view.getResources().getString(R.string.wait_part_one) + " " + TimerUtilities.DISABLE_TIME / 1000 + " " + view.getResources().getString(R.string.wait_part_two), Toast.LENGTH_SHORT);
            toast.setDuration(1);
            toast.show();
        } else {
            timerUtilities.resetNotificationTimer();
            buttonOnCooldown = true;        // So that no input is accepted in a while
            new ServerUpdateTask().execute(newQueueTime);
        }
    }

    /**
     * Sets up the connection to Parse.com.
     * Gets the object that corresponds to the logged in user.
     */
    public void setupParseObject() {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");
            query.whereEqualTo("owner", ParseUser.getCurrentUser());
            object = query.getFirst();
        } catch (ParseException pe) {
            // Do something perhaps
        }
    }

    /**
     * Gets the queueTime from the server and syncs locally.
     */
    synchronized void setLocalQueueTime() {
        queueTime = object.getInt("queueTime");
    }

    /**
     * Takes the local queueTime and sends it to the sever.
     *
     * @param newQueueTime The queueTime to be sent to the server.
     */
    synchronized void setServerQueueTime(int newQueueTime) {
        try {
            object.put("queueTime", newQueueTime);
            object.save();
        } catch (ParseException pe) {
            return;
        }
    }

    /**
     * Returns the queue time.
     *
     * @return the queue time
     */
    public int getQueueTime() {
        return queueTime;
    }

    /**
     * Logs out the user.
     * Cancels all the timers and tells Parse.com that the user isn't logged on any more.
     * Sends the user to MainActivity.
     */
    public void logOut() {
        timerUtilities.disableAllTimers();
        ParseUser.logOut();
        Intent intent = new Intent(view, MainActivity.class);
        view.startActivity(intent);
    }

    /**
     * Turns on notifications if they are off.
     * Turns off notifications if the are on.
     */
    public void toggleNotifications() {
        if (notificationsDisabled) {
            timerUtilities.resetNotificationTimer();
        } else {
            timerUtilities.disableNotificationTimer();
        }
        notificationsDisabled = !notificationsDisabled;
    }

    // A task to be be run on another thread, making sure that it shows a loading indicator when the task is executing.
    private class ServerUpdateTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            // This is what we want to do before the possibly time-consuming task is started.
            view.showProgressDialog();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            // This is what we want to do, that might take some time.
            setServerQueueTime(integers[0]);
            setLocalQueueTime();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // This is what we want to do when the task is done.
            view.updateGUI();
            view.hideProgressDialog();
        }
    }

    public ButtonsActivity getView() {
        return view;
    }

    public void inputAcceptedAgain() {
        buttonOnCooldown = false;
        view.updateGUI();
    }
}
