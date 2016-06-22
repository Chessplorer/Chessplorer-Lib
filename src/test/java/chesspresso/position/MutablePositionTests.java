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

import junit.framework.*;

/**
 *
 * @author Bernhard Seybold
 * @version $Revision: 1.1 $
 */
public abstract class MutablePositionTests extends PositionTests
{

    protected abstract MutablePosition createMutablePosition();

    //======================================================================

    public void testFENStartpos()
    {
        MutablePosition position = createMutablePosition();

        FEN.initFromFEN(position, FEN.START_POSITION, true);
        assertEquals("STARTPOS", FEN.START_POSITION, position.getFEN());
    }

    public void testStartpos()
    {
        MutablePosition position = createMutablePosition();

        position.setStart();
        assertTrue("Initial position is not legal", position.isLegal());
        assertTrue("Initial position is not the start position", position.isStartPosition());
    }

}