package top.mryan2005.simplifiedjava;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class EasyJSONObject extends JSONObject {
    public EasyJSONObject() {
        super();
    }
    public static JSONObject parseObject(String text, boolean ordered) {
        if (ordered) {
            return JSONObject.parseObject(text, Feature.OrderedField);
        } else {
            return JSONObject.parseObject(text);
        }
    }
}
