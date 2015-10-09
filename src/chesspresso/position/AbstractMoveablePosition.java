/*
 * Copyright (C) Bernhard Seybold. All rights reserved.
 *
 * This software is published under the terms of the LGPL Software License,
 * a copy of which has been included with this distribution in the LICENSE.txt
 * file.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *
 * $Id: AbstractMoveablePosition.java,v 1.1 2002/12/08 13:27:34 BerniMan Exp $
 */

package chesspresso.position;

import chesspresso.Chess;
import chesspresso.move.IllegalMoveException;
import chesspresso.move.Move;

/**
 *
 * @author Bernhard Seybold
 * @version $Revision: 1.1 $
 */
public abstract class AbstractMoveablePosition extends AbstractMutablePosition
		implements MoveablePosition {

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