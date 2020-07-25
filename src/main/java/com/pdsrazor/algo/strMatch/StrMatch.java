package com.pdsrazor.algo.strMatch;

// 字符串匹配算法:
// BF暴力算法，虽然复杂度O(n*m)很高，但是工程中很常用，尤其是主串和模式串都不太长时，而且·很简单
//
// RK算法(Rabin-Karp)：对主串中的所有符合长度的字串求hash，然后和模式串比较。此时hash算法的设计十分重要。
// 如果某些操作耗时，则可以尝试事先计算并缓存。
//
// 思考：二维字符矩阵的匹配
//
// Boyer-Moore算法: 匹配的时候看做子串不停的在主串中滑动，bf算法就是每次滑动一个位置。BM算法就是找到
// 一次多滑动几个位置的方法，提高效率。
// BM算法中的:bad character rule , good suffix shift
// 核心：从尾部开始倒着匹配， 前缀字串(abc的a和ab)，后缀字串(abc的bc和c)
//
//
//
//
//
//
public class StrMatch {
    public static int doCmd(String args[]) {
        if (args.length != 3)
            return 0;
        if (args[0].equals("strmatch")) {
            BruteForce(args[1], args[2]);
            RabinKarp(args[1], args[2]);
            BoyerMoore(args[1], args[2]);
            KMP(args[1], args[2]);
            return 1;
        }
        return 0;
    }

    // 暴力
    public static void BruteForce(String str, String sub) {
        int mlen = str.length();
        int slen = sub.length();
        if (slen > mlen) {
            System.out.println("BruteForce: no match, slen > mlen");
            return;
        }
        char m[] = new char[mlen];
        char s[] = new char[slen];
        str.getChars(0, mlen, m, 0);
        sub.getChars(0, slen, s, 0);

        for (int i = 0; i < (mlen-slen+1); i++) {
            int j = 0;
            for (j = 0; j < slen; j++) {
                if (m[i+j] != s[j])
                   break;
            }
            if (j == slen) {
                System.out.println("BruteForce: match: " + i);
                return;
            }
        }
        System.out.println("BruteForce: no match");
        return;
    }

    private static int hashWithSum(char s[], int start, int end) {
        int ret = 0;
        while (start < end) {
            ret += s[start++];
        }
        return ret;
    }

    private static boolean myStrcmp(char m[], int start, int len, char s[]) {
        for (int i = 0; i < len; i++) {
            if (m[start+i] != s[i])
                return false;
        }
        return true;
    }

    // 重点在hash算法设计， 以及提高计算hash的效率
    public static void RabinKarp(String str, String sub) {
        int mlen = str.length();
        int slen = sub.length();
        if (slen > mlen) {
            System.out.println("RabinKarp: no match, slen > mlen");
            return;
        }
        char m[] = new char[mlen];
        char s[] = new char[slen];
        str.getChars(0, mlen, m, 0);
        sub.getChars(0, slen, s, 0);

        int tHash = hashWithSum(s, 0, slen);
        // mlen - slen + 1 个子串
        for (int i = 0; i < (mlen-slen+1); i++) {
            int sHash = hashWithSum(m, i, i + slen);
            // 注意hash冲突检查
            if (sHash == tHash && myStrcmp(m, i, slen, s)) {
                System.out.println("RabinKarp: match: " + i);
                return;
            }
        }
        System.out.println("RabinKarp: no match");
        return;
    }

    // BM 算法核心思想是，利用模式串本身的特点，在模式串中某个字符与主串不能匹配的时候，
    // 将模式串往后多滑动几位，以此来减少不必要的字符比较，提高匹配的效率。
    // BM 算法构建的规则有两类，坏字符规则和好后缀规则。
    // 好后缀规则可以独立于坏字符规则使用。
    // 因为坏字符规则的实现比较耗内存，为了节省内存，我们可以只用好后缀规则来实现 BM 算法。

    // 暴力的BM实现，没有充分利用模式串特点进行优化，单纯循环比较实现规则，效率比较低。
    public static void BoyerMoore(String str, String sub) {
        int mlen = str.length();
        int slen = sub.length();
        if (slen > mlen) {
            System.out.println("BoyerMoore: no match, slen > mlen");
            return;
        }
        char m[] = new char[mlen];
        char s[] = new char[slen];
        str.getChars(0, mlen, m, 0);
        sub.getChars(0, slen, s, 0);

        for (int i = 0; i < (mlen-slen+1);) {
            int j = slen - 1;
            char bad = 0;
            int goodIdx;
            for (j = (slen - 1); j >= 0; j--) {
                if (s[j] != m[i+j]) {
                    bad = m[i+j];             // bad character
                    goodIdx = i+j+1; // good suffix start idx
                    break;
                }
            }
            int shift1, shift2, maxShift;
            if (j < 0) {
                // 完全匹配了，返回
                System.out.println("BoyerMoore: match: " + i);
                return;
            } else {
                shift1 = j + 1; // bad character rule, 最多滑动的字符数
                // bad character rule
                for (int k = j-1; k >= 0; k--) {
                    if (s[k] == bad) {
                        shift1 = j - k; // 可以滑动的字符数量
                        break;
                    }
                }
                // good suffix shift rule
                shift2 = slen; // 最多滑动的字符数量
                boolean match = true;
                for (int k = j; k >= 0; k--) { // 从未能匹配上的字符开始与好后缀匹配，从而决定滑动的字符数量
                    for (int t = (j + 1), t2 = k; t < slen; t++, t2++) { // 好后缀的长度和区间
                        if (s[t2] != s[t]) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        shift2 = j+1-k;
                        break;
                    }
                }
                // 前面滑动测试中没有匹配上，继续处理好后缀与模式串局部重叠的部分
                if (!match) {
                    match = true;
                    for (int k = j + 1 + 1; k < slen; k++) {
                        for (int t1 = 0, t2 = k /* 好后缀的一部分 */; t2 < slen; t2++,t1++) {
                            if (s[t2] != s[t1]) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            shift2 = j+1+(k-j-1);
                            break;
                        }
                    }
                }
                maxShift = (shift2 > shift1) ? shift2 : shift1;
                if (shift1 > shift2) {
                    //System.out.println("BoyerMoore: bad character shift: " + shift1);
                } else {
                    //System.out.println("BoyerMoore: good suffix shift: " + shift2);
                }
            }
            i = i + maxShift;
        }
        System.out.println("BoyerMoore: no match ");
    }

