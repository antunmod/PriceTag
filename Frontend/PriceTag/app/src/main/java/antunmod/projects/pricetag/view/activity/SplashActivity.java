package antunmod.projects.pricetag.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.service.SelectService;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY = 2000;
    private boolean scheduled = false;
    private Timer splashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SelectService selectService = new SelectService();
        selectService.pingServer();

        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                SplashActivity.this.finish();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, DELAY);
        scheduled = true;

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }
}
