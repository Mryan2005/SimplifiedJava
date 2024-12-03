package translate;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.util.LinkedHashMap;
import java.util.Map;

public class NewJSONObject {
    public Map<String, String> map;
    public NewJSONObject() {
        this.map = new LinkedHashMap<>();  //new HashMap();
    }
    public static NewJSONObject parseObject(String content, Feature orderedField) {
        NewJSONObject object = new NewJSONObject();
        JSONObject jsonObject = JSONObject.parseObject(content);
        for(String key : jsonObject.keySet()) {
            object.map.put(key, jsonObject.getString(key));
        }
        return object;
    }
}
