package ae.kazmi.networkprogramming;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    /** Tag for the log messages */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final AutoCompleteTextView usernameEditText = (AutoCompleteTextView) findViewById(R.id.username);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);

        Button signInButton = (Button) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonData = new JSONObject();
                try {

                    jsonData.put("email", usernameEditText.getText().toString());
                    jsonData.put("password", passwordEditText.getText().toString());

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getLocalizedMessage());
                }

                String payload = jsonData.toString();

                LoginAsyncTask task = new LoginAsyncTask();
                task.execute(payload);

            }
        });
    }

    private void startMainActivity(String apiToken) {

        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        intent.putExtra("api_token", apiToken);
        startActivity(intent);
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... payloads) {

            // Perform the HTTP request for earthquake data and process the response.
            String apiToken = Utils.performLogin(payloads[0]);
            return apiToken;

        }

        @Override
        protected void onPostExecute(String apiToken) {
            if (apiToken == null) {
                return;
            }

            startMainActivity(apiToken);

        }

    }

}
