package labs.mamangkompii.jokenorris.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class QuotePatternDrawable extends Drawable {

    private final ColorScheme colorScheme;

    private Paint gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public QuotePatternDrawable() {
        this.colorScheme = ColorScheme.random();
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        gradientPaint.setShader(
                new LinearGradient(
                        0, bounds.top,
                        0, bounds.bottom,
                        colorScheme.colors, null, Shader.TileMode.CLAMP
                )
        );
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawPaint(gradientPaint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public enum ColorScheme {
        GREEN(new int[]{Color.parseColor("#b4ec51"), Color.parseColor("#429321")}),
        ORANGE(new int[]{Color.parseColor("#fad961"), Color.parseColor("#f76b1c")}),
        PURPLE(new int[]{Color.parseColor("#988eff"), Color.parseColor("#3b2fa3")}),
        ;

        private final int[] colors;

        ColorScheme(int[] colors) {
            this.colors = colors;
        }

        public static ColorScheme random() {
            Random random = new Random();
            int rand = random.nextInt(ColorScheme.values().length);
            return ColorScheme.values()[rand];
        }

        public int[] getColors() {
            return colors;
        }
    }
}
