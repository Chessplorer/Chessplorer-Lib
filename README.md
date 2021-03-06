Chessplorer-Lib 1.0-RC1
=======================

*Chessplorer-Lib* is an open source chess library written in Java. It is a fork
of the [*ChesspressoNG*](https://github.com/gkalab/chesspressong) library by
[Gerhard Kalab](https://github.com/gkalab) (based on commit
[`d2468c3`](https://github.com/gkalab/chesspressong/tree/d2468c3ba25eb38f4a502dedc454d4aab4226ea3)),
who initially forked the
[*Chesspresso*](https://github.com/BernhardSeybold/Chesspresso) library by
[Bernhard Seybold](https://github.com/BernhardSeybold) (based on version 0.9.2).


Modifications
-------------

*Chessplorer-Lib* can be used as a drop in replacement for *Chesspresso* or
*ChesspressoNG* and contains the following modifications / improvements:

-   The project was converted into a [Maven](https://maven.apache.org/)
    structure to make it easier usable for other
    [Maven](https://maven.apache.org/) based applications.
-   The library will be published at
    [Maven Central Repository](http://search.maven.org/#search|ga|1|org.chessplorer)
    in order to allow an integration into other applications without the need of
    separate repositories.
-   The logging mechanism was replaced by [slf4j](http://www.slf4j.org/).
-   Internationalized with [GNU gettext](https://www.gnu.org/software/gettext/)
    and translated into English and German.


How to use
----------

Download the [latest release from GitHub](https://github.com/Chessplorer/Chessplorer-Lib/releases/latest).
The provided archive contains all required files (compiled libraries,
dependencies, source code and documentations).

Alternatively you can integrate the library from
[Maven Central Repository](http://search.maven.org/#search|ga|1|org.chessplorer)
into your [Maven](http://maven.apache.org/) project. Just add dependency to your
projects `pom.xml`:

```xml
<dependency>
  <groupId>org.chessplorer</groupId>
  <artifactId>Chessplorer-Lib</artifactId>
  <version>1.0-RC1</version>
</dependency>
```

In order to get latest **development snapshots** of *Chessplorer-Lib*, you need
to register the following repository in your `settings.xml` / `pom.xml`:

```xml
<repositories>
  <repository>
    <id>ossrh</id>
    <name>Open Source Project Repository Hosting</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    <snapshots>
      <enabled>true</enabled>
      <checksumPolicy>fail</checksumPolicy>
    </snapshots>
  </repository>
</repositories>
```


Dependencies
------------

-   Java 6 or newer
-   [gettext-commons 0.9.8](https://code.google.com/archive/p/gettext-commons/) ([LGPL 2.1](share/licenses/gettext-commons.license.txt))
-   [slf4j 1.7.21](http://www.slf4j.org/) ([MIT License](share/licenses/slf4j-api.license.txt))


Changelog
---------

Take a look at [`CHANGELOG.md`](CHANGELOG.md) for the full changelog.


License
-------

*Chessplorer-Lib* is licensed under the terms of
[GNU Lesser General Public License version 2.1](LICENSE.txt).


Further informations
--------------------

-   [*Chessplorer-Lib* at GitHub](https://github.com/Chessplorer/Chessplorer-Lib)
-   [Releases of *Chessplorer-Lib*](https://github.com/Chessplorer/Chessplorer-Lib/releases)
-   [Changelog of *Chessplorer-Lib*](https://github.com/Chessplorer/Chessplorer-Lib/blob/master/CHANGELOG.md)
-   [License of *Chessplorer-Lib*](https://github.com/Chessplorer/Chessplorer-Lib/blob/master/LICENSE.txt)
