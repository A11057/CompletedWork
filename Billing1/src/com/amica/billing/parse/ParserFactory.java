package com.amica.billing.parse;

import java.util.Optional;

public class ParserFactory {

    public static void createParser(String filename) {
        if (filename.contains(".csv")){
            new CSVParser();
                if (filename.contains((".flat"))) {
                new FlatParser();
            }
        }
    }

//    public Optional<String> createParser(String filename) {
//        return Optional.ofNullable(filename)
//                .filter(f -> f.contains("."))
//                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
//    }
}
