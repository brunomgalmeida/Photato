package com.trebonius.phototo.helpers;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchQueryHelper {

    /**
     * Given a list of words (which can be a search query), split them in tags 
     * relevant for the index, using multiple split, removing accentuation etc...
     * @param s the query
     * @return 
     */
    public static List<String> getSplittedTerms(String s) {
        return Arrays.asList(s.split("[ '\\,\\.-]")).parallelStream()
                .map((String str) -> Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").trim())
                .map((String str) -> str.replaceAll("[^a-z0-9]+", ""))
                .filter((String str) -> str.length() >= 3)
                .distinct()
                .collect(Collectors.toList());
    }
}
