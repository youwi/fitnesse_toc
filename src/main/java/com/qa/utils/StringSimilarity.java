package com.qa.utils;
/**
 * fitnesse_toc
 * Created by yu on 2017/8/4.
 */
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 比较两个字符串的相似度
 */
public class StringSimilarity {
    public static void main(String[] args) {
        String strA = "我是中国人";
        String strB = "你是中国人";
        double result = SimilarDegree(strA, strB);
        if (result >= 0.7) {
            System.out.println("相似度很高！" + similarityResult(result) + result);
        } else {
            System.out.println("相似度不高" + similarityResult(result) + result);
        }
        System.out.println();
    }

    /**
     * 相似度转百分比
     */
    public static String similarityResult(double resule) {
        return NumberFormat.getPercentInstance(new Locale("en ", "US ")).format(resule);
    }

    /**
     * 相似度比较
     *
     * @param strA A要长于B
     * @param strB
     * @return
     */
    public static double SimilarDegree(String strA, String strB) {
        String newStrA = removeSign(strA);
        String newStrB = removeSign(strB);
        int temp = Math.max(newStrA.length(), newStrB.length());
        int temp2=0;
        if(newStrA.length()>newStrB.length()){
              temp2 = longestCommonSubstring(newStrA, newStrB).length();
        }else{
              temp2 = longestCommonSubstring(newStrB, newStrA).length();
        }

        return temp2 * 1.0 / temp;
    }

    private static String removeSign(String str) {
        StringBuffer sb = new StringBuffer();
        for (char item : str.toCharArray())
            if (charReg(item)) {
                //System.out.println("--"+item);
                sb.append(item);
            }
        return sb.toString();
    }

    private static boolean charReg(char charValue) {
        return (charValue >= 0x4E00 && charValue <= 0X9FA5)
                || (charValue >= 'a' && charValue <= 'z')
                || (charValue >= 'A' && charValue <= 'Z')
                || (charValue >= '0' && charValue <= '9');
    }

    private static String longestCommonSubstring(String strA, String strB) {
        char[] chars_strA = strA.toCharArray();
        char[] chars_strB = strB.toCharArray();
        int m = chars_strA.length;
        int n = chars_strB.length;
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars_strA[i - 1] == chars_strB[j - 1])
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0) {
            try {
                if(n>matrix.length){}
                if (matrix[n] == matrix[n - 1])
                    n--;
                else if (matrix[m][n] == matrix[m - 1][n])
                    m--;
                else {
                    result[currentIndex] = chars_strA[m - 1];
                    currentIndex--;
                    n--;
                    m--;
                }
            }catch (Exception e){
                System.err.println(e.getClass().getName()+" "+e.getMessage());
                break;
            }
        }
        return new String(result);
    }
}