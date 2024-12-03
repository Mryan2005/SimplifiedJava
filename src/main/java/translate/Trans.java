package translate;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;

public class Trans {
    public JSONObject object;
    public Trans (String path) throws IOException, FileNotFoundException {
        RandomAccessFile rf = new RandomAccessFile(path, "r");
        StringBuilder content = new StringBuilder();
        while(rf.getFilePointer() < rf.length()) {
            content.append(rf.readLine());
        }
        rf.close();
        object = NewJSONObject.parseObject(content.toString(), true);
    }

    public String tr(String key) {
        return object.getString(key);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Trans trans = new Trans("in.json");
    }
}