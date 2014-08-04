package sortilege.iching

import spire.random.Generator.rng

sealed trait Reading {
  def display: String =
    this match {
      case Unchanging(h) => h.display
      case Changing(b, a) => s"${b.display} becoming ${a.display}"
    }
}

case class Unchanging(h: Hexagram) extends Reading
case class Changing(b: Hexagram, a: Hexagram) extends Reading

object Reading {
  def apply(b: Hexagram, a: Hexagram): Reading =
    if (b == a) Unchanging(b) else Changing(b, a)
}

case class Hexagram(name: String, num: Int, repr: Int) {
  def glyph: Char = ('\u4DBF' + num).toChar
  def display: String = s"$name $glyph"
  def lines: Lines = Lines(repr)
}

case class Lines(s1: Line, s2: Line, s3: Line, s4: Line, s5: Line, s6: Line)

object Lines {
  def apply(repr: Int): Lines = {
    def f(i: Int): Line = if (((repr >>> i) & 0xf) == 0) Broken else Whole
    Lines(f(20), f(16), f(12), f(8), f(4), f(0))
  }
}

sealed trait Line
case object Whole extends Line
case object Broken extends Line

object IChing {

  def yarrow(): Reading = {
    def loop(r: Int, i: Int, b: Int, a: Int): Reading =
      if (i >= 6) Reading(lookup(b), lookup(a)) else {
        val k = 1 << (i * 4)
        val x = r & 0xf
        val s = r >>> 4
        if (x < 1) loop(s, i + 1, b, a | k)
        else if (x < 4) loop(s, i + 1, b | k, a)
        else if (x < 9) loop(s, i + 1, b | k, a | k)
        else loop(s, i + 1, b, a)
      }
    loop(rng.nextInt(), 0, 0, 0)
  }

  def coins(): Reading = {
    def loop(r: Int, i: Int, b: Int, a: Int): Reading =
      if (i >= 6) Reading(lookup(b), lookup(a)) else {
        val k = 1 << (i * 4)
        val x = r & 0x7
        val s = r >>> 3
        if (x < 1) loop(s, i + 1, b, a | k)
        else if (x < 2) loop(s, i + 1, b | k, a)
        else if (x < 5) loop(s, i + 1, b | k, a | k)
        else loop(s, i + 1, b, a)
      }
    loop(rng.nextInt(), 0, 0, 0)
  }

  def lookup(repr: Int): Hexagram =
    hexagrams(table(repr) - 1)

  def random(): Hexagram =
    hexagrams(rng.nextInt(64) + 1)

  val hexagrams: Vector[Hexagram] = Vector(
    Hexagram("the creative heaven", 1, 0x111111),
    Hexagram("the receptive earth", 2, 0x000000),
    Hexagram("difficulty at the beginning", 3, 0x010001),
    Hexagram("youthful folly", 4, 0x100010),
    Hexagram("waiting", 5, 0x010111),
    Hexagram("conflict", 6, 0x111010),
    Hexagram("the army", 7, 0x000010),
    Hexagram("holding together", 8, 0x010000),
    Hexagram("small taming", 9, 0x110111),
    Hexagram("treading", 10, 0x111011),
    Hexagram("peace", 11, 0x000111),
    Hexagram("standstill", 12, 0x111000),
    Hexagram("fellowship", 13, 0x111101),
    Hexagram("great possession", 14, 0x101111),
    Hexagram("modesty", 15, 0x000100),
    Hexagram("enthusiasm", 16, 0x001000),
    Hexagram("following", 17, 0x011001),
    Hexagram("work on the decayed", 18, 0x100110),
    Hexagram("approach", 19, 0x000011),
    Hexagram("contemplation", 20, 0x110000),
    Hexagram("biting through", 21, 0x101001),
    Hexagram("grace", 22, 0x100101),
    Hexagram("splitting apart", 23, 0x100000),
    Hexagram("return", 24, 0x000001),
    Hexagram("innocence", 25, 0x111001),
    Hexagram("great taming", 26, 0x100111),
    Hexagram("mouth corners", 27, 0x100001),
    Hexagram("great preponderance", 28, 0x011110),
    Hexagram("the abysmal water", 29, 0x010010),
    Hexagram("the clinging fire", 30, 0x101101),
    Hexagram("influence", 31, 0x011100),
    Hexagram("duration", 32, 0x001110),
    Hexagram("retreat", 33, 0x111100),
    Hexagram("great power", 34, 0x001111),
    Hexagram("progress", 35, 0x101000),
    Hexagram("darkening of the light", 36, 0x000101),
    Hexagram("the family", 37, 0x110101),
    Hexagram("opposition", 38, 0x101011),
    Hexagram("obstruction", 39, 0x010100),
    Hexagram("deliverance", 40, 0x001010),
    Hexagram("decrease", 41, 0x100011),
    Hexagram("increase", 42, 0x110001),
    Hexagram("breakthrough", 43, 0x011111),
    Hexagram("coming to meet", 44, 0x111110),
    Hexagram("gathering together", 45, 0x011000),
    Hexagram("pushing upward", 46, 0x000110),
    Hexagram("oppression", 47, 0x011010),
    Hexagram("the well", 48, 0x010110),
    Hexagram("revolution", 49, 0x011101),
    Hexagram("the cauldron", 50, 0x101110),
    Hexagram("the arousing thunder", 51, 0x001001),
    Hexagram("the keeping still mountain", 52, 0x100100),
    Hexagram("development", 53, 0x110100),
    Hexagram("the marrying maiden", 54, 0x001011),
    Hexagram("abundance", 55, 0x001101),
    Hexagram("the wanderer", 56, 0x101100),
    Hexagram("the gentle wind", 57, 0x110110),
    Hexagram("the joyous lake", 58, 0x011011),
    Hexagram("dispersion",59, 0x110010 ),
    Hexagram("limitation", 60, 0x010011),
    Hexagram("inner truth", 61, 0x110011),
    Hexagram("small preponderance", 62, 0x001100),
    Hexagram("after completion", 63, 0x010101),
    Hexagram("before completion", 64, 0x101010))

  val table: Map[Int, Int] =
    hexagrams.iterator.map(h => (h.repr, h.num)).toMap
}
