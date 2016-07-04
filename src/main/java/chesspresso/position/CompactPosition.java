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

import chesspresso.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the position interface.
 *
 * The class is optimized for memory footprint. Each instance uses only 36 bytes
 * for internal representation (plus some overhead for java internals).
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public class CompactPosition extends AbstractPosition
{
    private final static Logger LOGGER = LoggerFactory.getLogger( CompactPosition.class );

    private final static int
        SQI_EP_SHIFT          =  0,   SQI_EP_MASK           = 0x07F,  // [0, 128[
        CASTLES_SHIFT         =  7,   CASTLES_MASK          = 0x00F,  // [0, 15]
        TO_PLAY_SHIFT         = 11,   TO_PLAY_MASK          = 0x001,  // [0 | 1]
        PLY_NUMBER_SHIFT      = 12,   PLY_NUMBER_MASK       = 0x3FF,  // [0, 1024[
        HALF_MOVE_CLOCK_SHIFT = 22,   HALF_MOVE_CLOCK_MASK  = 0xFF;   // [0, 128[

    /*================================================================================*/

    private int[] m_stones;   // 32 bytes
    private int m_flags;      //  4 bytes

    /*================================================================================*/

    public CompactPosition()
    {
        // TODO
    }

    public CompactPosition(int[] stones, int flags) {
    	m_stones = stones;
    	m_flags = flags;
    }

    public CompactPosition(ImmutablePosition position)
    {
        m_stones = new int[Chess.NUM_OF_SQUARES / 8];
        for (int sqi=0; sqi < Chess.NUM_OF_SQUARES; sqi += 8) {
            m_stones[sqi / 8] =  (position.getStone(sqi    ) + Chess.MAX_PIECE)        +
                                ((position.getStone(sqi + 1) + Chess.MAX_PIECE) <<  4) +
                                ((position.getStone(sqi + 2) + Chess.MAX_PIECE) <<  8) +
                                ((position.getStone(sqi + 3) + Chess.MAX_PIECE) << 12) +
                                ((position.getStone(sqi + 4) + Chess.MAX_PIECE) << 16) +
                                ((position.getStone(sqi + 5) + Chess.MAX_PIECE) << 20) +
                                ((position.getStone(sqi + 6) + Chess.MAX_PIECE) << 24) +
                                ((position.getStone(sqi + 7) + Chess.MAX_PIECE) << 28);
        }

        m_flags = ((position.getSqiEP() - Chess.NO_SQUARE)       << SQI_EP_SHIFT) +
                   (position.getCastles()                        << CASTLES_SHIFT) +
                  ((position.getToPlay() == Chess.WHITE ? 0 : 1) << TO_PLAY_SHIFT) +
                   (position.getPlyNumber()                      << PLY_NUMBER_SHIFT) +
                   (position.getHalfMoveClock()                  << HALF_MOVE_CLOCK_SHIFT);
    }

    /*================================================================================*/

    public int getStone(int sqi)   {
    	return ((m_stones[sqi / 8] >> (4 * (sqi & 0x7))) & 0xF) - Chess.MAX_PIECE;
    }
    public int getSqiEP()          {return ((m_flags >> SQI_EP_SHIFT) & SQI_EP_MASK) + Chess.NO_SQUARE;}
    public int getCastles()        {return ((m_flags >> CASTLES_SHIFT) & CASTLES_MASK);}
    public int getToPlay()         {return ((m_flags >> TO_PLAY_SHIFT) & TO_PLAY_MASK) == 0 ? Chess.WHITE : Chess.BLACK;}
    public int getPlyNumber()      {return ((m_flags >> PLY_NUMBER_SHIFT) & PLY_NUMBER_MASK);}
    public int getHalfMoveClock()  {return ((m_flags >> HALF_MOVE_CLOCK_SHIFT) & HALF_MOVE_CLOCK_MASK);}

    public int[] getStones() {
    	return m_stones;
    }
    public int getFlags() {
    	return m_flags;
    }
}