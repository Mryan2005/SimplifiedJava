package translate;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class NewJSONObject extends JSONObject {
    public NewJSONObject() {
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
