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

/**
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public interface PositionListener
{
    public void squareChanged(int sqi, int stone);
    public void toPlayChanged(int toPlay);
    public void castlesChanged(int castles);
    public void sqiEPChanged(int sqiEP);
    public void plyNumberChanged(int plyNumber);
    public void halfMoveClockChanged(int halfMoveClock);
}