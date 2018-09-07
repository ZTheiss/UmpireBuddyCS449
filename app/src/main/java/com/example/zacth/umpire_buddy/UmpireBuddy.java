package com.example.zacth.umpire_buddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UmpireBuddy extends AppCompatActivity {

    Button bbtn;
    TextView btxt;
    TextView stxt;
    Button sbtn;
    Integer bCounter = 0;
    Integer sCounter = 0;
    Integer outs = 0;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umpire_buddy);
        bbtn=(Button) findViewById(R.id.BallBtn);
        sbtn=(Button) findViewById(R.id.StrikeBtn);
        btxt=findViewById(R.id.BallTextView);
        stxt=findViewById(R.id.StrikeTextView);

        Toolbar myToolbar =(findViewById(R.id.my_toolbar));
        setSupportActionBar(myToolbar);

        bbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bCounter++;
                if (bCounter >= 4){
                        AlertDialog.Builder builder = new AlertDialog.Builder(UmpireBuddy.this);
                        builder.setTitle("Walk!");
                        builder.setMessage("Pitcher has thrown too many balls, batter walks.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btxt.setText("Balls: 0");
                                stxt.setText("Strikes: 0");
                                bCounter = 0;
                                sCounter = 0;
                            }
                        });
                        builder.show();
                }
                else {
                    btxt.setText("Balls: " + bCounter);
                }
            }
        });
        sbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sCounter++;
                if (sCounter >= 3) {
                    outs++;
                    AlertDialog.Builder strikesbuilder = new AlertDialog.Builder(UmpireBuddy.this);
                    strikesbuilder.setTitle("Out!");
                    strikesbuilder.setMessage("The batter has struck out! Number of outs: " + outs);
                    strikesbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btxt.setText("Balls: 0");
                            stxt.setText("Strikes: 0");
                            bCounter = 0;
                            sCounter = 0;
                        }
                    });
                    strikesbuilder.show();
                    if (outs >= 3){
                        AlertDialog.Builder outsBuilder = new AlertDialog.Builder(UmpireBuddy.this);
                        outsBuilder.setTitle("Innings Change!");
                        outsBuilder.setMessage("That is 3 outs, change sides!");
                        outsBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btxt.setText("Balls: 0");
                                stxt.setText("Strikes: 0");
                                bCounter = 0;
                                sCounter = 0;
                                outs = 0;
                            }
                        });
                        outsBuilder.show();
                    }
                }
                else {
                    stxt.setText("Strikes: " + sCounter);
                }
            }
        });
    }

    public void onBallButtonTap(View v){
        Toast myToast = Toast.makeText(getApplicationContext(), "Ball", Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void onStrikeButtonTap(View v){
        Toast myToast = Toast.makeText(getApplicationContext(), "Strike", Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_umpire_buddy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            btxt.setText("Balls: 0");
            stxt.setText("Strikes: 0");
            bCounter = 0;
            sCounter = 0;
            outs = 0;
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initAboutActivity(){
        AboutActivity aboutActivity = findViewById(R.id.about)
    }
}
