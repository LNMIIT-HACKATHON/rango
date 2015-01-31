package contagious.rango;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class RotatedTextView extends TextView {
    public RotatedTextView(Context context) {
        super(context);
    }

    public RotatedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotatedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public void draw(Canvas canvas) {
//        canvas.save();
//        canvas.rotate(180);
//        super.onDraw(canvas);
//        canvas.restore();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(180, canvas.getWidth() / 2, canvas.getHeight() / 2);
        super.onDraw(canvas);
        canvas.restore();
    }

}
