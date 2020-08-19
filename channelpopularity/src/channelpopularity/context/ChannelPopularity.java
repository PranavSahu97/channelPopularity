package channelpopularity.context;

import channelpopularity.operation.Operation;
import channelpopularity.util.FileProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelPopularity {
    FileProcessor file;
    Map<String, Integer> map = new HashMap<>();
    Map<String, List<Integer>> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    public ChannelPopularity(String input) throws IOException {
        file = new FileProcessor(input);
    }

    public void popularityCheck() throws IOException{
        String n = file.poll();
        while(n!= null) {
            if (n == null) {
                break;
            }

            String[] arr = n.split("::");
            if(arr[0].startsWith(Operation.ADD_VIDEO.toString())){
                if(!map.containsValue(arr[1])){
                    map.put(arr[1], 1);
                }
                else{
                    //throw error that video exists
                }
            }
            else if(arr[0].startsWith(Operation.METRICS.toString())){
                String[] s = arr[0].split("__");
                if(map.containsKey(s[1])){
                    String s1 = arr[1].substring(1, arr[1].length()-1);
                    String[] arr1 = s1.split(",");
                    List<Integer> list = new ArrayList<>();
                    for(String s2: arr1){
                        s2 = s2.replaceAll("[a-zA-Z=]+", "");
                        list.add(Integer.valueOf(s2));
                    }
                    map1.put(s[1], list);
                }
                else{
                    //throw error that video does not exist and metrics cannot be calculated
                }
            }

            else if(arr[0].startsWith(Operation.AD_REQUEST.toString())){
                String[] metrics = arr[0].split("__");
                if(map.containsKey(metrics[1])){
                    String m1 = arr[1];
                    m1 = m1.replaceAll("[a-zA-Z=]", "");
                    map2.put(metrics[1], Integer.valueOf(m1));
                }
            }

            else if(arr[0].startsWith(Operation.REMOVE_VIDEO.toString())){
                if(map.containsValue(arr[1])){
                    map.remove(arr[1]);
                }
                else{
                    //video does not exist already
                }
            }

            n = file.poll();
        } //end of while
    }



}
