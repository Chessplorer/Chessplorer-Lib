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

import java.io.*;

/**
 *
 * @author  Bernhard Seybold
 * @version $Revision: 1.1 $
 */
public class GameModel
{
    private GameHeaderModel m_headerModel;
    private GameMoveModel m_moveModel;

    /*================================================================================*/

    public GameModel()
    {
        m_headerModel = new GameHeaderModel();
        m_moveModel = new GameMoveModel();
    }

    public GameModel(GameHeaderModel headerModel, GameMoveModel moveModel)
    {
        m_headerModel = headerModel;
        m_moveModel = moveModel;
    }

    public GameModel(DataInput in, int headerMode, int movesMode) throws IOException
    {
        load(in, headerMode, movesMode);
    }

    /*================================================================================*/

    public GameHeaderModel getHeaderModel() {return m_headerModel;}
    public GameMoveModel getMoveModel() {return m_moveModel;}

    /*================================================================================*/

    public void load(DataInput in, int headerMode, int movesMode) throws IOException
    {
        m_headerModel = new GameHeaderModel(in, headerMode);
        m_moveModel = new GameMoveModel(in, movesMode);
    }

    public void save(DataOutput out, int headerMode, int movesMode) throws IOException
    {
        m_headerModel.save(out, headerMode);
        m_moveModel.save(out, movesMode);
    }

    /*================================================================================*/

    public int hashCode()
    {
        return getMoveModel().hashCode();
    }

    public boolean equals(Object obj)
    {
        if (obj == this) return true;  // =====>
        if (!(obj instanceof GameModel)) return false;  //=====>
        GameModel gameModel = (GameModel)obj;
        return gameModel.getMoveModel().equals(getMoveModel());
    }

    /*================================================================================*/

    public String toString() {return m_headerModel.toString();}
}