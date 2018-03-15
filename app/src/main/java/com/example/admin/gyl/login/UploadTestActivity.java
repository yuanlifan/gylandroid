package com.example.admin.gyl.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.gyl.base.BaseActivity;
import com.ylfcf.gyl.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/1.
 */

public class UploadTestActivity extends BaseActivity {
    /*
    *  从相册选择所得到的图像的保存路径
    */
    private Uri imageUri;

    /*
     *  从相册选择的照片的文件名
     */
    private String fileName;
    private final int PICK_FROM_ALBUN = 3;//从相册选择
    private final int CROP_PHOTO = 2;//切图操作
    private Button btn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_test_activity);
    }

    @Override
    public void initView() {
        btn = (Button) findViewById(R.id.upload_test_activity_btn);
        btn.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.upload_test_activity_img);
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.upload_test_activity_btn:
                pickPhotoFromAlbum(v);
                break;
        }
    }
    private void requestUploadFile(String userId,String file,String filename){
//        AsyncUpload task = new AsyncUpload(UploadTestActivity.this, userId, file, filename,
//                new Inter.OnCommonInter() {
//                    @Override
//                    public void back(BaseModel baseInfo) {
//                        if(baseInfo != null){
//                            Toast.makeText(UploadTestActivity.this,"上传成功"+baseInfo.getMsg(),Toast.LENGTH_LONG);
//                        }
//                    }
//                });
//        task.executeAsyncTask(ThreadUtils.FULL_TASK_EXECUTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "获取图片出现错误", Toast.LENGTH_SHORT).show();
        }
        else{
            switch(requestCode) {


            /*
             *  case PICK_FROM_ALBUM 代表从选择相册的intent返回之后
             *  完成从相册中选择照片后，就要将图片生成bitmap对象，然后显示在界面上了
             */
                case PICK_FROM_ALBUN:
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(data.getData()));
                        this.addPhotoToActivity(bitmap);
                    }catch (Exception e){

                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void addPhotoToActivity(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
        requestUploadFile("12",Bitmap2StrByBase64(bitmap),fileName+".jpg");
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     * @param bit
     * @return
     */
    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//第二个入参表示图片压缩率，如果是100就表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 点击按钮从手机相册中获取图片
     * @param view
     */
    public void pickPhotoFromAlbum(View view){
    /*
     *  用时间戳的方式来命名图片文件，这样可以避免文件名称重复
     */
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        this.fileName = "easyassetFromAlbum"+format.format(date);

    /*
     *  创建一个File对象，用于存放选到的照片
     */
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File outputImage = new File(path,this.fileName+".jpg");

    /*
     *  以防万一，看一下这个文件是不是存在，如果存在的话，先删除掉
     */
        if(outputImage.exists()){
            outputImage.delete();
        }

        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    /*
     *  将File对象转换为Uri对象，以便拍照后保存
     */
        this.imageUri = Uri.fromFile(outputImage);

    /*
     *  启动系统的选择界面
     */
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.putExtra("scale", true);//设置可以缩放
//        intent.putExtra("crop", true);//设置可以裁剪
        intent.setType("image/*");//设置需要从系统选择的内容：图片
        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.imageUri);//设置输出位置
        startActivityForResult(intent, this.PICK_FROM_ALBUN);//开始选择
    }

}
