package sortilege.eightball

import spire.math.Trilean
import spire.math.Trilean.{True, False, Unknown}
import spire.implicits._
import spire.random.Generator.rng

case class Phrase(msg: String, value: Trilean)

object Eightball {

  def random(): Phrase = phrases.qchoose

  val phrases: Vector[Phrase] = Vector(
    Phrase("it is certain", True),
    Phrase("it is decidedly so", True),
    Phrase("without a doubt", True),
    Phrase("yes definitely", True),
    Phrase("you may rely on it", True),
    Phrase("as i see it, yes", True),
    Phrase("most likely", True),
    Phrase("outlook good", True),
    Phrase("yes", True),
    Phrase("signs point to yes", True),
    Phrase("reply hazy try again", Unknown),
    Phrase("ask again later", Unknown),
    Phrase("better not tell you now", Unknown),
    Phrase("cannot predict now", Unknown),
    Phrase("concentrate and ask again", Unknown),
    Phrase("don't count on it", False),
    Phrase("my reply is no", False),
    Phrase("my sources say no", False),
    Phrase("outlook not so good", False),
    Phrase("very doubtful", False)).qshuffled
}
