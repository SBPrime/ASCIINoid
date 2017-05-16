package org.primesoft.ascinoid.asciinoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import org.primesoft.ascinoid.asciinoid.engine.IHiResTimer;
import org.primesoft.ascinoid.asciinoid.engine.implementation.HiResTimer;

import java.text.DecimalFormat;

/**
 * Created by user on 2017-05-14.
 */

public class GameActivityLayout extends SurfaceView implements Runnable {
    private final static DecimalFormat FORMAT_DOUBLE = new DecimalFormat("#.00");

    private IHiResTimer m_timer = new HiResTimer();

    private Thread m_thread = null;

    private boolean m_canDraw = false;
    private Canvas m_canvas;
    private SurfaceHolder m_surfaceHolder;

    private int m_movingTgr_X;
    private int m_movingTgr_Y;
    private int m_movingDir_X;
    private int m_movingDir_Y;

    public GameActivityLayout(Context context) {
        super(context);
        m_surfaceHolder = getHolder();

        m_movingDir_X = 1;
        m_movingDir_Y = 1;
        m_movingTgr_X = 10;
        m_movingTgr_Y = 10;
    }

    @Override
    public void run() {
        long fps = 0;

        Paint whiteBrushSolid = new Paint();
        whiteBrushSolid.setColor(Color.WHITE);
        whiteBrushSolid.setStyle(Paint.Style.FILL);

        m_timer.init();

        while(m_canDraw)
        {
            if(!m_surfaceHolder.getSurface().isValid())
            {
                continue;
            }
            m_canvas = m_surfaceHolder.lockCanvas();
            try {
                moveCircle(50);
                m_canvas.drawColor(Color.BLACK);
                m_canvas.drawCircle(m_movingTgr_X, m_movingTgr_Y, 10, whiteBrushSolid);

                // Limit the FPS to 30.
                m_timer.lockFPS(30);
            }
            finally {
                m_surfaceHolder.unlockCanvasAndPost(m_canvas);
                fps++;

                //Log the current FPS each 60 frames.
                if (fps % 60 == 0) {
                    Log.i("FPS", FORMAT_DOUBLE.format(m_timer.getFPS(fps)));

                    fps = 0;
                }
            }
        }
    }

    public void pause()
    {
        m_canDraw = false;
        while (true)
        {
            try {
                m_thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        m_thread = null;
    }

    public void resume()
    {
        m_canDraw = true;
        m_thread = new Thread(this);
        m_thread.start();
     }

    public void moveCircle(int speed)
    {
        if (m_movingTgr_X < speed)
        {
            m_movingDir_X = 1;
        }

        if (m_movingTgr_X > m_canvas.getWidth()-speed)
        {
            m_movingDir_X = -1;
        }

        if (m_movingTgr_Y < speed)
        {
            m_movingDir_Y = 1;
        }

        if (m_movingTgr_Y > m_canvas.getHeight()-speed)
        {
            m_movingDir_Y = -1;
        }

        m_movingTgr_Y += speed*m_movingDir_Y;
        m_movingTgr_X += speed*m_movingDir_X;
    }
}
