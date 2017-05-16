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

    Thread m_thread = null;
    boolean m_canDraw = false;
    Canvas m_canvas;
    SurfaceHolder m_surfaceHolder;

    int m_movingTgr_X;
    int m_movingTgr_Y;
    int m_movingDir_X;
    int m_movingDir_Y;

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
        Paint whiteBrushSolid = new Paint();
        whiteBrushSolid.setColor(Color.WHITE);
        whiteBrushSolid.setStyle(Paint.Style.FILL);

        while(m_canDraw)
        {
            if(!m_surfaceHolder.getSurface().isValid())
            {
                continue;
            }
            m_canvas = m_surfaceHolder.lockCanvas();
            moveCircle(50);
            m_canvas.drawColor(Color.BLACK);
            m_canvas.drawCircle(m_movingTgr_X,m_movingTgr_Y,10,whiteBrushSolid);
            m_surfaceHolder.unlockCanvasAndPost(m_canvas);
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
