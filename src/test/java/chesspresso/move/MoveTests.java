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

import chesspresso.Chess;
import junit.framework.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public class MoveTests extends TestCase
{
    private final static Logger LOGGER = LoggerFactory.getLogger( MoveTests.class );

    public static Test suite()
    {
        return new TestSuite(MoveTests.class);
    }

    public static void main (String[] args)
    {
        junit.textui.TestRunner.run(suite());
    }

    //======================================================================

    private void checkMove(short move,
                           int from, int to,
                           boolean isCapturing, boolean isPromo, int promoPiece, boolean isEPMove,
                           boolean isCastle, boolean isShortCastle, boolean isLongCastle,
                           boolean isSpecial, boolean isValid)
    {
        if (!isSpecial) {
            if (!isCastle) {
                assertEquals("from is wrong " + Move.getString(move),           from,        Move.getFromSqi(move));
                assertEquals("to is wrong " + Move.getString(move),             to,          Move.getToSqi(move));
                assertEquals("isCapturing is wrong " + Move.getString(move),    isCapturing, Move.isCapturing(move));
                assertEquals("isPromotion is wrong " + Move.getString(move),    isPromo,     Move.isPromotion(move));
                assertEquals("promotionPiece is wrong " + Move.getString(move), promoPiece,  Move.getPromotionPiece(move));
                assertEquals("isEPMove is wrong " + Move.getString(move),       isEPMove,    Move.isEPMove(move));
            }
            assertEquals("isCastle is wrong " + Move.getString(move),      isCastle,      Move.isCastle(move));
            assertEquals("isLongCastle is wrong " + Move.getString(move),  isShortCastle, Move.isShortCastle(move));
            assertEquals("isShortCastle is wrong " + Move.getString(move), isLongCastle,  Move.isLongCastle(move));
        }
        assertEquals("isSpecial is wrong " + Move.getString(move), isSpecial, Move.isSpecial(move));
        assertEquals("isValid is wrong " + Move.getString(move),   isValid  , Move.isValid(move));
    }

    public void testMove() throws Exception
    {
        for (int from = 0; from < Chess.NUM_OF_SQUARES; from++) {
            for (int to = 0; to < Chess.NUM_OF_SQUARES; to++) {
                for (int capturing=0; capturing <= 1; capturing++) {
                    checkMove(Move.getRegularMove(from, to, capturing == 1),
                              from, to,
                              capturing == 1, false, Chess.NO_PIECE, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.QUEEN),
                              from, to,
                              capturing == 1, true, Chess.QUEEN, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.ROOK),
                              from, to,
                              capturing == 1, true, Chess.ROOK, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.BISHOP),
                              from, to,
                              capturing == 1, true, Chess.BISHOP, false,
                              false, false, false,
                              false, true);
                    checkMove(Move.getPawnMove(from, to, capturing == 1, Chess.KNIGHT),
                              from, to,
                              capturing == 1, true, Chess.KNIGHT, false,
                              false, false, false,
                              false, true);
                }
                checkMove(Move.getEPMove(from, to),
                          from, to,
                          true, false, Chess.NO_PIECE, true,
                          false, false, false,
                          false, true);
            }
        }

        checkMove(Move.WHITE_SHORT_CASTLE,
                  Chess.E1, Chess.G1,
                  false, false, Chess.NO_PIECE, false,
                  true, true, false,
                  false, true);
        checkMove(Move.WHITE_LONG_CASTLE,
                  Chess.E1, Chess.C1,
                  false, false, Chess.NO_PIECE, false,
                  true, false, true,
                  false, true);
        checkMove(Move.BLACK_SHORT_CASTLE,
                  Chess.E8, Chess.G8,
                  false, false, Chess.NO_PIECE, false,
                  true, true, false,
                  false, true);
        checkMove(Move.BLACK_LONG_CASTLE,
                  Chess.E8, Chess.C8,
                  false, false, Chess.NO_PIECE, false,
                  true, false, true,
                  false, true);
        checkMove(Move.NO_MOVE,
                  0, 0,
                  false, false, Chess.NO_PIECE, false,
                  false, false, false,
                  true, false);
        checkMove(Move.ILLEGAL_MOVE,
                  0, 0,
                  false, false, Chess.NO_PIECE, false,
                  false, false, false,
                  true, false);
    }

}