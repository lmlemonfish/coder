package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串处理工具类
 * @author ma
 *
 */
public class StringUtils {
	
	/**
	 * 判断字符串为不为空或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str != null && !"".equals(str.trim())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 默认切割字符串的方式 以逗号和空格为切割符号
	 * @param str
	 * @return
	 */
	public static String[] defaultSplit(String str) {
		List<String> strList = new ArrayList<>();
		
		if (str.trim().contains(" ")) {
			String[] strs = str.split(" ");
			
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].contains(",")) { 
					String[] split = strs[i].split(",");
					
					for (int j = 0; j < split.length; j++) {
						strList.add(split[j]);
					}
					
				} else {
					strList.add(strs[i]);
					
				}
				
			}
		} else if (str.trim().contains(",")) {
			String[] strs = str.split(",");
			
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].contains(" ")) { 
					String[] split = strs[i].split(" ");
					
					for (int j = 0; j < split.length; j++) {
						strList.add(split[j]);
					}
					
				} else {
					strList.add(strs[i]);
					
				}
				
			}
		} else {
			strList.add(str);
		}
		
		String[] strs = new String[strList.size()];
		return strList.toArray(strs);
	}
	
}
