package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

/**
 * Created by student on 28.03.2017.
 */

public class ProcessingThread extends Thread {

    private Context context;


    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        while(true) {
            Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
            sendMessage(Constants.MESSAGE_STRING);
            sendMessage(Constants.MESSAGE_INTEGER);
            sendMessage(Constants.MESSAGE_ARRAY_LIST);

            try {
                Thread.sleep(Constants.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(int msgType) {
        Intent intent = new Intent();

        switch (msgType) {
            case Constants.MESSAGE_STRING:
                intent.setAction(Constants.ACTION_STRING);
                intent.putExtra(Constants.DATA, Constants.STRING_DATA);
                break;

            case Constants.MESSAGE_INTEGER:
                intent.setAction(Constants.ACTION_INTEGER);
                intent.putExtra(Constants.DATA, Constants.INTEGER_DATA);
                break;

            case Constants.MESSAGE_ARRAY_LIST:
                intent.setAction(Constants.ACTION_ARRAY_LIST);
                intent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
                break;
        }

        context.sendBroadcast(intent);
    }


}
