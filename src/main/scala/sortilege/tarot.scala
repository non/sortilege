package sortilege.tarot

import spire.implicits._
import spire.random.Generator.rng

sealed abstract class Card {
  def display: String
}

case class Minor(suit: Suit, rank: Rank) extends Card {
  def display: String = s"${rank.name} of ${suit.name}"
}

sealed abstract class Major(val num: Int, val name: String) extends Card {
  def display: String = name
}

sealed abstract class Suit(val name: String)
case object Cups extends Suit("cups")
case object Wands extends Suit("wands")
case object Swords extends Suit("swords")
case object Pentacles extends Suit("pentacles")

sealed abstract class Rank(val value: Int, val name: String)
case object Ace extends Rank(1, "ace")
case object Two extends Rank(2, "two")
case object Three extends Rank(3, "three")
case object Four extends Rank(4, "four")
case object Five extends Rank(5, "five")
case object Six extends Rank(6, "six")
case object Seven extends Rank(7, "seven")
case object Eight extends Rank(8, "eight")
case object Nine extends Rank(9, "nine")
case object Ten extends Rank(10, "ten")
case object Page extends Rank(11, "page")
case object Knight extends Rank(12, "knight")
case object Queen extends Rank(13, "queen")
case object King extends Rank(14, "king")

case object TheFool extends Major(0, "the fool")
case object TheMagician extends Major(1, "the magician")
case object TheHighPriestess extends Major(2, "the high priestess")
case object TheEmpress extends Major(3, "the empress")
case object TheEmperor extends Major(4, "the emperor")
case object TheHierophant extends Major(5, "the hierophant")
case object TheLovers extends Major(6, "the lovers")
case object TheChariot extends Major(7, "the chariot")
case object Justice extends Major(8, "justice")
case object TheHermit extends Major(9, "the hermit")
case object WheelOfFortune extends Major(10, "wheel of fortune")
case object Strength extends Major(11, "strength")
case object TheHangedMan extends Major(12, "the hanged man")
case object Death extends Major(13, "death")
case object Temperance extends Major(14, "temperance")
case object TheDevil extends Major(15, "the devil")
case object TheTower extends Major(16, "the tower")
case object TheStar extends Major(17, "the star")
case object TheMoon extends Major(18, "the moon")
case object TheSun extends Major(19, "the sun")
case object Judgement extends Major(20, "judgement")
case object TheWorld extends Major(21, "the world")

sealed trait Draw {
  def display: String = this match {
    case Upright(c) => c.display
    case Inverted(c) => s"${c.display} (inverted)"
  }
}

case class Upright(card: Card) extends Draw
case class Inverted(card: Card) extends Draw

object Draw {
  def apply(card: Card): Draw =
    if (rng.nextBoolean) Upright(card) else Inverted(card)
}

case class ThreeCard(past: Draw, present: Draw, future: Draw) {
  def display: String =
    s"""1. past:    ${past.display}
2. present: ${present.display}
3. future:  ${future.display}
"""
}

case class Horseshoe(present: Draw, desires: Draw, unexpected: Draw, future: Draw, outcome: Draw) {
  def display: String =
    s"""1. present:    ${present.display}
2. desires:    ${desires.display}
3. unexpected: ${unexpected.display}
4. future:     ${future.display}
5. outcome:    ${outcome.display}
"""
}

case class CelticCross(circle: Circle, staff: Staff) {
  def display: String =
    s""" 1. present:    ${circle.present.display}
 2. challenge:  ${circle.challenge.display}
 3. past:       ${circle.past.display}
 4. future:     ${circle.future.display}
 5. above:      ${circle.above.display}
 6. below:      ${circle.below.display}
--------
 7. advice:     ${staff.advice.display}
 8. influences: ${staff.influences.display}
 9. emotions:   ${staff.emotions.display}
10. outcome:    ${staff.outcome.display}
"""
}

case class Circle(present: Draw, challenge: Draw, past: Draw, future: Draw, above: Draw, below: Draw)
case class Staff(advice: Draw, influences: Draw, emotions: Draw, outcome: Draw)

object Tarot {

  val Suits: Vector[Suit] =
    Vector(Cups, Swords, Wands, Pentacles)

  val Ranks: Vector[Rank] =
    Vector(Ace, Two, Three, Four, Five, Six, Seven,
      Eight, Nine, Ten, Page, Knight, Queen, King)

  val MinorArcana: Vector[Minor] =
    for { s <- Suits; r <- Ranks } yield Minor(s, r)

  val MajorArcana: Vector[Major] =
    Vector(TheFool, TheMagician, TheHighPriestess, TheEmpress, TheEmperor,
      TheHierophant, TheLovers, TheChariot, Justice, TheHermit,
      WheelOfFortune, Strength, TheHangedMan, Death, Temperance, TheDevil,
      TheTower, TheStar, TheMoon, TheSun, Judgement, TheWorld)

  val Deck: Vector[Card] =
    MinorArcana ++ MajorArcana

  def shuffle(): Vector[Card] =
    Deck.qshuffled

  def single(): Draw =
    Draw(Deck.qchoose)

  def draw(n: Int): Vector[Draw] =
    shuffle.take(n).map(Draw(_))

  def threeCard: ThreeCard = {
    val Vector(a, b, c) = draw(3)
    ThreeCard(a, b, c)
  }

  def horseshoe: Horseshoe = {
    val Vector(a, b, c, d, e) = draw(5)
    Horseshoe(a, b, c, d, e)
  }

  def celticCross: CelticCross = {
    val Vector(a, b, c, d, e, f, g, h, i, j) = draw(10)
    val circle = Circle(a, b, c, d, e, f)
    val staff = Staff(g, h, i, j)
    CelticCross(circle, staff)
  }
}
