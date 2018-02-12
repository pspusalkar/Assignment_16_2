package com.example.poojajoshi.assignment_16_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import java.io.File;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the progress bars.
        final ProgressBar bar1 = findViewById(R.id.progressBar1);
        final ProgressBar bar2 = findViewById(R.id.progressBar2);

        // get download button handle and on lcick listener for the same
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager manager = new DownloadManager(bar1, bar2, getFilesDir().getAbsolutePath());
                manager.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://www.google.co.in/search?q=images&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjTy5Dl75rZAhWKN48KHVyaDMkQ_AUICigB&biw=1366&bih=693#imgrc=mVrwcCQle9g31M:");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
