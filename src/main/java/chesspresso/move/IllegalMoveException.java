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
package chesspresso.move;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Exception indicating an illegal move.
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public class IllegalMoveException extends Exception
{
    private final static Logger LOGGER = LoggerFactory.getLogger( IllegalMoveException.class );

    public IllegalMoveException(short move)
    {
        super ("Illegal move: " + Move.getString(move));
    }

    public IllegalMoveException(short move, String msg)
    {
        super ("Illegal move: " + Move.getString(move) + ": " + msg);
    }

    public IllegalMoveException(Move move)
    {
        super ("Illegal move: " + move);
    }

    public IllegalMoveException(Move move, String msg)
    {
        super ("Illegal move: " + move + ": " + msg);
    }

    public IllegalMoveException(String msg)
    {
        super ("Illegal move: " + msg);
    }
}