import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.lang.String;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;


public class test {

    protected StanfordCoreNLP pipeline;
    public test() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

        /*
         * This is a pipeline that takes in a string and returns various analyzed linguistic forms.
         * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator),
         * and then other sequence model style annotation can be used to add things like lemmas,
         * POS tags, and named entities. These are returned as a list of CoreLabels.
         * Other analysis components build and store parse trees, dependency graphs, etc.
         *
         * This class is designed to apply multiple Annotators to an Annotation.
         * The idea is that you first build up the pipeline by adding Annotators,
         * and then you take the objects you wish to annotate and pass them in and
         * get in return a fully annotated object.
         *
         *  StanfordCoreNLP loads a lot of models, so you probably
         *  only want to do this once per execution
         */
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String lemmatize(String documentText)
    {
        List<String> lemmas = new LinkedList<String>();
        List<String> wo = new LinkedList<String>();
        List<String> POS = new LinkedList<String>();
        List<String> NER = new LinkedList<String>();

        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                lemmas.add(token.get(LemmaAnnotation.class));
                wo.add(token.get(CoreAnnotations.TextAnnotation.class));
                POS.add(token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
                NER.add(token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }
        }
        System.out.println(wo);
        System.out.println(POS);
        System.out.println(NER);
        StringBuilder sb = new StringBuilder();
        for (String s : lemmas)
        {
            sb.append(s);
            sb.append(" ");
        }

        //System.out.println(sb.toString());
        //String s = sb.toString();
        return sb.toString();
    }
    public String ner(String documentText)
    {
        List<String> wo = new LinkedList<String>();
        List<String> POS = new LinkedList<String>();
        List<String> NER = new LinkedList<String>();

        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                wo.add(token.get(CoreAnnotations.TextAnnotation.class));
                POS.add(token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
                NER.add(token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }
        }

        System.out.println(wo);
        System.out.println(POS);
        System.out.println(NER);

        StringBuilder sb = new StringBuilder();
        for (String s : NER)
        {
            sb.append(s);
            sb.append(" ");
        }

        //System.out.println(sb.toString());
        //String s = sb.toString();
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Starting Stanford Lemmatizer");
        String text = "How answering you be seeing into my eyes like open doors? \n";

        //test slem = new test();

        //System.out.println(slem.lemmatize(text));

    }

}