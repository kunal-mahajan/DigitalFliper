package digitalfliper.com.digitalfliper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Utils {


    public static void flip(final RelativeLayout rlTime, Context context, int num, int numPrev) {
        int upperBackId = R.id.up_back;
        final ImageView upBack = (ImageView) rlTime.findViewById(upperBackId);
        final ImageView up = (ImageView) rlTime.findViewById(R.id.up);
        String txt = num < 10 ? "0" + num : "" + num;
        String txtPrev = (numPrev) < 10 ? "0" + (numPrev) : "" + (numPrev);
        up.setImageBitmap(textAsBitmap(context, txtPrev, Color.BLACK, true));
        up.setVisibility(View.INVISIBLE);

        final ImageView down = (ImageView) rlTime.findViewById(R.id.down);
        down.setVisibility(View.INVISIBLE);

        upBack.setImageBitmap(textAsBitmap(context, txt, Color.BLACK, true));
        down.setImageBitmap(textAsBitmap(context, txt, Color.BLACK, false));

        Animation anim = new ScaleAnimation(1f, 1f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        anim.setDuration(300);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim = new ScaleAnimation(1f, 1f, 0f, 1f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f);
                anim.setDuration(100);
                anim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        ImageView back_down = (ImageView) rlTime.findViewById(R.id.down_back);
                        back_down.setImageDrawable(down.getDrawable());

                    }
                });
                down.startAnimation(anim);

            }
        });
        up.startAnimation(anim);

    }


    private static Bitmap textAsBitmap(Context context, String text, int textColor, boolean top) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.time_bg);
        bm = bm.copy(Bitmap.Config.ARGB_8888, true);
        TextPaint textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
        float textSize = (float) (bm.getHeight() * .45);
        textPaint.setTextSize(textSize);
        float textHeight = textPaint.descent() - textPaint.ascent();
        float textOffset = (textHeight / 2) - textPaint.descent();
        RectF bounds = new RectF(0, 0, bm.getWidth(), bm.getHeight());
        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, bounds.centerX(), bounds.centerY() + textOffset, textPaint);
        Bitmap cropedBitmap = Bitmap.createBitmap(bm, 0, top ? 0 : bm.getHeight() / 2, bm.getWidth(), bm.getHeight() / 2);
        return cropedBitmap;
    }


}
