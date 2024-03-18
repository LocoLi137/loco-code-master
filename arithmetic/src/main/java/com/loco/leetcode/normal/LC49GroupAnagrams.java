package com.loco.leetcode.normal;

/**
 * @description:
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/3/6 19:44
 */

import java.security.Key;
import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class LC49GroupAnagrams {
    public static void main(String[] args) {
        LC49GroupAnagrams lc49GroupAnagrams = new LC49GroupAnagrams();
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = lc49GroupAnagrams.groupAnagrams(strings);
        System.out.println(lists);
    }

    /**
     * 排序，将各个字符串对字符排序后，放入哈希表，只要字母相同都被定为到一个桶下标，问题是不能包含相同字符串
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = Arrays.toString(charArray);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);

        }
        return new ArrayList<>(map.values());
    }

}
