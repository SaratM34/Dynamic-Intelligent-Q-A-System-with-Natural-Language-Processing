import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class QA {


    public void processMethod() throws IOException {

        // creates a StanfordCoreNLP object.
        Properties props = new Properties();
        props.setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);


        String inputPath;
        String outputPath;

        // The name of the file to open
        inputPath ="C:\\Users\\USER\\Desktop\\bbcsport.txt";
        outputPath ="C:\\Users\\USER\\Desktop\\CoreNLP-QA\\src\\main\\output.txt";

        // The below code is for Lab 1b - 2

        File inputFile = new File(inputPath);
        Scanner input = new Scanner(new FileReader(inputFile));
        String inputString;
        StringBuilder inputSB = new StringBuilder();
        while(input.hasNext())
        {
            inputSB.append(input.next());
            inputSB.append('\t');
        }
        input.close();
        inputString =inputSB.toString();

        StringBuilder sb1 = new StringBuilder();
        String outString1;

        /*public void determineSubroutine()
        {
            System.out.println("Lava");
        }*/

        // create an empty Annotation just with the given text.
        Annotation document = new Annotation(inputString);

        // run all Annotators on this text.
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence :sentences)
        {
            // traverse through the words in the selected sentence.
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);

                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (ne.equalsIgnoreCase("O")) {
                    // no NEP relationship.
                } else {
                    sb1.append(ne);
                    sb1.append(" ");
                    sb1.append(word);
                    sb1.append(" ");
                    sb1.append('\n');
                }
            }
        }
        try
        {
            // Write the file to a document.
            outString1 = sb1.toString();
            FileWriter fileWriter = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(outString1);

            // Close the file
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error writing to the file");

        }

    }

    public String qaMethod(String question) throws IOException {
        // The below code is for Lab 1b - 3 - Question Answering System
        //read the question from the input
        //System.out.println("Type your Question: \n");
        /*BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        String s1 = "";
        String s2 = "";
        if ((s1 = br1.readLine()) != null) {
            s2 = s1;
        }*/
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation
        Annotation document1 = new Annotation(question);

        // run all Annotators on the question
        pipeline.annotate(document1);

        // these are all the sentences in the given question
        List<CoreMap> sentences1 = document1.get(CoreAnnotations.SentencesAnnotation.class);

        StringBuilder sb3 = new StringBuilder();
        String outString3;

        for (CoreMap sentence : sentences1) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                // this is the text of the token in the question
                String word = token.get(CoreAnnotations.TextAnnotation.class);

                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                // this is the NER label of the token in the question
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                String outputPath;
                outputPath ="C:\\Users\\USER\\Desktop\\CoreNLP-QA\\src\\main\\output.txt";

                File inputFile1 = new File(outputPath);
                Scanner input1 = new Scanner(new FileReader(inputFile1));
                String inputString1;

                while (input1.hasNext()) {
                    inputString1 = input1.next();

                    if (lemma.equalsIgnoreCase("PERSON") && inputString1.equalsIgnoreCase("PERSON")) {
                        String finalValue;
                        finalValue = input1.next();
                        sb3.append(finalValue);
                        sb3.append('\n');
                    }
                    if (lemma.equalsIgnoreCase("NUMBER") && inputString1.equalsIgnoreCase("NUMBER")) {
                        String finalValue;
                        finalValue = input1.next();
                        sb3.append(finalValue);
                        sb3.append('\n');
                    }
                    if (lemma.equalsIgnoreCase("LOCATION") && inputString1.equalsIgnoreCase("LOCATION")) {
                        String finalValue;
                        finalValue = input1.next();
                        sb3.append(finalValue);
                        sb3.append('\n');
                    }
                    if (lemma.equalsIgnoreCase("ORGANISATION") && inputString1.equalsIgnoreCase("ORGANISATION")) {
                        String finalValue;
                        finalValue = input1.next();
                        sb3.append(finalValue);
                        sb3.append('\n');
                    }

                }
                input1.close();
            }
        }

        outString3 = sb3.toString();
        System.out.println("Answer to your question is as follows: \n" + outString3);
        return outString3;
    }
    public static void main(String args[]) {

    }
}
