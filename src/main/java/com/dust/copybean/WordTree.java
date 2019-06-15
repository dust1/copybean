package com.dust.copybean;

import java.util.Arrays;
import java.util.List;

/**
 * 单词树
 */
public class WordTree {

    private char val;

    private WordTree[] nexts;

    private boolean isWord;

    public WordTree() {
        this('-');
    }

    public WordTree(char word) {
        this.isWord = false;
        this.val = word;
        this.nexts = new WordTree[57];
    }

    public void append(String word) {
        char[] chars = word.toCharArray();
        append(0, word);
    }

    private void append(int index, String word) {
        if (index == word.length()) {
            this.isWord = true;
            return;
        }
        char chars = word.charAt(index);
        if (Character.isUpperCase(chars) ||         //是否是大写字母
                Character.isLowerCase(chars)) {     //是否是小写字母
            int nodeIndex = chars - 'A';
            WordTree node = nexts[nodeIndex] == null
                    ? new WordTree(chars)
                    : nexts[nodeIndex ];
            nexts[nodeIndex] = node;
            node.append(index + 1, word);
            return;
        }
        throw new IllegalArgumentException("you should input 'a' to 'z' or 'A' to 'Z'");
    }

    @Override
    public String toString() {
        WordTree[] wordTrees = new WordTree[this.nexts.length];
        int index = 0;
        for (WordTree wordTree : this.nexts) {
            if (wordTree != null) {
                wordTrees[index++] = wordTree;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(wordTrees[i]).append(",");
        }
        sb.append("\r\n");
        return "{val:" + this.val + ", next: " + sb.toString() + "}";
    }

}
