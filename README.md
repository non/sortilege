## Sortilege

> Divination (from Latin *divinare* "to foresee, to be inspired by a
> god", related to *divinus*, divine) is the attempt to gain insight
> into a question or situation by way of an occultic, standardized
> process or ritual. Used in various forms throughout history,
> diviners ascertain their interpretations of how a querent should
> proceed by reading signs, events, or omens, or through alleged
> contact with a supernatural agency.

-- [*Divination*](http://en.wikipedia.org/wiki/Divination), Wikipedia

### Overview

Sortilege is a library for predicting the future.

Sortilege currently supports five divination methods:

 1. [I Ching](http://en.wikipedia.org/wiki/I_Ching)
 2. [Tarot](http://en.wikipedia.org/wiki/Divinatory,_esoteric_and_occult_tarot)
 3. [Futhark](http://en.wikipedia.org/wiki/Runes)
 4. [Ogham](http://en.wikipedia.org/wiki/Ogham)
 5. [Magic 8-Ball](http://en.wikipedia.org/wiki/Magic_8-Ball)

### Examples

```scala
scala> sortilege.iching.IChing.yarrow.display
res0: String = the clinging fire ䷝ becoming abundance ䷶

scala> sortilege.runes.Runes.random.display
res1: String = algiz ᛉ

scala> sortilege.ogham.Ogham.choose(3).map(_.display)
res2: scala.collection.immutable.Vector[String] = Vector(Onn ᚑ, Sail ᚄ, Dair ᚇ)

scala> println(sortilege.tarot.Tarot.celticCross.display)
 1. present:    temperance (inverted)
 2. challenge:  five of swords (inverted)
 3. past:       king of pentacles (inverted)
 4. future:     two of pentacles (inverted)
 5. above:      eight of swords
 6. below:      knight of cups (inverted)
--------
 7. advice:     the fool (inverted)
 8. influences: ten of swords
 9. emotions:   knight of swords (inverted)
10. outcome:    the moon
        
scala> sortilege.eightball.Eightball.random
res4: sortilege.eightball.Phrase = Phrase(cannot predict now,unknown)
```

### Detailed Information

Sortilege is currently using a
[Complimentary-multiply-with-carry](http://en.wikipedia.org/wiki/Multiply-with-carry#Complementary-multiply-with-carry_generators)
random number generator provided by
[Spire](http://github.com/non/spire). Eventually we should make the
generators pluggable to support alternate implementations.

Users may wish to manually seed the generator with information derived
from the querant, such as the location and time, the question being
asked, other background information, etc.

### Getting Sortilege

Sortilege is currently unpublished.

### Known Issues

When used improperly, these methods may fail to predict the future. It
is up to the user to determine if and when various prediction methods
should be used.

In addition, there are numerous issues with localization and
canonicalization, to say nothing of competing standards and
authorities.

### Future Work

More divination methods could be added.

Better support for operating on divination result types; possibly a
"divination monad"?

Include text, images, and commentary as resources in the JAR file,
especially for the I Ching and Tarot.

The random number generator should be pluggable.

It would be interesting to provide randomized physical simulations of
divination methods (including dice rolling, coin flipping, and so on).

### Copyright and License

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[COPYING](COPYING) file.

Copyright Erik Osheim, 2014.

### No Warranty

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
