package translate;

import com.alibaba.fastjson.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Trans {
    public Trans (String path) throws IOException, FileNotFoundException {
        RandomAccessFile rf = new RandomAccessFile(path, "r");
        StringBuilder content = new StringBuilder();
        while(rf.getFilePointer() < rf.length()) {
            content.append(rf.readLine());
        }
        rf.close();
        JSONObject object = JSONObject.parseObject(content.toString());
        System.out.println(object);
    }
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Trans trans = new Trans("test.json");
    }
}