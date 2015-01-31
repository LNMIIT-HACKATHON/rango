package contagious.rango;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    public static final String[][] WORDS = {
        {"apple", "aeroplane", "aircraft"},
        {"bear", "banana", "bridge"},
        {"carrot", "chocolate", "contagious"},
        {"dust", "drainage", "drenched"},
        {"elephant", "emotion", "equator"},
        {"fish", "friendly", "fantastic"},
    };

    public static final String[][] SENTENCES = {
            {"Who am I?", "Who i am?"},
            {"What are you doing?", "What doing are you?"},
            {"I can drink water.", "I water can drink."},
            {"Charlie is a girl.", "Charlie girl a is."}
    };

    private int player1Score, player2Score;
    private boolean isCorrect;
    private int a=0;
    private boolean answered=false;

    private Handler handler;
    private Timer timer;
    private TimerTask timerTask;
    private Runnable runnable;
    private Button p2b2, p1b1, p1b2, p2b1;
    private TextView p2text, p1text, p2t2, p2t1, p1t1, p1t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p2b2 = (Button) findViewById(R.id.p2b2);
        p2b1 = (Button) findViewById(R.id.p2b1);
        p2text = (TextView) findViewById(R.id.p2text);
        p1text = (TextView) findViewById(R.id.p1text);
        p2t2 = (TextView) findViewById(R.id.p2t2);
        p2t1 = (TextView) findViewById(R.id.p2t1);
        p1t1 = (TextView) findViewById(R.id.p1t1);
        p1t2 = (TextView) findViewById(R.id.p1t2);
        p1b1 = (Button) findViewById(R.id.p1b1);
        p1b2 = (Button) findViewById(R.id.p1b2);

        player2Score = 0;
        player1Score = 0;


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(!answered)
                refreshGame();
                else
                    answered = false;

                handler.postDelayed(this,5000);
            }
        };
        handler.postDelayed(runnable,5000);
        //timer = new Timer();
        //timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }

    private void refreshGame() {
        Log.d("Refresh","Refreshing");
        Random random = new Random();
        switch (random.nextInt(2)){
            case 0:

                break;
            case 1:
                break;
            case 2:
                int random1 = random.nextInt(2);
                int random2 = random.nextInt(1);
                p1t1.setText(SENTENCES[random1][random2]);
                p2t2.setText(SENTENCES[random1][random2]);
                if(random2==0)
                    isCorrect = true;
                else
                    isCorrect = false;
                break;
        }
        a++;
        p1t1.setText(""+a);

//        Toast.makeText(getApplicationContext(), "refreshing", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        // score update
        int id = view.getId();
        switch (id) {
            case R.id.p1b1:
                if (isCorrect)
                    player1Score++;
                else
                    player1Score--;
                break;
            case R.id.p1b2:
                if (!isCorrect)
                    player1Score++;
                else
                    player1Score--;
                break;
            case R.id.p2b1:
                if (isCorrect)
                    player2Score++;
                else
                    player2Score--;
                break;
            case R.id.p2b2:
                if (!isCorrect)
                    player2Score++;
                else
                    player2Score--;
                break;
        }

        // timer reset
        //timer.cancel();
        //timer = new Timer(); // try deleting this
        //timer.scheduleAtFixedRate(timerTask, 0, 5000);
        answered = true;
        handler.postDelayed(runnable,5000);
    }

}
