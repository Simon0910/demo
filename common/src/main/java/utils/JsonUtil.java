package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class JsonUtil {

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }


    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }


    public static List<Map<String, Object>> getListByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return parseJSON2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String, Object> getMapByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return parseJSON2Map(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //test
    public static void main(String[] args) {
        String url = "{\n" +
                "  \"responseId\": \"38de3511-4e61-4eae-9db1-5424b182bb25\",\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"source\": \"MDES\",\n" +
                "      \"reasonCode\": \"INVALID_PAYMENT_APP_INSTANCE_ID\",\n" +
                "      \"errorCode\": \"INVALID_PAYMENT_APP_INSTANCE_ID\",\n" +
                "      \"errorDescription\": \"Invalid Payment App Instance ID\",\n" +
                "      \"description\": \"Invalid Payment App Instance ID\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Map<String, Object> map = parseJSON2Map(url);
        System.out.println(map);
    }
}