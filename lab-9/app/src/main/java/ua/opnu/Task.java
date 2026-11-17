package ua.opnu;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i++) {
            list.remove(list.get(i).length() <= list.get(i + 1).length() ? i : i + 1);
        }
    }

    public void stutter(List<String> list) {
        for (int i = 0; i < list.size(); i += 2) {
            list.add(i, list.get(i));
        }
    }

    public void switchPairs(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            Collections.swap(list, i, i + 1);
        }
    }

    public void removeDuplicates(List<String> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).equals(list.get(i - 1))) {
                list.remove(i);
            }
        }
    }

    public void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i++, "****");
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int number : queue) {
            stack.push(number);
        }
        for (int number : queue) {
            if (number != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int number = queue.remove();
            if (number < 0) {
                stack.push(number);
            } else {
                queue.add(number);
            }
        }
        int positives = queue.size();
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        for (int i = 0; i < positives; i++) {
            queue.add(queue.remove());
        }
    }

    public void rearrange(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int number = queue.remove();
            if (number % 2 == 0) {
                queue.add(number);
            } else {
                stack.push(number);
            }
        }
        Iterator<Integer> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            queue.add(iterator.next());
        }
    }

    public int maxLength(Set<String> set) {
        int max = 0;
        for (String word : set) {
            max = Math.max(max, word.length());
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        set.removeIf(word -> word.length() % 2 == 0);
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> common = new HashSet<>(list1);
        common.retainAll(list2);
        return common.size();
    }

    public boolean isUnique(Map<String, String> map) {
        return new HashSet<>(map.values()).size() == map.size();
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey()) && entry.getValue().equals(map2.get(entry.getKey()))) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            result.merge(entry.getValue(), entry.getKey(), Math::max);
        }
        return result;
    }

    public int rarest(Map<String, Integer> map) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int number : map.values()) {
            counts.merge(number, 1, Integer::sum);
        }
        int result = 0;
        int minCount = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() < minCount
                    || (entry.getValue() == minCount && entry.getKey() < result)) {
                result = entry.getKey();
                minCount = entry.getValue();
            }
        }
        return result;
    }

    public int maxOccurrences(List<Integer> list) {
        Map<Integer, Integer> counts = new HashMap<>();
        int max = 0;
        for (int number : list) {
            max = Math.max(max, counts.merge(number, 1, Integer::sum));
        }
        return max;
    }

}
