package topics.datas_tructure.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Constructor of LinkedHashMap:
 *
 * LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
 * accessOrder - the ordering mode - true for access-order, false for insertion-order
 *
 * LinkedHashMap()
 * Constructs an empty insertion-ordered LinkedHashMap instance
 * with the default initial capacity (16) and load factor (0.75).
 */
public class LRUCacheWithLinkedHashMap {

  private Map<Integer, Integer> map;
  private final int CAPACITY;

  public LRUCacheWithLinkedHashMap(int capacity) {
    this.CAPACITY = capacity;
    map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true){
      @Override     // anonymous sub class to override method deciding whether to remove eldest entry
      protected boolean removeEldestEntry(Map.Entry eldestEntry) {
        return size() > CAPACITY;
      }
    };
  }

  public int get(int key) {
    return map.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    map.put(key, value);
  }
}
