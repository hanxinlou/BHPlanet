package com.example.a12525.bhplanet;

import java.io.InputStream;
import java.lang.reflect.Field;
import com.example.a12525.bhplanet.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * PowerImageView鏄竴涓粡杩囨墿灞曠殑ImageView锛屽畠涓嶄粎缁ф壙浜咺mageView鍘熺敓鐨勬墍鏈夊姛鑳斤紝杩樺姞鍏ヤ簡鎾斁GIF鍔ㄧ敾鐨勫姛鑳姐€?
 *
 * @author guolin
 */
public class PowerImageView extends android.support.v7.widget.AppCompatImageView implements OnClickListener {

    /**
     * 鎾斁GIF鍔ㄧ敾鐨勫叧閿被
     */
    private Movie mMovie;

    /**
     * 寮€濮嬫挱鏀炬寜閽浘鐗?
     */
    private Bitmap mStartButton;

    /**
     * 璁板綍鍔ㄧ敾寮€濮嬬殑鏃堕棿
     */
    private long mMovieStart;

    /**
     * GIF鍥剧墖鐨勫搴?
     */
    private int mImageWidth;

    /**
     * GIF鍥剧墖鐨勯珮搴?
     */
    private int mImageHeight;

    /**
     * 鍥剧墖鏄惁姝ｅ湪鎾斁
     */
    private boolean isPlaying;

    /**
     * 鏄惁鍏佽鑷姩鎾斁
     */
    private boolean isAutoPlay;

    /**
     * PowerImageView鏋勯€犲嚱鏁般€?
     *
     * @param context
     */
    public PowerImageView(Context context) {
        super(context);
    }

    /**
     * PowerImageView鏋勯€犲嚱鏁般€?
     *
     * @param context
     */
    public PowerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * PowerImageView鏋勯€犲嚱鏁帮紝鍦ㄨ繖閲屽畬鎴愭墍鏈夊繀瑕佺殑鍒濆鍖栨搷浣溿€?
     *
     * @param context
     */
    public PowerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PowerImageView);
        int resourceId = getResourceId(a, context, attrs);
        if (resourceId != 0) {
            // 褰撹祫婧恑d涓嶇瓑浜?鏃讹紝灏卞幓鑾峰彇璇ヨ祫婧愮殑娴?
            InputStream is = getResources().openRawResource(resourceId);
            // 浣跨敤Movie绫诲娴佽繘琛岃В鐮?
            mMovie = Movie.decodeStream(is);
            if (mMovie != null) {
                // 濡傛灉杩斿洖鍊间笉绛変簬null锛屽氨璇存槑杩欐槸涓€涓狦IF鍥剧墖锛屼笅闈㈣幏鍙栨槸鍚﹁嚜鍔ㄦ挱鏀剧殑灞炴€?
                isAutoPlay = a.getBoolean(R.styleable.PowerImageView_auto_play, false);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                mImageWidth = bitmap.getWidth();
                mImageHeight = bitmap.getHeight();
                bitmap.recycle();
                if (!isAutoPlay) {
                    // 褰撲笉鍏佽鑷姩鎾斁鐨勬椂鍊欙紝寰楀埌寮€濮嬫挱鏀炬寜閽殑鍥剧墖锛屽苟娉ㄥ唽鐐瑰嚮浜嬩欢
                    mStartButton = BitmapFactory.decodeResource(getResources(),
                            R.drawable.start_play);
                    setOnClickListener(this);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == getId()) {
            // 褰撶敤鎴风偣鍑诲浘鐗囨椂锛屽紑濮嬫挱鏀綠IF鍔ㄧ敾
            isPlaying = true;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie == null) {
            // mMovie绛変簬null锛岃鏄庢槸寮犳櫘閫氱殑鍥剧墖锛屽垯鐩存帴璋冪敤鐖剁被鐨刼nDraw()鏂规硶
            super.onDraw(canvas);
        } else {
            // mMovie涓嶇瓑浜巒ull锛岃鏄庢槸寮燝IF鍥剧墖
            if (isAutoPlay) {
                // 濡傛灉鍏佽鑷姩鎾斁锛屽氨璋冪敤playMovie()鏂规硶鎾斁GIF鍔ㄧ敾
                playMovie(canvas);
                invalidate();
            } else {
                // 涓嶅厑璁歌嚜鍔ㄦ挱鏀炬椂锛屽垽鏂綋鍓嶅浘鐗囨槸鍚︽鍦ㄦ挱鏀?
                if (isPlaying) {
                    // 姝ｅ湪鎾斁灏辩户缁皟鐢╬layMovie()鏂规硶锛屼竴鐩村埌鍔ㄧ敾鎾斁缁撴潫涓烘
                    if (playMovie(canvas)) {
                        isPlaying = false;
                    }
                    invalidate();
                } else {
                    // 杩樻病寮€濮嬫挱鏀惧氨鍙粯鍒禛IF鍥剧墖鐨勭涓€甯э紝骞剁粯鍒朵竴涓紑濮嬫寜閽?
                    mMovie.setTime(0);
                    mMovie.draw(canvas, 0, 0);
                    int offsetW = (mImageWidth - mStartButton.getWidth()) / 2;
                    int offsetH = (mImageHeight - mStartButton.getHeight()) / 2;
                    canvas.drawBitmap(mStartButton, offsetW, offsetH, null);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMovie != null) {
            // 濡傛灉鏄疓IF鍥剧墖鍒欓噸鍐欒瀹歅owerImageView鐨勫ぇ灏?
            setMeasuredDimension(mImageWidth, mImageHeight);
        }
    }

    /**
     * 寮€濮嬫挱鏀綠IF鍔ㄧ敾锛屾挱鏀惧畬鎴愯繑鍥瀟rue锛屾湭瀹屾垚杩斿洖false銆?
     *
     * @param canvas
     * @return 鎾斁瀹屾垚杩斿洖true锛屾湭瀹屾垚杩斿洖false銆?
     */
    private boolean playMovie(Canvas canvas) {
        long now = SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        int duration = mMovie.duration();
        if (duration == 0) {
            duration = 1000;
        }
        int relTime = (int) ((now - mMovieStart) % duration);
        mMovie.setTime(relTime);
        mMovie.draw(canvas, 0, 0);
        if ((now - mMovieStart) >= duration) {
            mMovieStart = 0;
            return true;
        }
        return false;
    }

    /**
     * 閫氳繃Java鍙嶅皠锛岃幏鍙栧埌src鎸囧畾鍥剧墖璧勬簮鎵€瀵瑰簲鐨刬d銆?
     *
     * @param a
     * @param context
     * @param attrs
     * @return 杩斿洖甯冨眬鏂囦欢涓寚瀹氬浘鐗囪祫婧愭墍瀵瑰簲鐨刬d锛屾病鏈夋寚瀹氫换浣曞浘鐗囪祫婧愬氨杩斿洖0銆?
     */
    private int getResourceId(TypedArray a, Context context, AttributeSet attrs) {
        try {
            Field field = TypedArray.class.getDeclaredField("mValue");
            field.setAccessible(true);
            TypedValue typedValueObject = (TypedValue) field.get(a);
            return typedValueObject.resourceId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        return 0;
    }

}
