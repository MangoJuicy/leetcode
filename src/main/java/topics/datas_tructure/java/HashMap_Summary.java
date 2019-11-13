package topics.datas_tructure.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMap_Summary {

  public void commonUse() {

    Map<String, String> map = new HashMap<>();

    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      String key = entry.getKey();
      String value = entry.getValue();
    }

    for (String key: map.keySet()) {
      String value = map.get(key);
    }

    for(Map.Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
    }
  }

}
