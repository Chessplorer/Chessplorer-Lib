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
package chesspresso.pgn;

/**
 * Interface for handlers of PGN errors as produced by {@link PGNReader}.
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public interface PGNErrorHandler
{
    /**
     * Called in case of an error.
     *
     *@return the error
     */
    public void handleError(PGNSyntaxError error);

    /**
     * Called in case of a warning.
     *
     *@return the warning
     */
    public void handleWarning(PGNSyntaxError warning);
}