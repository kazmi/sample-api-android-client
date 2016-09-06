package ae.kazmi.networkprogramming;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** Tag for the log messages */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    private String apiToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        apiToken = intent.getStringExtra("api_token");

        Button btnFetchJokes = (Button) findViewById(R.id.btnFetchJokes);
        btnFetchJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokesAsyncTask task = new JokesAsyncTask();
                task.execute(apiToken);
            }
        });

    }

    /**
     * Update the UI with the given information.
     */
    private void updateUi(List<Joke> jokes) {

        for (Joke joke : jokes) {
            Log.d(LOG_TAG, joke.getContent());
        }


    }

    private class JokesAsyncTask extends AsyncTask<String, Void, List<Joke>> {

        @Override
        protected List<Joke> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Perform the HTTP request to fetch jokes and process the response.
            List<Joke> result = Utils.fetchJokes(urls[0]);
            return result;
        }

        protected void onPostExecute(List<Joke> result) {

            if (result == null) {
                return;
            }
            // Update the information displayed to the user.
            updateUi(result);
        }

    }


}
