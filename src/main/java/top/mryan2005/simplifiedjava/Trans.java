package top.mryan2005.simplifiedjava;

import com.alibaba.fastjson.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Trans {
    public JSONObject object;
    public Trans (String path) throws IOException, FileNotFoundException {
        RandomAccessFile rf = new RandomAccessFile(path, "r");
        StringBuilder content = new StringBuilder();
        while(rf.getFilePointer() < rf.length()) {
            content.append(rf.readLine());
        }
        rf.close();
        object = EasyJSONObject.parseObject(content.toString(), true);
    }

    public String tr(String key) {
        return object.getString(key);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Trans trans = new Trans("in.json");
    }
}