    // 利用hash表提升 查找坏字符在模式串出现位置的效率
    private static final int SIZE = 256; // 全局变量或成员变量
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1; // 初始化bc
        }
        for (int i = 0; i < m; ++i) {
            int ascii = (int)b[i]; // 计算b[i]的ASCII值
            bc[ascii] = i; // 这里会后出现的位置会覆盖先出现的，也符合坏字符规则
            // 从后往前查找，一旦找到match的就停下来。
        }
    }

    // a:主串， b：模式串，n和m分别为长度
    public int bmBC(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int i = 0; // i表示主串与模式串对齐的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
            i = i + (j - bc[(int)a[i+j]]); 
        }
        return -1;
    }

    // 这段代码用来提前计算好 模式串中每个后缀字串对应的另一个可匹配的字串的位置
    // 以及好后缀的后缀字串中，能和模式串的前缀字串匹配的后缀字串
    // b表示模式串，m表示长度，suffix，prefix数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        // 强调是子串，所以是m-1
        for (int i = 0; i < m - 1; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]) { // 与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
        }
    }
    
    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    // bm算法完整版，包含坏字符规则和好后缀规则
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix); // 构建suffix和prefix数组，快速根据好后缀计算后移位置
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];// 坏字符规则得到的后移字符数量
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix); // 计算根据好后缀得到的后移字符数量
            }
            i = i + Math.max(x, y); // 取两种规则得到的后移字符数量的最大值
        }
        return -1;
    }
  
    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] +1;
        for (int r = j+2; r <= m-1; ++r) { // 遍历好后缀的后缀字串，所以是j+2
            if (prefix[m-r] == true) { // 好后缀的后缀字串和模式串的前缀字串匹配
                return r; // 后移r个字符
            }
        }
        return m; // 好后缀规则，最长直接移动整个模式串的长度
    }

    public static void KMP(String str, String sub) {
        int mlen = str.length();
        int slen = sub.length();
        if (slen > mlen) {
            System.out.println("KMP: no match, slen > mlen");
            return;
        }
        char m[] = new char[mlen];
        char s[] = new char[slen];
        str.getChars(0, mlen, m, 0);
        sub.getChars(0, slen, s, 0);
        int ret = kmp(m, mlen, s, slen);
        if (ret != -1) {
            System.out.println("KMP: match " + ret);
        } else {
            System.out.println("KMP: no match ");
        }
    }

    // KMP算法： 好前缀和坏字符，遇到坏字符后，对已经对比过的好前缀，找到规律将模式串一次滑动很多位
    // 整个算法关键在求出模式串所有 前缀子串 的最长可匹配前缀子串 (可以想象为两个完全一样的字符串
    // 其中一个像后滑动，找到最长的可匹配的部分，则分别为最长可匹配前缀和最长可匹配后缀)
    // a 和 b 分别为主串和模式串
    public static int kmp(char a[], int n, char[] b, int m) {
        int j = 0; // 模式串的当前下标
        int[] next = getNexts(b, m);
        for (int i = 0; i < n; i++) { // i指向主串的下标
            while (j > 0 && a[i] != b[j]) {
                // i永远不会倒退是kmp的精髓 
                j = next[j-1] + 1; // i不变，重新移动j到好前缀的最长匹配前缀字串的下一个字符的位置
            }
            if (a[i] == b[j]) { // 移动j，增加了好前缀长度
                j++;
            }
            if (j == m) { // 匹配上了
                return i-m+1; // 此时i的位置还没有++，相当于指向模式串最后一个字符，m-1
                // 则i-m+1指向模式串首字符
            }
        }
        return -1;
    }

    public static int[] getNexts(char[] b, int m) {
        int[] next = new int[m]; // 下标是前缀字串的结束位置
        next[0] = -1; // 长度为1的字符串，没有子串
        int k = -1; // 最长可匹配前缀的结束位置
        // i是模式串的所有前缀字串的最后一个元素的下标
        for (int i = 1; i < m; i++) { // 所有前缀子串，他们都可能成为好前缀， 除去特殊情况，从1开始
            while (k != -1 && b[k+1] != b[i]) {
                // 次长可匹配前缀子串，肯定是在最长可匹配前缀子串范围内
                // 且一定是最长可匹配前缀子串的 最长可匹配子串。。。。
                k = next[k];
                //k = k-1;
            }
            if (b[k+1] == b[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }
}