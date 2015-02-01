package contagious.rango;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinaleActivity extends Activity {

    Typeface type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finale);

        TextView p1 = (TextView) findViewById(R.id.p1);
        TextView p2 = (TextView) findViewById(R.id.p2);
        int player1Score = getIntent().getIntExtra(MainActivity.PLAYER1_SCORE, 0);
        int player2Score = getIntent().getIntExtra(MainActivity.PLAYER2_SCORE,0);

        type = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");

        p1.setTypeface(type);
        p2.setTypeface(type);

        if(player1Score > player2Score){
            p1.setText("You Win!");
            p1.setTextColor(Color.parseColor("#39DBAC"));
            p2.setText("You Lose!");
            p2.setTextColor(Color.parseColor("#EA3556"));
        }
        if(player1Score < player2Score){
            p1.setText("You Lose!");
            p1.setTextColor(Color.parseColor("#EA3556"));
            p2.setText("You Win!");
            p2.setTextColor(Color.parseColor("#39DBAC"));
        }
        if(player1Score == player2Score){
            p1.setText("Draw :(");
            p1.setTextColor(Color.parseColor("#FFFFFF"));
            p2.setText("Draw :(");
            p2.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }
    public void restart(View view){
        startActivity(new Intent(this,MainActivity.class));
    }

    public void exit(View view){
        finish();
    }

}
