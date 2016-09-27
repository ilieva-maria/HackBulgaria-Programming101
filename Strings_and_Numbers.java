package application;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Strings_and_Numbers {
	public static void main(String[] args) {
		Strings_and_Numbers s = new Strings_and_Numbers();

		String str1 = "|?=xi^.k%x||^cs^s^=||x=x|.&=..|=x=|&kv^^jkt&jzx.xx=|&&!jkjs&kj|x>j.!..^&k..&k||o&s|s=j.xx!x)j=!&s&]n|^j.!jx",
			   str2 = "pr$pprtppp{%r%%#(;%rn$;~*s%r%r%;(#(x$p([~(~(r}%=([$[#[~[;~+rr~[r#(n([r%(n%b~;p#rp($;$[,l?(n~p#%$prn~%$r#(~$",
			   str3 = "}w#\\a:\\?uxv?xvxx@axx?\\u\\^:a~wx?x-:u\\v\\a:???^xv?x??cwwx_?uhvc:w<v,:ucwzuaw::uaucwaa^ra:;?:\\?xbw[^^:w::ca\\wcvl\\:%",
			   str4 = "bbcccddddeeeeeffffffggggggghhhhhhhhiiiiiiiiijjjjjjjjjja";

		s.decrypt(str1);
		s.decrypt(str2);
		s.decrypt(str3);
		s.decrypt(str4);
	}

	public static Map<Character, Integer> charsHistogram(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) {
			Integer count = map.get(c);
			map.put(c, count == null ? 1 : count + 1);
		}
		return sortByValue(map);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public BigInteger decrypt(String s) {
		System.out.println("s = " + s);
		Map<Character, Integer> map = charsHistogram(s);
		int count = 0;
		char[] symbols = new char[10];
		Iterator<Map.Entry<Character, Integer>> entries = map.entrySet().iterator();
		while (entries.hasNext() && count < 10) {
			Map.Entry<Character, Integer> entry = entries.next();
			symbols[count] = entry.getKey();
			count++;
		}

		String key = String.valueOf(symbols);
		int index = 0;
		StringBuilder out = new StringBuilder(s.length());
		for (char c : s.toCharArray()) {
			index = key.indexOf(c);
			if (index >= 0) {
				out.append(9 - index);
			} else {
				out.append(c);
			}
		}

		String decoded = out.toString();
		System.out.println("decoded = " + decoded);

		StringBuilder result = new StringBuilder("result = ");

		String[] numbers = decoded.replaceAll("\\D+", " ").split(" ");

		BigInteger sum = BigInteger.ZERO;
		boolean isFirst = true;
		for (String num : numbers) {
			if (isFirst) {
				result.append(num);
				isFirst = false;
			} else {
				result.append(" + " + num);
			}
			if (num.length() > 0) {
				BigInteger number = new BigInteger(num);
				sum = sum.add(number);
			}

		}
		result.append(" = " + sum);
		System.out.println(result.toString() + "\n");
		return sum;
	}
}

