package edu.illinois.cs.cs125.spring2019.lab12;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * main.
 */
public class MainActivity extends AppCompatActivity {
    /** */
    TextView ring1;
    /** */
    TextView ring2;
    /** */
    TextView ring3;
    /** */
    TextView ring4;
    /** */
    Button setAlarm1;
    /** */
    Button setAlarm2;
    /** */
    Button setAlarm3;
    /** */
    Button setAlarm4;
    /** */
    Button cancel1;
    /** */
    Button cancel2;
    /** */
    Button cancel3;
    /** */
    Button cancel4;
    /** */
    String ring1N = null;
    /** */
    String ring2N = null;
    /** */
    String ring3N = null;
    /** */
    String ring4N = null;
    /** */
    String defalutString = "No Alarm";
    /** */
    AlertDialog builder=null;
    /** */
    Calendar date = Calendar.getInstance();
    /** */
    private MediaPlayer mediaPlayer;

    /**
     * create.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.clockmusic2);
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        ring1N = settings.getString("TIME1", defalutString);
        ring2N = settings.getString("TIME2", defalutString);
        ring3N = settings.getString("TIME3", defalutString);
        ring4N = settings.getString("TIME4", defalutString);

        InitSetAlarm1();
        InitSetAlarm2();
        InitSetAlarm3();
        InitSetAlarm4();
        InitCancel1();
        InitCancel2();
        InitCancel3();
        InitCancel4();

        ring1.setText(ring1N);
        ring2.setText(ring2N);
        ring3.setText(ring3N);
        ring4.setText(ring4N);
    }

    /**
     * setalarm1.
     */
    private void InitSetAlarm1() {
        ring1 = (TextView) findViewById(R.id.ring1);
        setAlarm1 = (Button) findViewById(R.id.setalarm1);
        setAlarm1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                date.setTimeInMillis(System.currentTimeMillis());
                int mHour = date.get(Calendar.HOUR_OF_DAY);
                int mMinute = date.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                date.setTimeInMillis(System.currentTimeMillis());
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                date.set(Calendar.SECOND, 0);
                                date.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), sender);
                                }

                                String tmp = format(hourOfDay) + "：" + format(minute);
                                ring1.setText(tmp);

                                //SAVE DATA
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmp);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmp,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * cancel1.
     */
    private void InitCancel1() {
        cancel1 = (Button) findViewById(R.id.cancel1);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "alarm deleted",
                        Toast.LENGTH_SHORT).show();
                ring1.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "No Alarm");
                editor.commit();
            }
        });
    }

    /**
     * setalarm2.
     */
    private void InitSetAlarm2() {
        ring2 = (TextView) findViewById(R.id.ring2);
        setAlarm2 = (Button) findViewById(R.id.setalarm2);
        setAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                date.setTimeInMillis(System.currentTimeMillis());
                int mHour = date.get(Calendar.HOUR_OF_DAY);
                int mMinute = date.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                date.setTimeInMillis(System.currentTimeMillis());
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                date.set(Calendar.SECOND, 0);
                                date.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" + format(minute);
                                ring2.setText(tmpS);

                                //SAVE DATA
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * cancel2.
     */
    private void InitCancel2() {
        cancel2 = (Button) findViewById(R.id.cancel2);
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "Alarm Deleted",
                        Toast.LENGTH_SHORT).show();
                ring2.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "Mo Alarm");
                editor.commit();
            }
        });
    }

    /**
     * setalarm3.
     */
    private void InitSetAlarm3() {
        ring3 = (TextView) findViewById(R.id.ring3);
        setAlarm3 = (Button) findViewById(R.id.setalarm3);
        setAlarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                date.setTimeInMillis(System.currentTimeMillis());
                int mHour = date.get(Calendar.HOUR_OF_DAY);
                int mMinute = date.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                date.setTimeInMillis(System.currentTimeMillis());
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                date.set(Calendar.SECOND, 0);
                                date.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" +  format(minute);
                                ring3.setText(tmpS);

                                //SharedPreferences
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * cancel3.
     */
    private void InitCancel3() {
        cancel3 = (Button) findViewById(R.id.cancel3);
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "Alarm Deleted",
                        Toast.LENGTH_SHORT).show();
                ring3.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "No Alarm");
                editor.commit();
            }
        });
    }

    /**
     * setalarm4.
     */
    private void InitSetAlarm4() {
        ring4 = (TextView) findViewById(R.id.ring4);
        setAlarm4 = (Button) findViewById(R.id.setalarm4);
        setAlarm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                date.setTimeInMillis(System.currentTimeMillis());
                int mHour = date.get(Calendar.HOUR_OF_DAY);
                int mMinute = date.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(final TimePicker view, final int hourOfDay,
                                                  final int minute) {
                                date.setTimeInMillis(System.currentTimeMillis());
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                date.set(Calendar.SECOND, 0);
                                date.set(Calendar.MILLISECOND, 0);

                                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent.getBroadcast(
                                        MainActivity.this, 0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), sender);
                                }

                                String tmpS = format(hourOfDay) + "：" +  format(minute);
                                ring4.setText(tmpS);

                                //SAVE DATA
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MainActivity.this, "Set the Alarm at" + tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });
    }

    /**
     * cancel4.
     */
    private void InitCancel4() {
        cancel4 = (Button) findViewById(R.id.cancel4);
        cancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MainActivity.this, "Alarm Deleted",
                        Toast.LENGTH_SHORT).show();
                ring4.setText("No Alarm");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "No Alarm");
                editor.commit();
            }
        });
    }

    /**
     * keyup.
     * @param key
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(final int key, final KeyEvent event) {
        mediaPlayer.stop();
        if (key == KeyEvent.KEYCODE_BACK) {
            mediaPlayer.stop();
            builder = new AlertDialog.Builder(MainActivity.this)

                    .setTitle("Tip: ")
                    .setMessage("Do you want to exit the app?")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                                    final int whichButton) {
                                    mediaPlayer.stop();
                                    MainActivity.this.finish();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                                    final int whichButton) {
                                    mediaPlayer.stop();
                                    builder.dismiss();
                                }
                            }).show();
        }
        return true;
    }

    /**
     * format.
     * @param x - .
     * @return x.
     */
    private String format(final int x) {
        String time = "" + x;
        if (time.length() == 1) {
            time = "0" + time;
        }
        return time;
    }

}