package com.mytest.TimerCreated;

import android.content.Context;
import android.os.Handler;

public class TimerCoundown extends Thread {

    int Hours, min, sec;
    Context onfinish;


    public TimerCoundown(final String hours, String Min, String Seconds, Context currents, Context finish){


        for(Hours=Integer.parseInt(hours);Hours>=0;Hours--){
                    System.out.print(Hours);

            for(min=Integer.parseInt(Min);min>=0;min--){

                for( sec=Integer.parseInt(Seconds); sec>=0;sec--){
//------------------------------------------------------------
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Time"+ Hours +":"+ min+":"+sec);

                        }
                    },1000);
                    sec=60;
//-------------------------------------------------------------
                }
              min=60;
            }

        }
    }
}




