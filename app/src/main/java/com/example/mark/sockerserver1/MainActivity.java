package com.example.mark.sockerserver1;

        import android.content.Context;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.lang.ClassNotFoundException;
        import java.net.ServerSocket;
        import java.net.Socket;

        import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    private static MainActivity INSTANCE;
    TextView mTextView;
    TextView scoreH,scoreA,nameH,nameA;
    Handler handler=new Handler();
    int count = 0;



    static int homeScore = 0;
    static int awayScore = 0;
    String homeName, awayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println( "before object");
        ChatServer chat = new ChatServer(8887);
        System.out.println( "after object");
        chat.start();
        System.out.println( "after start");

        //mTextView=(TextView)findViewById(R.id.homeScore);
        scoreH=(TextView)findViewById(R.id.homeScore);
        scoreA=(TextView)findViewById(R.id.awayScore);

        handler.post(updateTextRunnable);

    }
    Runnable updateTextRunnable=new Runnable(){
        public void run() {

            count++;
            scoreH.setText(Integer.toString(homeScore));
            scoreA.setText(Integer.toString(awayScore));

            handler.postDelayed(this, 1000);
        }
    };
    public void updateScreen() {





    }
    public void setText(String a) {
        System.out.println("setTEXT");

        TextView test = (TextView) findViewById(R.id.homeScore);
        test.setText("hello");

    }

}
