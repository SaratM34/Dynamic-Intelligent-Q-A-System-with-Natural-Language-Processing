import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.rdd.RDD
import java.nio.file.{Files, Paths}
object MainClass {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\winutils");
    val sparkConf = new SparkConf().setAppName("MainClass").setMaster("local[*]")
    val sc=new SparkContext(sparkConf)

    val slem: test = new test()
    val demo = slem.lemmatize("This is a question answering system. The question is from Quora").split(" ")
    // RDD[ String ]
    val text = new String(Files.readAllBytes(Paths.get("C:\\Users\\USER\\Desktop\\bbcsport.txt")))
    val  ra = slem.lemmatize(text)
    val x = sc.parallelize(Array(ra))
    //val y = x.groupBy(word => word.charAt(0))
    val y = x.flatMap(line=>{line.split(" ")}).groupBy(word=> word.charAt(0)).cache()
    val wor: test=new test()
    val demo1=wor.ner(text)
    val x1=sc.parallelize(Array(demo1))
    val y1=x1.flatMap(line=>{line.split(" ")}).filter(line => line.contains("PERSON")).count()
    System.out.println(y1)
    y.saveAsTextFile("output")

    val coreNLP: QA = new QA
    coreNLP.processMethod() ;

    println("Type your Question:")
    val input4:String = scala.io.StdIn.readLine()

    var s1:String = coreNLP.qaMethod(input4);
    println("Answer to ur question is:" + s1)

  }
}
