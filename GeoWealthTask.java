package com.csi.helpdesk.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class GeoWealthTask {
    private static final String WORDS_URL = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";
    private static final Set<String> validWords = new HashSet<>();

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();

            URL url = new URL(WORDS_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            Set<String> words = new HashSet<>();
            String word;
            while ((word = in.readLine()) != null) {
                words.add(word);
            }
            in.close();

            words.add("I");
            words.add("A");

            for (String w : words) {
                if (w.length() == 9 && isValidWord(w, words)) {
                    validWords.add(w);
                }
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("Number of valid 9-letter words: " + validWords.size());
            System.out.println("Execution time (excluding data loading): " + duration + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidWord(String word, Set<String> words) {
        if (word.length() == 1) {
            return word.equals("I") || word.equals("A");
        }
        for (int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i + 1);
            if (words.contains(newWord) && isValidWord(newWord, words)) {
                return true;
            }
        }
        return false;
    }
}