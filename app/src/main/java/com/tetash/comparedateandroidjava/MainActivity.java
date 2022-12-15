package com.tetash.comparedateandroidjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView showtime =findViewById(R.id.viewtime);



        //Specifying the pattern of input date and time
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateString = "14-12-2022 23:46:00";
        try{
            //formatting the dateString to convert it into a Date
            Date date = sdf.parse(dateString);

         //Toast.makeText(this, "Given Time in milliseconds : "+date.getTime(), Toast.LENGTH_SHORT).show();
            // showtime.setText("frist "+date.getTime());


            Calendar calendar = Calendar.getInstance();
            //Setting the Calendar date and time to the given date and time
            calendar.setTime(date);

            long time= System.currentTimeMillis();
            //Toast.makeText(this, "current"+time, Toast.LENGTH_LONG).show();
            if (time>calendar.getTimeInMillis()){
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();

               AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("Update")
                            .setMessage("This version is old, please update  ")
                            .setPositiveButton("UPDATE", null)
                            .show();
               dialog.setCancelable(false);

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.lite")));
                            }
                            catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=com.facebook.lite")));

                            }
                        }
                    });


            }else {
                Toast.makeText(this, "Not Update", Toast.LENGTH_SHORT).show();

            }
            SimpleDateFormat timeZoneDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            String currenttime = timeZoneDate.format(time);
            SimpleDateFormat timeZoneDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            String futuretime = timeZoneDate.format(calendar.getTimeInMillis());
            showtime.setText("Current"+currenttime+"\n"+"Future time"+futuretime);

        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}