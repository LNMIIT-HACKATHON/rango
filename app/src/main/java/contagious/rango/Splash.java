package contagious.rango;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void click(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}