import java.nio.file.{Files, Paths}

/**
  * Created by Mayanka on 19-06-2017.
  */
object NGRAM {

  def main(args: Array[String]): Unit = {

    val text = new String(Files.readAllBytes(Paths.get("C:\\Users\\user\\Desktop\\bbc\\entertainment\\013.txt")))
    val a = getNGrams(text,3)
    a.foreach(f=>println(f.mkString(" ")))
  }

  def getNGrams(sentence: String, n:Int): Array[Array[String]] = {
    val words = sentence
    val ngrams = words.split(' ').sliding(n)
    ngrams.toArray
  }

}


