package com.slogan.wristband.wristband.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import com.slogan.wristband.wristband.app.WristbandApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static  boolean sdCardExist() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

//    public static File saveBitmapToSdCard(Bitmap bitmap){
//        if (!sdCardExist()) {
//            return null;
//        }
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(Environment.getExternalStorageDirectory().getPath());
//        stringBuffer.append(File.separator);
//        stringBuffer.append(Configs.FILE_PATH_PRE);
//        stringBuffer.append(File.separator);
//        stringBuffer.append("crop_img");
//        File dirFile = new File(stringBuffer.toString());
//        if (!dirFile.exists()) {
//            dirFile.mkdirs();
//        }else{
//            deleteDirToContent(dirFile);
//        }
//        File file = new File(dirFile, System.currentTimeMillis()+".jpg");
//        FileOutputStream fileOutputStream = null;
//        try{
//            fileOutputStream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
//        }catch (Exception e){
//            WristbandApplication.getInstance().showToast("未知错误");
//            return null;
//        }
//        try{
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        }catch (IOException e){
//
//        }
//        return file;
//    }
    public static void deleteDirToContent(File file){
        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
        }
    }
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }
}
