package com.basasagerald.incrs.NDS;

import android.os.AsyncTask;

import java.net.InetAddress;


/**
 * Created by basasa on 6/10/17.
 */

public class ClientThread extends  AsyncTask<Void, Void, Void>  {
    InetAddress dstAddress;
    int dstPort;
    String response = "";


    ClientThread(InetAddress addr, int port) {
        dstAddress = addr;
        dstPort = port;

    }
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
