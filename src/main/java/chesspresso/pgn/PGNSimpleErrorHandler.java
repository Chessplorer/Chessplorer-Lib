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

import java.io.*;

/**
 * Simple implementation of a PGN error handler. Write the errors and warnings
 * directly to a configured print stream.
 *
 * @author  Bernhard Seybold
 * @version $Revision: 1.1 $
 */
public class PGNSimpleErrorHandler implements PGNErrorHandler
{

    private PrintStream m_out;

    /*================================================================================*/

    public PGNSimpleErrorHandler(PrintStream out)
    {
        m_out = out;
    }

    /*================================================================================*/

    public void handleError(PGNSyntaxError error)
    {
        m_out.println(error);
    }

    public void handleWarning(PGNSyntaxError warning)
    {
        m_out.println(warning);
    }
}