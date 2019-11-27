package topics.datas_tructure.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

  public void concurrent() {

    Map<String, String> nonThreadSafeMap = new HashMap<>();
    /*
     * If multiple threads access a (linked) hash map concurrently,
     * and at least one of the threads modifies the map structurally,
     * it must be synchronized externally.
     *
     * This is typically accomplished by synchronizing on some object that naturally encapsulates the map.
     * If no such object exists, the map should be "wrapped" using the Collections.synchronizedMap method.
     * This is best done at creation time, to prevent accidental unsynchronized access to the map
     */

    Map<String, String> threadSafeMap = Collections.synchronizedMap(new HashMap<String, String>());

    // However it is not enough to make it fully thread-safe
    // you sill need to protect any iteration over the content of the map using the instance of the wrapped map as object's monitor

    Map m = Collections.synchronizedMap(threadSafeMap);
    Set s = m.keySet();  // Needn't be in synchronized block
    synchronized (m) {   // Synchronizing on m, not s!
      Iterator i = s.iterator(); // Must be in synchronized block
      while (i.hasNext()) {
        System.out.println(i.next());
      }
    }
  }

}
