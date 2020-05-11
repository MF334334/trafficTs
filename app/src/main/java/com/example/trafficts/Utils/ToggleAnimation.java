package com.example.trafficts.Utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.FloatRange;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class ToggleAnimation {
//    float STIFFNESS = SpringForce.STIFFNESS_MEDIUM;//硬
//    float STIFFNESS = 200f;//硬度 自己修改 越小越软
    private float STIFFNESS;
    float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;//阻尼

    androidx.dynamicanimation.animation.SpringAnimation xAnimation;//x方向
    androidx.dynamicanimation.animation.SpringAnimation yAnimation;//y方向

    View movingView;//图片

    float dX = 0f;
    float dY = 0f;

    public ToggleAnimation(View movingView, float STIFFNESS) {
        this.STIFFNESS = STIFFNESS;
        this.movingView = movingView;
    }

    public  void init() {
        // 以图片的初始位置创建动画对象
        movingView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xAnimation = createSpringAnimation(
                        movingView, androidx.dynamicanimation.animation.SpringAnimation.X, movingView.getX(), STIFFNESS, DAMPING_RATIO);
                yAnimation = createSpringAnimation(
                        movingView, androidx.dynamicanimation.animation.SpringAnimation.Y, movingView.getY(), STIFFNESS, DAMPING_RATIO);
                //初始位置确定，移除监听
                movingView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void touch(){
        movingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        // 计算到左上角的距离
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();

                        // 取消动画以便按住图片
                        xAnimation.cancel();
                        yAnimation.cancel();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 另一种改变View的LayoutParams（位置）的方式
                        movingView.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP:
                        xAnimation.start();
                        yAnimation.start();
                        break;
                }
                return true;
            }
        });

    }

    /**
     * 创建弹性动画
     * @param view 动画关联的控件
     * @param property 动画作用的属性
     * @param finalPosition 动画结束的位置
     * @param stiffness 硬度
     * @param dampingRatio 阻尼
     * @return
     */
    androidx.dynamicanimation.animation.SpringAnimation createSpringAnimation(View view,
                                                                              DynamicAnimation.ViewProperty property,
                                                                              Float finalPosition,
                                                                              @FloatRange(from = 0.0) Float stiffness,
                                                                              @FloatRange(from = 0.0) Float dampingRatio) {
        //创建弹性动画类SpringAnimation
        androidx.dynamicanimation.animation.SpringAnimation animation = new androidx.dynamicanimation.animation.SpringAnimation(view, property);
        //SpringForce类，定义弹性特质
        SpringForce spring = new SpringForce(finalPosition);
        spring.setStiffness(stiffness);
        spring.setDampingRatio(dampingRatio);
        //关联弹性特质
        animation.setSpring(spring);
        return animation;
    }

}







