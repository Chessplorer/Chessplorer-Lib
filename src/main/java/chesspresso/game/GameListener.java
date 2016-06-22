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
package chesspresso.game;

import chesspresso.move.Move;

/**
 * Listener for moves made on games.
 *
 * @author  Bernhard Seybold
 * @version $Revision: 1.1 $
 */
public interface GameListener
{
    public void notifyMove(Move move, short[] nags, String preMoveComment, String postMoveComment,
    		int plyNumber, int level);
    public void notifyLineStart(int level);
    public void notifyLineEnd(int level);
}