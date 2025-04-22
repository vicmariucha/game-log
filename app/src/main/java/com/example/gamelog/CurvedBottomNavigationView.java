package com.example.gamelog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CurvedBottomNavigationView extends BottomNavigationView {

    private Path path;
    private Paint paint;

    private final int CURVE_CIRCLE_RADIUS = 128;

    // Coordinates of the first curve
    private int firstCurveStartPointX;
    private int firstCurveStartPointY;
    private int firstCurveEndPointX;
    private int firstCurveEndPointY;
    private int firstCurveControlPoint1X;
    private int firstCurveControlPoint1Y;
    private int firstCurveControlPoint2X;
    private int firstCurveControlPoint2Y;

    // Coordinates of the second curve
    private int secondCurveStartPointX;
    private int secondCurveStartPointY;
    private int secondCurveEndPointX;
    private int secondCurveEndPointY;
    private int secondCurveControlPoint1X;
    private int secondCurveControlPoint1Y;
    private int secondCurveControlPoint2X;
    private int secondCurveControlPoint2Y;

    public CurvedBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(getResources().getColor(R.color.navbar_bg));
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int navigationBarWidth = getWidth();
        int navigationBarHeight = getHeight();

        // Start point (left side)
        firstCurveStartPointX = (navigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2);
        firstCurveStartPointY = 0;

        // End of first curve == start of second curve
        firstCurveEndPointX = navigationBarWidth / 2;
        firstCurveEndPointY = CURVE_CIRCLE_RADIUS;

        secondCurveStartPointX = firstCurveEndPointX;
        secondCurveStartPointY = CURVE_CIRCLE_RADIUS;

        // End of second curve
        secondCurveEndPointX = (navigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2);
        secondCurveEndPointY = 0;

        // Control points
        firstCurveControlPoint1X = firstCurveStartPointX + CURVE_CIRCLE_RADIUS;
        firstCurveControlPoint1Y = 0;

        firstCurveControlPoint2X = firstCurveEndPointX - CURVE_CIRCLE_RADIUS;
        firstCurveControlPoint2Y = CURVE_CIRCLE_RADIUS;

        secondCurveControlPoint1X = secondCurveStartPointX + CURVE_CIRCLE_RADIUS;
        secondCurveControlPoint1Y = CURVE_CIRCLE_RADIUS;

        secondCurveControlPoint2X = secondCurveEndPointX - CURVE_CIRCLE_RADIUS;
        secondCurveControlPoint2Y = 0;

        path.reset();
        path.moveTo(0, 0);
        path.lineTo(firstCurveStartPointX, firstCurveStartPointY);

        path.cubicTo(
                firstCurveControlPoint1X, firstCurveControlPoint1Y,
                firstCurveControlPoint2X, firstCurveControlPoint2Y,
                firstCurveEndPointX, firstCurveEndPointY
        );

        path.cubicTo(
                secondCurveControlPoint1X, secondCurveControlPoint1Y,
                secondCurveControlPoint2X, secondCurveControlPoint2Y,
                secondCurveEndPointX, secondCurveEndPointY
        );

        path.lineTo(navigationBarWidth, 0);
        path.lineTo(navigationBarWidth, navigationBarHeight);
        path.lineTo(0, navigationBarHeight);
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
