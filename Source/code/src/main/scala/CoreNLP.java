import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.CollectionUtils;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Mayanka on 21-Jun-16.
 */
public class CoreNLP {

    public static String returnLemma(String sentence) throws IOException {

        Document doc = new Document(sentence);
        String lemma= new String(Files.readAllBytes(Paths.get("C:/Users/user/Desktop/bbc/entertainment/019.txt")));

        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
            List<String> l=sent.lemmas();
            for (int i = 0; i < l.size() ; i++) {
                lemma+= l.get(i) +" ";
            }
            System.out.println(lemma);
        }

        return lemma;
    }

}
