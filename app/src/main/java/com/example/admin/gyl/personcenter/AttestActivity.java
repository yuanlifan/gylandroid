package com.example.admin.gyl.personcenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.View;

import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.http.MyCallBack;
import com.example.admin.gyl.http.model.BaseModel;
import com.example.admin.gyl.personcenter.fragment.AttestFirstFragment;
import com.example.admin.gyl.personcenter.fragment.AttestSecondFragment;
import com.example.admin.gyl.utils.SettingManager;
import com.example.admin.gyl.utils.Util;
import com.ylfcf.gyl.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;

public class AttestActivity extends BaseActivity {

    private int                     currentPosition = 0;
    private ArrayList<BaseFragment> fragments       = new ArrayList<>();
    private BaseFragment mAttestFirstFragment;
    private BaseFragment mAttestSecondFragment;
    public int currentPosition1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attest);
    }

    @Override
    public void initView() {
        initFragments();
        changeFragment(0);
    }

    @Override
    public void onViewClick(View v) {

    }

    /**
     * 初始化集合
     */
    private void initFragments() {
        fragments.add(mAttestFirstFragment);
        fragments.add(mAttestSecondFragment);
    }

    /**
     * 切换Fragment
     */
    public void changeFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        hideFragment(transaction);
        if (position >= fragments.size() || fragments.get(position) == null) {
            BaseFragment fragment = getFragment(position);
            fragments.set(position, fragment);
            transaction.add(R.id.vp_main, fragment).commitAllowingStateLoss();
        } else {
            Fragment fragment = fragments.get(position);
            transaction.show(fragment).commitAllowingStateLoss();
            currentPosition = position;
        }
    }

    /**
     * 根据点击获取显示的Fragment
     */
    private BaseFragment getFragment(int position) {
        BaseFragment fragment = null;
        if (position == 0) {
            mAttestFirstFragment = new AttestFirstFragment();
            fragment = mAttestFirstFragment;
        } else if (position == 1) {
            mAttestSecondFragment = new AttestSecondFragment();
            fragment = mAttestSecondFragment;
        }
        return fragment;
    }

    /**
     * 隐藏Fragment
     */
    private void hideFragment(FragmentTransaction transaction) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
//            showImage(imagePath);
            if(getFragment(1) != null) {
                ((AttestSecondFragment) fragments.get(1)).showImage(imagePath, currentPosition1);
            }
            c.close();
        }else if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getData() != null || data.getExtras() != null) { // 防止没有返回结果
//                Uri uri = data.getData();
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                String s = saveBitmap(AttestActivity.this, bm);
                if(getFragment(1) != null) {
                ((AttestSecondFragment) fragments.get(1)).showImage(s, currentPosition1);
            }
        }
    }else if(requestCode == 3 && resultCode == Activity.RESULT_OK && data != null) {
//            String path = data.getData().getPath();
            String path = getPath(AttestActivity.this, data.getData());
            if (getFragment(1) != null) {
                ((AttestSecondFragment) fragments.get(1)).showImage(path, currentPosition1);
        }
    }
}

    private  String co_name;//企业名称
    private  String legal_person;//企业法人
    private  String yyzz_code;//营业执照号
    private  String khxk_code;//开户许可证号
    private  String bank_name;//收款银行
    private  String bank_card;//收款银行账号
    private  String jgxy_code;//机构信用代码证号

    public void setFormbody(String co_name, String legal_person, String yyzz_code,
                            String khxk_code, String bank_name, String bank_card, String jgxy_code) {
        this.co_name = co_name;
        this.legal_person = legal_person;
        this.yyzz_code = yyzz_code;
        this.khxk_code = khxk_code;
        this.bank_name = bank_name;
        this.bank_card = bank_card;
        this.jgxy_code = jgxy_code;
    }

    public void postFormbody(List<String> photosListData) {
        if(mLoadingDialog != null){
            mLoadingDialog.show();
        }
        String userId = SettingManager.getUserId(AttestActivity.this);
        netHandler.postFormbody(userId, co_name, legal_person, yyzz_code,
                jgxy_code, khxk_code, bank_name, bank_card, Bitmap2StrByBase64(photosListData.get(0)),
                Bitmap2StrByBase64(photosListData.get(1)), Bitmap2StrByBase64(photosListData.get(2)),
                Bitmap2StrByBase64(photosListData.get(3)), getFileName(photosListData.get(0)), getFileName(photosListData.get(1)),
                getFileName(photosListData.get(2)), getFileName(photosListData.get(3)), new MyCallBack<BaseModel>() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mLoadingDialog.dismiss();
                        Util.toastLong(AttestActivity.this, "资料上传失败！");
                    }

                    @Override
                    public void onSuccess(Call call, BaseModel data) {
                        mLoadingDialog.dismiss();
                        if(data != null){
                            int resultCode = SettingManager.getResultCode(data);
                            if(resultCode == 0){
                                Util.toastLong(AttestActivity.this, "资料上传成功！");
                                AttestActivity.this.finish();
                            }
                        }
                    }
                });
    }

    public String getFileName(String pathandname){

        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return pathandname.substring(start+1,end);
        }else{
            return null;
        }

    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     */
    public String Bitmap2StrByBase64(String data){
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(new FileInputStream(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//第二个入参表示图片压缩率，如果是100就表示不压缩
        byte[] bytes=bos.toByteArray();
        String s = Base64.encodeToString(bytes, Base64.DEFAULT);
        return s;
    }

    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }


    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
