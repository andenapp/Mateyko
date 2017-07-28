package com.anden.mateyko.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.anden.mateyko.internal.Step;

/**
 * Created by ignacio on 19/07/17.
 */

public class MateykoView extends FrameLayout {

    static final int RING_DURATION = 250;
    static final int FOCUS_DURATION = 250;

    private Bitmap eraseBitmap;

    private Canvas eraseCanvas;

    private Paint clearPaint;

    private Paint firstRingPaint;

    private Paint secondRingPaint;

    private Step step;

    private long lastFrameTime;

    private long elapsedTime;

    private OnDissmisListener callback;

    public interface OnDissmisListener{
        void onDismiss(MateykoView view);
    }

    public MateykoView(@NonNull Context context) {
        super(context);

        init();
    }

    private void init() {

        setWillNotDraw(false);

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();

        eraseBitmap = Bitmap.createBitmap(dm.widthPixels, dm.heightPixels, Bitmap.Config.ARGB_8888);
        eraseBitmap.eraseColor(Color.TRANSPARENT);
        eraseCanvas = new Canvas(eraseBitmap);
        rectF = new RectF(0, 0, eraseCanvas.getWidth(), eraseCanvas.getHeight());

        clearPaint = new Paint();
        clearPaint.setAntiAlias(true);
        clearPaint.setColor(Color.TRANSPARENT);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        firstRingPaint = new Paint();
        firstRingPaint.setAntiAlias(true);
//        firstRingPaint.setStyle(Paint.Style.STROKE);
//        firstRingPaint.setStrokeWidth(1);
//        firstRingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        firstRingPaint.setColor(Color.parseColor("#e6FFFFFF"));   //color.RED

        secondRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondRingPaint.setAntiAlias(true);
//        secondRingPaint.setStyle(Paint.Style.STROKE);
//        secondRingPaint.setStrokeWidth(1);
//        secondRingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        secondRingPaint.setColor(Color.parseColor("#80FFFFFF"));   //color.RED

        shader = new LinearGradient(0, 0, eraseCanvas.getWidth(), eraseCanvas.getHeight(), Color.parseColor("#3495d5"), Color.argb(0, 52, 149, 213), Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setShader(shader);
    }

    RectF rectF;
    Shader shader;
    Paint paint;

    Drawable background;

    public void setBackground(int drawableResourceId){
        this.background = ContextCompat.getDrawable(getContext(), drawableResourceId);
        this.background.setBounds(0, 0, eraseCanvas.getWidth(), eraseCanvas.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        eraseBitmap.eraseColor(Color.TRANSPARENT);

        if(step == null){
            return;
        }

        elapsedTime += System.currentTimeMillis() - lastFrameTime;

        background.draw(eraseCanvas);

        if(elapsedTime < FOCUS_DURATION) {

            float f = Math.max(0, Math.min(1, elapsedTime / (float) FOCUS_DURATION));

            float radius = f * step.getRadius();
            eraseCanvas.drawCircle(step.getTargetCenter().x, step.getTargetCenter().y, radius, clearPaint);
        }else if(elapsedTime > FOCUS_DURATION && elapsedTime - FOCUS_DURATION < RING_DURATION){

            float f = Math.max(0, Math.min(1, (elapsedTime - FOCUS_DURATION) / (float) RING_DURATION));

            step.getFirstRing().draw(f, eraseCanvas, firstRingPaint);


            eraseCanvas.drawCircle(step.getTargetCenter().x, step.getTargetCenter().y, step.getRadius(), clearPaint);
        }else{
            float f = Math.max(0, Math.min(1, (elapsedTime - (FOCUS_DURATION + RING_DURATION)) / (float) RING_DURATION));

            step.getSecondRing().draw(f, eraseCanvas, secondRingPaint);

            step.getFirstRing().draw(1, eraseCanvas, firstRingPaint);

            eraseCanvas.drawCircle(step.getTargetCenter().x, step.getTargetCenter().y, step.getRadius(), clearPaint);
        }

        if(elapsedTime < (FOCUS_DURATION + RING_DURATION * 2)) {
            lastFrameTime = System.currentTimeMillis();
            ViewCompat.postInvalidateOnAnimation(this);
        }

        canvas.drawBitmap(eraseBitmap, 0, 0, null);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        start();
    }

    public void start(){
        lastFrameTime = System.currentTimeMillis();
        elapsedTime = 0;
        invalidate();
        invalidate();
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            KeyEvent.DispatcherState state = getKeyDispatcherState();
            if (state != null) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getRepeatCount() == 0) {
                    state.startTracking(event, this);
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP
                        && !event.isCanceled() && state.isTracking(event)) {
                    dismiss();
                    return true;
                }
            }
        }

        return super.dispatchKeyEventPreIme(event);
    }

    private void dismiss(){
        if(callback != null){
            callback.onDismiss(this);
        }
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public void setOnDissmisListener(OnDissmisListener callback) {
        this.callback = callback;
    }
}
