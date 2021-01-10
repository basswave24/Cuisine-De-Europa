/*
REFERENCE:
I have used multiple stack overflow code snippets in order to get this code working
https://stackoverflow.com/questions/2097909/playing-bg-music-across-activities-in-android
https://stackoverflow.com/questions/37478396/android-studio-add-music-to-background-over-multiple-activities-without-repeat
 I have also used mediaPlayer documentation
 */

//THIS service is used to have the violin background music throughout the application




package com.example.cuisinedeeuropa;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class BGM extends Service {

    MediaPlayer mediaPlayer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    //no need to stop it as it should run constantly
}
//REFERENCE COMPLETE
