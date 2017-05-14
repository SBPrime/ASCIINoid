package org.primesoft.ascinoid.asciinoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by user on 2017-05-14.
 */

public class GameActivityLayout extends SurfaceView implements Runnable {

    Thread thread = null;
    boolean CanDraw = false;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    int movingTgr_X;
    int movingTgr_Y;
    int movingDir_X;
    int movingDir_Y;

    public GameActivityLayout(Context context) {
        super(context);
        surfaceHolder = getHolder();

        movingDir_X = 1;
        movingDir_Y = 1;
        movingTgr_X = 10;
        movingTgr_Y = 10;
    }

    @Override
    public void run() {
        Paint whiteBrushSolid = new Paint();
        whiteBrushSolid.setColor(Color.WHITE);
        whiteBrushSolid.setStyle(Paint.Style.FILL);

        while(CanDraw)
        {
            if(!surfaceHolder.getSurface().isValid())
            {
                continue;
            }
            canvas = surfaceHolder.lockCanvas();
            moveCircle(50);
            canvas.drawColor(Color.BLACK);
            canvas.drawCircle(movingTgr_X,movingTgr_Y,10,whiteBrushSolid);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause()
    {
        CanDraw = false;
        while (true)
        {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }

    public void resume()
    {
        CanDraw = true;
        thread = new Thread(this);
        thread.start();
     }

    public void moveCircle(int speed)
    {
        if (movingTgr_X < speed)
        {
            movingDir_X = 1;
        }

        if (movingTgr_X > canvas.getWidth()-speed)
        {
            movingDir_X = -1;
        }

        if (movingTgr_Y < speed)
        {
            movingDir_Y = 1;
        }

        if (movingTgr_Y > canvas.getHeight()-speed)
        {
            movingDir_Y = -1;
        }

        movingTgr_Y += speed*movingDir_Y;
        movingTgr_X += speed*movingDir_X;
    }
}
