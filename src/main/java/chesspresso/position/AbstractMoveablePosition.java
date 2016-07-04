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
import chesspresso.move.IllegalMoveException;
import chesspresso.move.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public abstract class AbstractMoveablePosition extends AbstractMutablePosition implements MoveablePosition
{
  private final static Logger LOGGER = LoggerFactory.getLogger( AbstractMoveablePosition.class );

	@Override
	public void doMove(Move move) throws IllegalMoveException {
		doMove(move.getShortMoveDesc());
	}

	@Override
	public short getMove(int from, int to, int promoPiece) {
		if (getColor(from) != getToPlay())
			return Move.ILLEGAL_MOVE; // =====>
		int piece = getPiece(from);
		if (piece == Chess.PAWN) {
			if (Chess.sqiToCol(from) != Chess.sqiToCol(to)
					&& getPiece(to) == Chess.NO_PIECE) {
				return Move.getEPMove(from, to);
			} else {
				return Move.getPawnMove(from, to,
						Chess.sqiToCol(from) != Chess.sqiToCol(to), promoPiece);
			}
		} else if (piece == Chess.KING && ((to - from) == 2 || isChess960ShortCastle(from, to))) {
			return Move.getShortCastle(getToPlay());
		} else if (piece == Chess.KING && ((to - from) == -2 || isChess960LongCastle(from, to))) {
			return Move.getLongCastle(getToPlay());
		} else {
			return Move.getRegularMove(from, to, !isSquareEmpty(to));
		}
	}

	private boolean isChess960ShortCastle(int from, int to) {
		return getPiece(to) == Chess.ROOK && getColor(to) == getToPlay()
				&& (to - from) > 0;
	}

	private boolean isChess960LongCastle(int from, int to) {
		return getPiece(to) == Chess.ROOK && getColor(to) == getToPlay()
				&& (to - from) < 0;
	}
}