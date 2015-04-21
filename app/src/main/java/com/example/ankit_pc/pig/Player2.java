package com.example.ankit_pc.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int temp=0;
    private int p1Score=0;
    private int p2Score=0;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        Intent intent = getIntent();
        TextView p1 = (TextView) findViewById(R.id.p1score);
        TextView p2 = (TextView) findViewById(R.id.p2score);
        if(intent!=null) {
            p1Score = intent.getIntExtra("P1Score", 0);
            p2Score = intent.getIntExtra("P2Score", 0);
            System.out.println(p1Score+"here");
            //System.out.println(textView.getText().toString());
           /* p1.setText("P1: " + p1Score);
            p2.setText("P2: " + p2Score);*/
            p1.setText(p1Score+"");
            p2.setText(p2Score+"");
            //Toast.makeText(this, "The score is: " + p1Score, Toast.LENGTH_LONG).show();
        }
        if (p1Score > 99) {
            AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
            alertDialog.setTitle("Player 1 Won!");
            alertDialog.setMessage("Yipeeaieahhh!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Player2.this, MainActivity.class);
                            intent.putExtra("P1Score", 0);
                            intent.putExtra("P2Score", 0);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);

                        }
                    });
            alertDialog.show();
        }
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollDice();
                TextView textView=(TextView) findViewById(R.id.score);
                //System.out.println(textView.getText().toString());
                textView.setText(String.valueOf(temp));

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*The commented out code here was the code we used
                in class to send an integer to the next activty.
                It was replaced by an alert dialog to be used to indicate
                a winner (for demonstration purposes).
                Use the alert dialog code in your program where appropriate
                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("score", 99);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);*/
                p2Score += temp;
                Intent intent = new Intent(Player2.this, MainActivity.class);
                intent.putExtra("P1Score", p1Score);
                intent.putExtra("P2Score", p2Score);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
               /* AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                if (p1Score > 99 || p2Score > 99) {

                    alertDialog.setTitle("Player 2 Won!");
                    alertDialog.setMessage("Yipeeaieahhh!");
                    alertDialog.show();

                }
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
*/
            }
        });

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        if (val1 == 1 || val2 == 1 )
        {
            Intent intent = new Intent(Player2.this,MainActivity.class);
            //p2Score+=temp;
            intent.putExtra("P2Score",p2Score);
            intent.putExtra("P1Score", p1Score);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        else
        {
            temp += (val1 + val2);
        }

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
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
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/