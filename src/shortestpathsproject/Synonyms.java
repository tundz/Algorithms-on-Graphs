package shortestpathsproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Synonyms {
    
     
    public static List<String> findNameCount(List<String> Names, List<List<String>> Synonyms) {
        Map<String, Integer> nameFreq = deconstructNameFreq(Names);
        List<Set<String>> synonymSets = deconstructSynonyms(Synonyms);
        Map<String, Integer> result = getRealNameFreq(nameFreq, synonymSets);
        List<String> output = new ArrayList<>();
        for (String name: result.keySet()) {
            if (result.get(name) != 0)
                output.add("" + name + "(" + result.get(name) + ")");
        }
        return output;
    }
   
    private static Map<String, Integer> deconstructNameFreq(List<String> nameAndFreq) {
        Map<String, Integer> nameFreq = new HashMap<>();
        for (String info: nameAndFreq) {
            int i = info.indexOf('(');
            int l = info.indexOf(')');
            int freq = Integer.parseInt(info.substring(i+1, l));
            int k = lastCharIndex(info);
            String name = info.substring(0, k);
            nameFreq.put(name, freq);
        }
        return nameFreq;
    }
    private static int lastCharIndex(String info){
        for(int i = 0; i < info.length(); i++)
            if (!Character.isLetter(info.charAt(i)))
                return i;
        return 0;
    }
    private static List<Set<String>> deconstructSynonyms(List<List<String>> synonymPairs) {
        List<Set<String>> list = new ArrayList<>();
        for (List<String> pair: synonymPairs) {
            boolean exists = false;
            for (Set<String> set : list) {
                if (set.contains(pair.get(0)) || set.contains(pair.get(1))) {
                    set.add(pair.get(1));
                    set.add(pair.get(0));
                    exists = true;
                }  
            }
            if(!exists) {
                Set<String> set = new HashSet<>();
                set.add(pair.get(0)); set.add(pair.get(1));
                list.add(set);
            }
        }
        return list;
    }
    
    private static Map<String, Integer> getRealNameFreq(Map<String, Integer> nameFreq, List<Set<String>> synonymSets) {
        Map<String, Integer> result = new HashMap<>();
        for (Set<String> set: synonymSets) {
            int sum = 0;
            String x = "";
            for (String name: set) {
                if (nameFreq.containsKey(name))
                    sum += nameFreq.get(name);
                x = name;
            }
            result.put(x, sum);
        }
        return result;
    }
    
    public static void main (String[] argv) {
        List<String> names = new ArrayList<>();
        names.add("Sue(15)"); names.add("Susan(12)"); names.add("Chris(13)");
        names.add("Kris(4)"); names.add("Christopher(19)");
        List<String> pair1 = new ArrayList<>();
        pair1.add("Sue"); pair1.add("Susan");
        List<String> pair2 = new ArrayList<>();
        pair2.add("John"); pair2.add("Johnny");
        List<String> pair3 = new ArrayList<>();
        pair3.add("Chris"); pair3.add("Kris");
        List<String> pair4 = new ArrayList<>();
        pair4.add("Chris"); pair4.add("Christopher");
        List<List<String>> lists = new ArrayList<>();
        lists.add(pair1); lists.add(pair2); lists.add(pair3); lists.add(pair4);
        System.out.println(findNameCount(names, lists));
    }
    
}
