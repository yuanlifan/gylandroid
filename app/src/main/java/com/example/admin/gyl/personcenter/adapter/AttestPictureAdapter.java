package com.example.admin.gyl.personcenter.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.admin.gyl.personcenter.AttestActivity;
import com.ylfcf.gyl.R;

import java.util.List;

/**
 * Created by yjx on 2018/3/15.
 */

public class AttestPictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AttestPictureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        ImageView imageView = helper.getView(R.id.image);
        TextView tv_desc = helper.getView(R.id.tv_desc);
        TextView tv_look_picture = helper.getView(R.id.tv_look_picture);
        TextView tv_delete_picture = helper.getView(R.id.tv_delete_picture);
        LinearLayout ll_bottom = helper.getView(R.id.ll_bottom);
        FrameLayout fl_image = helper.getView(R.id.fl_image);
        final int pos = helper.getPosition();
        if(TextUtils.isEmpty(item)) {
            ll_bottom.setVisibility(View.INVISIBLE);
            tv_desc.setVisibility(View.VISIBLE);
            fl_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo 从下往上 弹出对话框：《相册 拍摄 文件夹》
                    showDialog(pos);
                }
            });
            Drawable drawableColor = new ColorDrawable(ContextCompat.getColor(mContext, R.color.common_color2));
            imageView.setImageDrawable(drawableColor);
        }else {
            ll_bottom.setVisibility(View.VISIBLE);
            tv_desc.setVisibility(View.GONE);
            fl_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //有图片时 ，显示大图片
                    showPopuWindow(item);
                }
            });
            Glide.with(mContext).load(item).into(imageView);
        }

        switch (pos) {
            case 0:
                tv_desc.setText("点击上传《营业执照》");
                break;
            case 1:
                tv_desc.setText("点击上传公司法人的身份证（正面）");
                break;
            case 2:
                tv_desc.setText("点击上传公司法人的身份证（反面）");
                break;
            case 3:
                tv_desc.setText("点击上传公司的银行开户许可证");
                break;
        }
    }

    private void showDialog(final int pos) {
        final Dialog dialog = new Dialog(mContext, R.style.dialog_style2);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        dialog.setContentView(R.layout.view_dialog_picture_bottom);
        LinearLayout llBottom1 = dialog.findViewById(R.id.ll_bottom_1);
        LinearLayout llBottom2 = dialog.findViewById(R.id.ll_bottom_2);
        LinearLayout llBottom3 = dialog.findViewById(R.id.ll_bottom_3);
        llBottom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 相册
                //调用相册
                Intent intent1 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((AttestActivity) mContext).currentPosition1 = pos;
                ((AttestActivity) mContext).startActivityForResult(intent1,1);
            }
        });
        llBottom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 拍摄
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
                ((AttestActivity) mContext).currentPosition1 = pos;
                ((AttestActivity) mContext).startActivityForResult(intent2,2);
            }
        });
        llBottom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.setCanceledOnTouchOutside(true);//点击对话框以外的部分，进行销毁
        dialog.show();
    }


    private void showPopuWindow(String item) {
        LayoutInflater inflater = ((AttestActivity) mContext).getLayoutInflater();
        LayoutInflater inflater2 = ((AttestActivity) mContext).getLayoutInflater();
        //得到界面视图
        View currean_View = inflater.inflate(R.layout.activity_attest, null);
        //得到要弹出的界面视图
        View view = inflater2.inflate(R.layout.picture_big, null);
        ImageView imageView = view.findViewById(R.id.image_big);
        Glide.with(mContext).load(item).into(imageView);
        WindowManager windowManager = ((AttestActivity) mContext).getWindowManager();
        int width = windowManager.getDefaultDisplay().getWidth();
        int heigth = windowManager.getDefaultDisplay().getHeight();
        Log.i("width", width+"");
        Log.i("height", heigth+"");
        PopupWindow popupWindow = new PopupWindow(view,(int)(width*0.8),(int)(heigth*0.5));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //显示在屏幕中央
        popupWindow.showAtLocation(currean_View, Gravity.CENTER, 0, 40);
        //popupWindow弹出后屏幕半透明
        BackgroudAlpha((float)0.5);
        //弹出窗口关闭事件
        popupWindow.setOnDismissListener(new popupwindowdismisslistener());
    }

    //设置屏幕背景透明度
    private void BackgroudAlpha(float alpha) {
        // TODO Auto-generated method stub
        WindowManager.LayoutParams l = ((AttestActivity) mContext).getWindow().getAttributes();
        l.alpha = alpha;
        ((AttestActivity) mContext).getWindow().setAttributes(l);
    }
    //点击其他部分popwindow消失时，屏幕恢复透明度
    class popupwindowdismisslistener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            BackgroudAlpha((float)1);
        }

    }

}
