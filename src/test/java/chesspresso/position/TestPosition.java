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
 * Concrete test for the Position class.
 *
 * @author Bernhard Seybold
 * @version $Revision: 1.2 $
 */
public class TestPosition extends MoveablePositionTests
{

    public static Test suite()
    {
        return new TestSuite(TestPosition.class);
    }

    public static void main (String[] args)
    {
        junit.textui.TestRunner.run(suite());
    }

    //======================================================================

    protected ImmutablePosition createPosition()         {return new Position();}
    protected MutablePosition   createMutablePosition()  {return new Position();}
    protected MoveablePosition  createMoveablePosition() {return new Position();}

}