package contagious.rango;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends ActionBarActivity {

    public static final String[][] WORDS_EASY = {
        {"apple", "appel"}, {"bear", "baer"}, {"carrot", "carot"}, {"dust", "duust"},
        {"elephant", "elephanta"}, {"fish", "feesh"}, {"grape", "grap"}, {"hill", "hiel"},
        {"inch", "inchi"}, {"judge", "judje"}, {"kite", "kaite"}, {"local", "lucal"},
        {"mind", "meind"}, {"nice", "neice"}, {"opera", "upera"}, {"post", "posst"},
        {"queen", "quen"}, {"rose", "roze"}, {"show", "sohow"}, {"tame", "teme"},
        {"uranus", "urenus"}, {"video", "vedio"}, {"wagon", "wagen"}, {"xmas", "xmax"},
        {"yellow", "yellou"}, {"zeal", "zeel"}
    };

    public static final String[][] WORDS_MEDIUM = {
        {"aeroplane", "airoplank"}, {"banana", "bananana"}, {"chocolate", "choklat"},
        {"drainage", "dryinage"}, {"emotion", "eemotion"}, {"friendly", "freindly"},
        {"gorilla", "guerialla"}, {"helicopter", "helecopter"}, {"international", "enternational"},
        {"justice", "jushtise"}, {"kitten", "keten"}, {"leisure", "liesur"}, {"measure", "mesur"},
        {"numeric", "numaric"}, {"octopus", "octupos"}, {"position", "pozision"}, {"quest", "qoest"},
        {"request", "requeast"}, {"scrabble", "scrable"}, {"tangent", "tanegent"}, {"umbrella", "umbreala"},
        {"vocalize", "vocalyse"}, {"watermelon", "watermellon"}, {"xylophone", "sylofone"},
        {"yacht", "yatch"}, {"zebra", "zebera"}
    };

    public static final String[][] WORDS_HARD = {
        {"aircraft", "aercraft"}, {"bridge", "brige"}, {"contagious", "contageos"},
        {"drenched", "dranched"}, {"equator", "equador"}, {"fabulous", "fabulus"},
        {"grasshopper", "grasshoper"}, {"hitchhiker", "hickhiker"}, {"insidious", "incedious"},
        {"judiciary", "judishiary"}, {"knackered", "nackerd"}, {"license", "lisence"},
        {"metamorphosis", "metamorfosis"}, {"negotiate", "negoeteate"}, {"orangutan", "orangotaan"},
        {"parakeet", "parakit"}, {"quintessential", "queintessential "}, {"receive", "revieve"},
        {"signature", "signaure "}, {"trigonometry", "tregnometry"}, {"unveil", "unviel"},
        {"velocity", "velawcity"}, {"wrecker", "wraker"}, {"xerox", "xeros"},
        {"yosemite", "yosimete"}, {"zimbabwe", "zimbambawe"}
    };

    public static final String[][] SENTENCES = {
        {"You missed the flight.", "Your the flight missed."},
        {"Scrabble is a word game.", "A scrabble word is game."},
        {"Honesty is the best policy.", "The honesty is best policy."},
        {"Please close the door.", "Please close an door."},
        {"A bank account can have no money", "An back account can't be money."},
        {"Batman is a super hero.", "The betaman is an super man."},
        {"Return him his book.", "Return his him book."},
        {"Stars glow during the entire day.", "Stares don't glow the day entirely."},
        {"Absolutely I shall follow the right path.", "Absolutely I are follow the right path."},
        {"No man has ever crossed this land.", "No women her ever crossed this land."},
        {"An Apple a day keeps the doctor away.", "A apple an day keeps a doctor away."},
        {"Who am I?", "Who i am?"},
        {"What are you doing?", "What doing are you?"},
        {"I can drink water.", "I water can drink."},
        {"Charlie is a girl.", "Charlie girl a is."}
    };

    public static final String[][] COLORS = {
        {"Black", "#333333"},   {"Blue", "#2196F3"},
        {"Green", "#1DE986"},   {"Grey", "#454545"},
        {"Orange", "#FF9800"},  {"Pink", "#E91E63"},
        {"Purple,", "#9C27B0"}, {"Red", "#F44336"},
        {"White", "#FFFFFF"},   {"Yellow", "#FFEB3B"},
    };

    public static final String PLAYER1_SCORE = "PLAYER1_SCORE";
    public static final String PLAYER2_SCORE = "PLAYER2_SCORE";

    public static final int MAX_SCORE = 15;
    public static final int MAX_GAMES = 25;

    private int player1Score = 0, player2Score = 0, lastGame = 2, game_count = 0;
    private boolean isCorrect;

    private Context context;
    private Handler handler;
    private Runnable runnable;

    private Button p2b2, p1b1, p1b2, p2b1;
    private TextView p2text, p1text, p2t2, p2t1, p1t1, p1t2, p1s, p2s;
    private LinearLayout gameLayout2, gameLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        p2b2 = (Button) findViewById(R.id.p2b2);
        p2b1 = (Button) findViewById(R.id.p2b1);
        p2text = (TextView) findViewById(R.id.p2text);
        p1text = (TextView) findViewById(R.id.p1text);
        p2t2 = (TextView) findViewById(R.id.p2t2);
        p2t1 = (TextView) findViewById(R.id.p2t1);
        p1t1 = (TextView) findViewById(R.id.p1t1);
        p1t2 = (TextView) findViewById(R.id.p1t2);
        p1s = (TextView) findViewById(R.id.p1s);
        p2s = (TextView) findViewById(R.id.p2s);
        p1b1 = (Button) findViewById(R.id.p1b1);
        p1b2 = (Button) findViewById(R.id.p1b2);
        gameLayout1 = (LinearLayout) findViewById(R.id.gameLayout1);
        gameLayout2 = (LinearLayout) findViewById(R.id.gameLayout2);

        player2Score = 0;
        player1Score = 0;

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 5000);
                refreshGame();
            }
        };
        handler.postDelayed(runnable, 5000);
        refreshGame();
    }

    private void refreshGame() {
        game_count++;
        if (game_count > MAX_GAMES) {
            Intent intent = new Intent(context, FinaleActivity.class);
            intent.putExtra(PLAYER1_SCORE, player1Score);
            intent.putExtra(PLAYER2_SCORE, player2Score);
            context.startActivity(intent);
        }

        Random random = new Random(System.currentTimeMillis());
        int game;

        do {
            game = random.nextInt(3);
        } while (game == lastGame);

        lastGame = game;

        switch (game){
            case 0:
                // word-color
                String[] color1 = COLORS[random.nextInt(COLORS.length)];
                String[] color2;

                do  {
                    color2 = COLORS[random.nextInt(COLORS.length)];
                } while (color1[0].equals(color2[0]));

                p1t1.setText(color1[0]);
                p1t2.setText(color1[0]);
                p2t1.setText(color1[0]);
                p2t2.setText(color1[0]);

                isCorrect = random.nextBoolean();
                p1t1.setTextColor(isCorrect ? Color.parseColor(color1[1]) : Color.parseColor(color2[1]));
                p1t2.setTextColor(isCorrect ? Color.parseColor(color2[1]) : Color.parseColor(color1[1]));
                p2t1.setTextColor(isCorrect ? Color.parseColor(color1[1]) : Color.parseColor(color2[1]));
                p2t2.setTextColor(isCorrect ? Color.parseColor(color2[1]) : Color.parseColor(color1[1]));

                gameLayout1.setVisibility(View.GONE);
                gameLayout2.setVisibility(View.VISIBLE);
            break;

            case 1:
                // word spelling

                p1t1.setTextColor(getResources().getColor(R.color.black));
                p1t2.setTextColor(getResources().getColor(R.color.black));
                p2t1.setTextColor(getResources().getColor(R.color.black));
                p2t2.setTextColor(getResources().getColor(R.color.black));

                String[] word;
                if (player1Score < 5 || player2Score < 5)
                    word = WORDS_EASY[random.nextInt(WORDS_EASY.length)];
                else if (player1Score < 10 || player2Score < 10)
                    word = WORDS_MEDIUM[random.nextInt(WORDS_EASY.length)];
                else
                    word = WORDS_HARD[random.nextInt(WORDS_EASY.length)];

                // TODO: Use random boolean to jumble the 2 words
                // TODO: Save game id and answer globally
                isCorrect = random.nextBoolean();

                p1t1.setText(isCorrect ? word[0] : word[1]);
                p1t2.setText(isCorrect ? word[1] : word[0]);
                p2t1.setText(isCorrect ? word[0] : word[1]);
                p2t2.setText(isCorrect ? word[1] : word[0]);

                gameLayout1.setVisibility(View.GONE);
                gameLayout2.setVisibility(View.VISIBLE);
                break;

            case 2:
                gameLayout1.setVisibility(View.VISIBLE);
                gameLayout2.setVisibility(View.GONE);

                int sentence_index1 = random.nextInt(SENTENCES.length);
                int sentence_index2 = random.nextInt(SENTENCES[0].length);

                p1text.setText(SENTENCES[sentence_index1][sentence_index2]);
                p2text.setText(SENTENCES[sentence_index1][sentence_index2]);
                isCorrect = sentence_index2 == 0;
            break;
        }
    }

    public void onClick(View view) {
        // score update
        int id = view.getId();
        switch (id) {
            case R.id.p1b1:
                if (isCorrect)
                    player1Score++;
                else
                    player1Score--;
            break;

            case R.id.p1b2:
                if (!isCorrect)
                    player1Score++;
                else
                    player1Score--;
            break;

            case R.id.p2b1:
                if (isCorrect)
                    player2Score++;
                else
                    player2Score--;
            break;

            case R.id.p2b2:
                if (!isCorrect)
                    player2Score++;
                else
                    player2Score--;
            break;
        }

        p1s.setText(Integer.toString(player1Score));
        p2s.setText(Integer.toString(player2Score));

        if (player1Score == MAX_SCORE | player2Score == MAX_SCORE) {
            Intent intent = new Intent(context, FinaleActivity.class);
            intent.putExtra(PLAYER1_SCORE, player1Score);
            intent.putExtra(PLAYER2_SCORE, player2Score);
            context.startActivity(intent);
        }

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 0);
    }

}
