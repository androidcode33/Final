package com.basasagerald.incrs.NDS;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by basasa on 6/10/17.
 */

public class NSDHelper extends AppCompatActivity{
    private String SERVICE_NAME = "Client Device";
    private String SERVICE_TYPE = "_http._tcp.";
    private  String TAG="CHAT";
    private InetAddress hostAddress;
    private int hostPort;
     NsdManager mNsdManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// set content view and other stuff
// NSD Stuff
        mNsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);
        mNsdManager.discoverServices(SERVICE_TYPE,
                NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mNsdManager != null) {
            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mNsdManager != null) {
            mNsdManager.discoverServices(
                    SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
        }
    }
    @Override
    protected void onDestroy() {
        if (mNsdManager != null) {
            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
        }
        super.onDestroy();
    }
    NsdManager.DiscoveryListener mDiscoveryListener = new NsdManager.DiscoveryListener() {
        // Called as soon as service discovery begins.
        @Override
        public void onDiscoveryStarted(String regType) {
            Log.d(TAG, "Service discovery started");
        }
        @Override
        public void onServiceFound(NsdServiceInfo service) {
// A service was found! Do something with it.
            Log.d(TAG, "Service discovery success : " + service);
            Log.d(TAG, "Host = "+ service.getServiceName());
            Log.d(TAG, "port = " + String.valueOf(service.getPort()));
            if (!service.getServiceType().equals(SERVICE_TYPE)) {
// Service type is the string containing the protocol and
// transport layer for this service.

                Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
            } else if (service.getServiceName().equals(SERVICE_NAME)) {
// The name of the service tells the user what they'd be
// connecting to. It could be "Bob's Chat App".
                Log.d(TAG, "Same machine: " + SERVICE_NAME);
            } else {
                Log.d(TAG, "Diff Machine : " + service.getServiceName());
// connect to the service and obtain serviceInfo
                mNsdManager.resolveService(service, mResolveListener);
            }
        }
        @Override
        public void onServiceLost(NsdServiceInfo service) {
// When the network service is no longer available.
// Internal bookkeeping code goes here.
            Log.e(TAG, "service lost" + service);
        }
        @Override
        public void onDiscoveryStopped(String serviceType) {
            Log.i(TAG, "Discovery stopped: " + serviceType);
        }
        @Override
        public void onStartDiscoveryFailed(String serviceType, int errorCode) {
            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            mNsdManager.stopServiceDiscovery(this);
        }
        @Override
        public void onStopDiscoveryFailed(String serviceType, int errorCode) {
            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            mNsdManager.stopServiceDiscovery(this);
        }
    };
    NsdManager.ResolveListener mResolveListener = new NsdManager.ResolveListener() {
        @Override
        public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
// Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "Resolve failed " + errorCode);
            Log.e(TAG, "serivce = " + serviceInfo);
        }
        @Override
        public void onServiceResolved(NsdServiceInfo serviceInfo) {
            Log.d(TAG, "Resolve Succeeded. " + serviceInfo);
            if (serviceInfo.getServiceName().equals(SERVICE_NAME)) {
                Log.d(TAG, "Same IP.");
                return;
            }
// Obtain port and IP
            hostPort = serviceInfo.getPort();
            hostAddress = serviceInfo.getHost();

            ClientThread myClient = new ClientThread(hostAddress,hostPort);
            myClient.execute();


        }
    };
}
