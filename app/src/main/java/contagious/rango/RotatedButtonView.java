package contagious.rango;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class RotatedButtonView extends ImageButton {
    public RotatedButtonView(Context context) {
        super(context);
    }

    public RotatedButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotatedButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(180, canvas.getWidth() / 2, canvas.getHeight() / 2);
        super.onDraw(canvas);
        canvas.restore();
    }

}
