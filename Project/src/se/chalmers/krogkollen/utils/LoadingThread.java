package se.chalmers.krogkollen.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * LoadingThread (UTF-8)
 * <p/>
 * Author: Johan Backman
 * Date: 2013-09-27
 */

public class LoadingThread extends Thread {
    private Handler handler;

    public final static String STATE = "se.chalmers.krogkollen.STATE";

    public LoadingThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            this.wait(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt(STATE, 1);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

}
