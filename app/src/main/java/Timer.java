import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cesar on 6/24/2016.
 */
public class Timer extends Activity implements View.OnClickListener{
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView text;
    private final long startTime = 180 * 1000;
    private final long interval = 1 * 1000;
    @Override
    public void onClick(View v) {}



}
