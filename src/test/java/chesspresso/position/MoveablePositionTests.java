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
import chesspresso.move.*;
import junit.framework.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public abstract class MoveablePositionTests extends MutablePositionTests
{
    private final static Logger LOGGER = LoggerFactory.getLogger( MoveablePositionTests.class );
    protected abstract MoveablePosition createMoveablePosition();

    //======================================================================

    private String getAllMoves(MoveablePosition position)
    {
        short[] moves = position.getAllMoves();
        Move.normalizeOrder(moves);
        return position.getMovesAsString(moves, true);
    }

    private String readLine(LineNumberReader in) throws IOException
    {
        for (;;) {
            String line = in.readLine();
            if (line == null) return null;
            line = line.trim();
            if (line.length() > 0 && !line.startsWith(";")) {
                return line;
            }
        }
    }

    //======================================================================

    public void testMove() throws IllegalMoveException
    {
        MoveablePosition position = createMoveablePosition();

        position.setStart();
        position.doMove(Move.getPawnMove(Chess.E2, Chess.E4, false, Chess.NO_PIECE));
    }

    public void testGenerateMoves_basic()
    {
        MoveablePosition position = createMoveablePosition();

        position.setStart();
        assertEquals("Moves in startpos",
                     "{Na3,a3,b3,Nc3,c3,d3,e3,Nf3,f3,g3,Nh3,h3,a4,b4,c4,d4,e4,f4,g4,h4}",
                     getAllMoves(position));
    }

    public void testGenerateMoves_extended() throws IOException
    {
        LineNumberReader in = new LineNumberReader(
            new InputStreamReader(
                ClassLoader.getSystemResourceAsStream("chesspresso/position/testGenerateMoves.txt")));

        for (;;) {
            String fen = readLine(in);
            if (fen == null) break; // =====>
            String fileMoves = readLine(in);
            MoveablePosition position = createMoveablePosition();
            FEN.initFromFEN(position, fen, true);
            String moves = getAllMoves(position);
            assertEquals("Moves wrong in position \"" + fen + "\"", fileMoves, moves);
        }
    }

}