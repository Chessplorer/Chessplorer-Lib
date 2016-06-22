/*
 * Chessplorer-Lib - an open source chess library written in Java
 * Copyright (C) 2016 Chessplorer.org
 * Copyright (C) 2012-2016 Gerhard Kalab
 * Copyright (C) 2002-2003 Bernhard Seybold
 *
 * This software is published under the terms of the LGPL Software License,
 * a copy of which has been included with this distribution in the LICENSE.txt
 * file.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 */
package chesspresso.position;

import chesspresso.Chess;
import junit.framework.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for the FEN class.
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public class FENTests extends TestCase
{
    private final static Logger LOGGER = LoggerFactory.getLogger( FENTests.class );

    public static Test suite()
    {
        return new TestSuite(FENTests.class);
    }

    public static void main (String[] args)
    {
        junit.textui.TestRunner.run(suite());
    }

    //======================================================================

    public static void testChars()
    {
        for (int stone=Chess.MIN_STONE; stone<Chess.MAX_PIECE - 1; stone++) {
            if (stone != Chess.NO_STONE) {
                assertEquals("stone changed", stone, FEN.fenCharToStone(FEN.stoneToFenChar(stone)));
            }
        }
    }

}