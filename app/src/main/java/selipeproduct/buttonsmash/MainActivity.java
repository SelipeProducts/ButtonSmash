package selipeproduct.buttonsmash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
   // CountDownTimer theTimer;
    private int counter =0;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView text;
    private final long startTime = 5 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startB = (Button) this.findViewById(R.id.Smash);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.time);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        //
        long totalTime = startTime / 1000;
        long minute = totalTime/60;
        long second = totalTime%60;
        if(second <10) {
            text.setText(text.getText() + String.valueOf(minute + ":0" + second));
        }
        else{
            text.setText(text.getText() + String.valueOf(minute + ":" + second));
        }

    }
    @Override
    public void onClick(View v) {

    //public void buttonSmash(View v){
        TextView t1 =  (TextView) findViewById(R.id.score);
        counter = counter+1;
        t1.setText(Integer.toString(counter));
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("STOP");
        }
// } else {
//            countDownTimer.cancel();
//            timerHasStarted = false;
//            startB.setText("RESTART");
//        }
    }
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            text.setText("Time's up!");
            //create a dialog
            AlertDialog.Builder finAlert = new AlertDialog.Builder(MainActivity.this);
            finAlert.setMessage("Time Has Finished!\nYour score is "+ counter +". Do you want to play another game?")
                    .setCancelable(false)

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = getBaseContext().getPackageManager()   //restarts app
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);   //restarts app(end)
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert = finAlert.create();
            alert.setTitle("Another Game?");
            alert.show();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //text.setText("" + millisUntilFinished / 1000);
            long totalTime = millisUntilFinished / 1000;
            long minute = totalTime/60;
            long second = totalTime%60;
            if(second<10){
                text.setText(minute + ":0" + second);

            }else {
                text.setText(minute + ":" + second);
            }
        }
    }

}
