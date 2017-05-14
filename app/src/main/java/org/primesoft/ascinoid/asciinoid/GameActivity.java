package org.primesoft.ascinoid.asciinoid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by user on 2017-05-14.
 */

public class GameActivity extends Activity {

    GameActivityLayout gameActivityLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivityLayoutView = new GameActivityLayout(this);
        setContentView(gameActivityLayoutView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameActivityLayoutView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameActivityLayoutView.pause();
    }
}
