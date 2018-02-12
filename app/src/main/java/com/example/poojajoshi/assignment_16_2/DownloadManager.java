package com.example.poojajoshi.assignment_16_2;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import android.os.Environment;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class DownloadManager extends AsyncTask<String, Integer, String > {

    ProgressBar bar1;
    ProgressBar bar2;
    String filePath;
    public DownloadManager(ProgressBar bar1, ProgressBar bar2, String filePath) {
        this.bar1 = bar1;
        this.bar2 = bar2;
        this.filePath = filePath;
    }

    @Override
    protected String doInBackground(String... str) {
        String name = "";
        InputStream input = null;
        OutputStream output = null;
        BufferedOutputStream outputStream;
        Bitmap bitmap = null;
        try{
            // get URL connections
            URL url = new URL(str[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            int lengthOfFile = connection.getContentLength();

            input = connection.getInputStream();

            ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

            outputStream = new BufferedOutputStream(dataStream);

            String fileName = filePath + "image.png";

            byte data[] = new byte[12000];
            long total = 0;
            int count;

            while ((count = input.read(data)) != 1) {
                if ( isCancelled() ) {
                    input.close();
                    return null;
                }
                total += count;

                if ( lengthOfFile > 0 ) {
                    int progress = (int)(total * 100/lengthOfFile);

                    // updaye the progress t0 progress bar
                    bar1.setProgress(progress);
                    bar2.setProgress(progress);
                }
                outputStream.write(data, 0, count);
            }
            outputStream.flush();

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            byte[] bytes = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,bmOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }

    /*
    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        bar1.setIndeterminate(false);
        // bar1.setMax(100);
        bar1.setProgress(progress[0]);
    }
    */
}
