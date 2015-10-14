package sortilege.runes

import spire.implicits._
import spire.random.Generator.rng

sealed abstract class Rune(val name: String, val glyph: Char, val sound: Char, val meaning: String) {
  def display: String = s"$name $glyph"
}

case object Fehu extends Rune("fehu", 'ᚠ', 'f', "cattle")
case object Uruz extends Rune("uruz", 'ᚢ', 'u', "auroch")
case object Thurisaz extends Rune("thursiaz", 'ᚦ', 'þ', "thorn")
case object Ansuz extends Rune("ansuz", 'ᚨ', 'a', "mouth")
case object Raido extends Rune("raido", 'ᚱ', 'r', "wheel")
case object Kaunan extends Rune("kaunan", 'ᚲ', 'k', "torch")
case object Gebo extends Rune("gebo", 'ᚷ', 'g', "gift")
case object Wunjo extends Rune("wunjo", 'ᚹ', 'w', "joy")
case object Hagalaz extends Rune("hagalaz", 'ᚺ', 'h', "hail")
case object Naudiz extends Rune("naudiz", 'ᚾ', 'n', "need")
case object Isaz extends Rune("isaz", 'ᛁ', 'i', "ice")
case object Jera extends Rune ("jera", 'ᛃ', 'j', "harvest")
case object Eiwaz extends Rune("eiwaz", 'ᛇ', 'æ', "yew")
case object Perth extends Rune("perth", 'ᛈ', 'p', "cup")
case object Algiz extends Rune("algiz", 'ᛉ', 'z', "elk")
case object Sowilo extends Rune("sowilo", 'ᛊ', 's', "sun")
case object Tiwaz extends Rune("tiwaz", 'ᛏ', 't', "creator")
case object Berkanen extends Rune("berkanen", 'ᛒ', 'b', "birch")
case object Ehwaz extends Rune("ehwaz", 'ᛖ', 'e', "horse")
case object Mannaz extends Rune("mannaz", 'ᛗ', 'm', "human")
case object Laguz extends Rune("laguz", 'ᛚ', 'l', "water")
case object Ingwaz extends Rune("ingwaz", 'ᛝ', 'ŋ', "fertility")
case object Othila extends Rune("othila", 'ᛟ', 'o', "home")
case object Dagaz extends Rune("dagaz", 'ᛞ', 'd', "day")

object Runes {
  val Aett1: Vector[Rune] =
    Vector(Fehu, Uruz, Thurisaz, Ansuz, Raido, Kaunan, Gebo, Wunjo)

  val Aett2: Vector[Rune] =
    Vector(Hagalaz, Naudiz, Isaz, Jera, Eiwaz, Perth, Algiz, Sowilo)

  val Aett3: Vector[Rune] =
    Vector(Tiwaz, Berkanen, Ehwaz, Mannaz, Laguz, Ingwaz, Othila, Dagaz)

  val Aetts: Vector[Vector[Rune]] = Vector(Aett1, Aett2, Aett3)

  val All: Vector[Rune] = Aett1 ++ Aett2 ++ Aett3

  def random(): Rune = All.qchoose

  def choose(n: Int): Vector[Rune] = All.qshuffled.take(n)
}
