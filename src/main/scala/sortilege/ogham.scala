package sortilege.ogham

import spire.implicits._
import spire.random.Generator

object Ogham {

  sealed abstract class Feda(val name: String, val glyph: Char, val meaning: String) {
    def display: String = s"$name $glyph"
  }

  //Aicme Beithe
  case object Beith extends Feda("Beith", 'ᚁ', "birch")
  case object Luis extends Feda("Luis", 'ᚂ', "rowan")
  case object Fearn extends Feda("Fearn", 'ᚃ', "alder")
  case object Sail extends Feda("Sail", 'ᚄ', "willow")
  case object Nion extends Feda("Nion", 'ᚅ', "ash")

  //Aicme hÚatha
  case object Uath extends Feda("Uath", 'ᚆ', "hawthorn")
  case object Dair extends Feda("Dair", 'ᚇ', "oak")
  case object Tinne extends Feda("Tinne", 'ᚈ', "holly")
  case object Coll extends Feda("Coll", 'ᚉ', "hazel")
  case object Ceirt extends Feda("Ceirt", 'ᚊ', "apple")

  //Aicme Muine
  case object Muin extends Feda("Muin", 'ᚋ', "vine")
  case object Gort extends Feda("Gort", 'ᚌ', "ivy")
  case object NGeadal extends Feda("nGéadal", 'ᚍ', "reed")
  case object Straif extends Feda("Straif", 'ᚎ', "blackthorn")
  case object Ruis extends Feda("Ruis", 'ᚏ', "elder")

  //Aicme Ailme
  case object Ailm extends Feda("Ailm", 'ᚐ', "fir")
  case object Onn extends Feda("Onn", 'ᚑ', "gorse")
  case object Ur extends Feda("Úr", 'ᚒ', "heather")
  case object Eadhadh extends Feda("Eadhadh", 'ᚓ', "poplar")
  case object Iodhadh extends Feda("Iodhadh", 'ᚔ', "yew")

  //Forfeda
  case object Eabhadh extends Feda("Éabhadh", 'ᚕ', "aspen")
  case object Or extends Feda("Ór", 'ᚖ', "spindle tree")
  case object Uilleann extends Feda("Uilleann", 'ᚗ', "honeysuckle")
  case object Ifin extends Feda("Ifín", 'ᚘ', "gooseberry")
  case object Eamhancholl extends Feda("Eamhancholl", 'ᚙ', "twin of hazel")

  val AicmeBeithe: Vector[Feda] =
    Vector(Beith, Luis, Fearn, Sail, Nion)

  val AicmeHuatha: Vector[Feda] =
    Vector(Uath, Dair, Tinne, Coll, Ceirt)

  val AicmeMuine: Vector[Feda] =
    Vector(Muin, Gort, NGeadal, Straif, Ruis)

  val AicmeAilme: Vector[Feda] =
    Vector(Ailm, Onn, Ur, Eadhadh, Iodhadh)

  val Aicmi: Vector[Vector[Feda]] =
    Vector(AicmeBeithe,  AicmeHuatha, AicmeMuine, AicmeAilme)

  val Forfeda: Vector[Feda] =
    Vector(Eabhadh, Or, Uilleann, Ifin, Eamhancholl)

  val Standard: Vector[Feda] =
    AicmeBeithe ++ AicmeHuatha ++ AicmeMuine ++ AicmeAilme

  val Extended: Vector[Feda] =
    Standard ++ Forfeda

  def random(implicit gen: Generator): Feda =
    Standard.qchoose

  def choose(n: Int)(implicit gen: Generator): Vector[Feda] =
    Standard.qshuffled.take(n)

}
