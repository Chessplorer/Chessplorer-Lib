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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import chesspresso.move.Move;
import chesspresso.position.NAG;

/**
 * Representation of moves of a chess game.
 *
 * @author Bernhard Seybold
 */
public class GameMoveModel
{

    private static final int MIN_ALLOC_SIZE = 262144;
	private final static boolean DEBUG = false;
    private final static boolean EXTRA_CHECKS = true;

    //======================================================================

    public final static int
        MODE_EVERYTHING = 0;

    final static short
        NO_MOVE            = Move.NO_MOVE,
        LINE_START         = Move.OTHER_SPECIALS,
        LINE_END           = Move.OTHER_SPECIALS +  1,
        COMMENT_START      = Move.OTHER_SPECIALS +  2,
        COMMENT_END        = Move.OTHER_SPECIALS +  3,
        PRE_COMMENT_START  = Move.OTHER_SPECIALS +  5,
        PRE_COMMENT_END    = Move.OTHER_SPECIALS +  6,
        NULL_MOVE          = Move.NULL_MOVE,
        NAG_BASE           = Move.OTHER_SPECIALS + 16,
        LAST_SPECIAL       = (short)(NAG_BASE + NAG.NUM_OF_NAGS);

    static {
        if (LAST_SPECIAL > Move.SPECIAL_MOVE + Move.NUM_OF_SPECIAL_MOVES) {
            throw new RuntimeException("Not enough space to define special moves for game move model");
        }
    }

    //======================================================================

    private short[] m_moves;
    private int m_size;
    private int m_hashCode;

    //======================================================================

    public GameMoveModel()
    {
        m_moves = new short[MIN_ALLOC_SIZE];
        m_moves[0] = LINE_START;
        m_moves[1] = LINE_END;
        m_size = 2;
        m_hashCode = 0;
    }

    public GameMoveModel(DataInput in, int mode) throws IOException
    {
        load(in, mode);
        m_hashCode = 0;  // TODO: store in file?
    }

    //======================================================================
    // invariant checking

    private void checkLegalCursor(int index)
    {
        if (index < 0) throw new RuntimeException("Illegal index " + index);
        if (index >= m_size) throw new RuntimeException("Illegal index " + index + " m_size=" + m_size);
        if (m_moves[index] != LINE_START && m_moves[index] != NULL_MOVE && !isMoveValue(m_moves[index]))
            throw new RuntimeException("No move at index " + index + " move=" + valueToString(m_moves[index]));
    }

    //======================================================================

    private static boolean isMoveValue(short value)    {return !Move.isSpecial(value);}
    private static boolean isNagValue(short value)   {return value >= NAG_BASE && value < NAG_BASE + NAG.NUM_OF_NAGS;}
    private static short getNagForValue(short value) {return (short)(value - NAG_BASE);}
    private static short getValueForNag(short nag)   {return (short)(nag + NAG_BASE);}

    //======================================================================

    private void changed()
    {
        m_hashCode = 0;
    }

    //======================================================================

    public boolean hasNag(int index, short nag)
    {
        if (DEBUG) {
            System.out.println("hasNag " + index + " nag " + nag);
            write(System.out);
        }

        short nagValue = getValueForNag(nag);
        short value;
        do {
            index++;
            value = m_moves[index];
            if (value == nagValue) return true;
        } while (isNagValue(value));

        return false;
    }

    public short[] getNags(int index)
    {
        if (EXTRA_CHECKS)
            if (!isMoveValue(m_moves[index]))
                throw new RuntimeException("No move at index " + index + " move=" + valueToString(m_moves[index]));

        int num = 0;
        while (isNagValue(m_moves[index + 1])) {index++; num++;}
        if (num == 0) {
            return null;
        } else {
            short[] nags = new short[num];
            // collect nags from back to front (most recently added last)
            for (int i = 0; i < num; i++) nags[i] = getNagForValue(m_moves[index - i]);
            return nags;
        }
    }

    public void addNag(int index, short nag)
    {
        if (DEBUG) {
            System.out.println("addNag " + index + " nag " + nag);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (!isMoveValue(m_moves[index]))
            	// ignore if there's no move: just don't add the NAG
            	return;

        makeSpace(index + 1, 1, false);  // most recent nag first
        m_moves[index + 1] = getValueForNag(nag);
        changed();

        if (DEBUG) write(System.out);
    }

    public boolean removeNag(int index, short nag)
    {
        if (DEBUG) {
            System.out.println("removeNag " + index + " nag " + nag);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (!isMoveValue(m_moves[index]))
                throw new RuntimeException("No move at index " + index + " val=" + valueToString(m_moves[index]));

        short nagValue = getValueForNag(nag);
        short value;
        boolean changed = false;
        do {
            index++;
            value = m_moves[index];
            if (value == nagValue) {
                while (isNagValue(m_moves[index + 1])) {
                    m_moves[index] = m_moves[index + 1];
                    index++;
                }
                m_moves[index] = NO_MOVE;
                changed = true;
                break;
            }
        } while (isNagValue(value));
        changed();

        if (DEBUG) write(System.out);
        return changed;
    }

    //======================================================================

    private int skipComment(int index)
    {
        if (m_moves[index] == COMMENT_START) {
            while (m_moves[index] != COMMENT_END) index++;
        } else if (m_moves[index] == COMMENT_END) {
            while (m_moves[index] != COMMENT_START) index--;
        } else if (m_moves[index] == PRE_COMMENT_START) {
        	while (m_moves[index] != PRE_COMMENT_END) index++;
        } else if (m_moves[index] == PRE_COMMENT_END) {
        	while (m_moves[index] != PRE_COMMENT_START) index--;
        } else {
            throw new RuntimeException("No comment start or end at index " + index + " move " + valueToString(m_moves[index]));
        }
        return index;
    }

    public String getComment(int index)
    {
        if (EXTRA_CHECKS)
            if (!isMoveValue(m_moves[index]) && index != 0)  // comment at index 0 allowed
                throw new RuntimeException("No move at index " + index + " move=" + valueToString(m_moves[index]));

        // skip all nags
        while(isNagValue(m_moves[index + 1])) index++;
        // skip pre move comments
        if (m_moves[index + 1] == PRE_COMMENT_START) {
        	while (m_moves[index] != PRE_COMMENT_END) index++;
        }

        if (m_moves[index + 1] == COMMENT_START) {
            StringBuffer sb = new StringBuffer();
            addCommentToStringBuffer(index, sb, COMMENT_START, COMMENT_END);
            return sb.toString();
        } else {
            return null;
        }
    }

    private void addCommentToStringBuffer(int index, StringBuffer sb, short commentStartToken, short commentEndToken) {
        index += 2;
        while (m_moves[index] != commentEndToken) {
            sb.append((char)m_moves[index]);
            index++;
        }
        if (m_moves[index + 1] == commentStartToken) {
            sb.append(" ");
            addCommentToStringBuffer(index, sb, commentStartToken, commentEndToken);
        }
    }

    public String getPreMoveComment(int index)
    {
        if (EXTRA_CHECKS)
            if (!isMoveValue(m_moves[index]) && index != 0)  // comment at index 0 allowed
                throw new RuntimeException("No move at index " + index + " move=" + valueToString(m_moves[index]));

        // skip all nags
        while(isNagValue(m_moves[index + 1])) index++;
        // skip other comments
        if (m_moves[index + 1] == COMMENT_START) {
        	while (m_moves[index] != COMMENT_END) index++;
        }

        if (m_moves[index + 1] == PRE_COMMENT_START) {
            StringBuffer sb = new StringBuffer();
            addCommentToStringBuffer(index, sb, PRE_COMMENT_START, PRE_COMMENT_END);
            return sb.toString();
        } else {
            return null;
        }
    }

    public boolean addComment(int index, String comment)
    {
        if (DEBUG) {
            System.out.println("addComment " + index+ " comment " + comment);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (index != 0 && !isMoveValue(m_moves[index]))
                throw new RuntimeException("No move at index " + index + " val=" + valueToString(m_moves[index]));

        if (comment == null || comment.length() == 0) return false;  // =====>

        // allow comments before first move (index == 0)
        if (index != 0) {
            while(isNagValue(m_moves[index + 1])) index++;
            if (m_moves[index + 1] == COMMENT_START) {
                index++;
                index = skipComment(index);
            }
        }
        makeSpace(index + 1, comment.length() + 2, false);
        m_moves[index + 1] = COMMENT_START;
        for (int i = 0; i < comment.length(); i++) {
            m_moves[index + 2 + i] = (short)comment.charAt(i);
        }
        m_moves[index + comment.length() + 2] = COMMENT_END;
        changed();

        if (DEBUG) write(System.out);
        return true;
    }

    public boolean addPreMoveComment(int index, String comment)
    {
        if (DEBUG) {
            System.out.println("addPreMoveComment " + index+ " comment " + comment);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (index != 0 && !isMoveValue(m_moves[index]))
                throw new RuntimeException("No move at index " + index + " val=" + valueToString(m_moves[index]));

        if (comment == null || comment.length() == 0) return false;  // =====>

        // allow comments before first move (index == 0)
        if (index != 0) {
            while(isNagValue(m_moves[index + 1])) index++;
        }
        makeSpace(index + 1, comment.length() + 2, false);
        m_moves[index + 1] = PRE_COMMENT_START;
        for (int i = 0; i < comment.length(); i++) {
            m_moves[index + 2 + i] = (short)comment.charAt(i);
        }
        m_moves[index + comment.length() + 2] = PRE_COMMENT_END;
        changed();

        if (DEBUG) write(System.out);
        return true;
    }

    public boolean removeComment(int index)
    {
        if (DEBUG) {
            System.out.println("removeComment " + index);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (index != 0 && !isMoveValue(m_moves[index]))
                throw new RuntimeException("No move at index " + index + " val=" + valueToString(m_moves[index]));

        // allow comments before first move (index == 0)
        if (index != 0) {
            while(isNagValue(m_moves[index + 1])) index++;
        }
        boolean isChanged = false;
        if (m_moves[index + 1] == COMMENT_START || m_moves[index + 1] == PRE_COMMENT_START) {
        	int i = skipComment(index + 1);
        	if (m_moves[i + 1] == COMMENT_START || m_moves[i + 1] == PRE_COMMENT_START) {
        		i = skipComment(i + 1);
        	}
            for (; i > index; i--) {
                m_moves[i] = NO_MOVE;
            }
            isChanged = true;
        }
        if (isChanged) changed();

        if (DEBUG) write(System.out);
        return isChanged;
    }

    public boolean setComment(int index, String comment)
    {
        boolean changed = removeComment(index);
        return addComment(index, comment) || changed;
    }

    //======================================================================

    public boolean hasLines()
    {
        for (int i=1; i<m_size; i++) {
            if (m_moves[i] == LINE_START) return true;
        }
        return false;
    }

    public int getTotalNumOfPlies()
    {
        int num = 0;
        for (int index = 0; index < m_size; index++) {
            if (isMoveValue(m_moves[index])) num++;
        }
        return num;
    }

    public int getTotalCommentSize()
    {
        boolean inComment = false;
        int num = 0;
        for (int i=0; i<m_size; i++) {
            short move = m_moves[i];
            if (move == COMMENT_END || move == PRE_COMMENT_END)   inComment = false;
            if (inComment) num++;
            if (move == COMMENT_START || move == PRE_COMMENT_START) inComment = true;
        }
        return num;
    }

    public short getMove(int index)
    {
        if (index >= 0 && index < m_size) {
            short move = m_moves[index];
            return (isMoveValue(move) ? move : NO_MOVE);
        } else {
            return NO_MOVE;
        }
    }

    /**
     *@return -1 if at the beginning of a line
     */
    public int goBack(int index, boolean gotoMainLine)
    {
        if (DEBUG) {
            System.out.println("goBack " + index + " " + gotoMainLine);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        if (index <= 0) return -1;  // =====>

        index--;
        int level = 0;
        while (index > 0) {
            short move = m_moves[index];
            if      (move == LINE_START)   {
                level--;
                if (level == -1) {
                    if (!gotoMainLine) {
                        index = -1; break;
                    } else {
                        index = goBack(index, false);  // now at main line's move
                        index = goBack(index, false);  // now one move back
                        break;
                    }
                }
            }
            else if (move == LINE_END)      level++;
            else if (isNagValue(move))      ;
            else if (move == COMMENT_START || move == PRE_COMMENT_START) ;  // error
            else if (move == COMMENT_END || move == PRE_COMMENT_END)   index = skipComment(index);
            else if (move == NO_MOVE)       ;
            else if (level == 0)            break; // =====>
            index--;
        }
        if (DEBUG) System.out.println("  --> " + index);
        return index;
    }

    /**
     * Advances one move in the current line.
     *
     * @param index the index of the current move
     *
     * @return the index of the next move. If the next move does not exist, the index
     * points to a LINE_END, where a next move should be inserted.
     */
    public int goForward(int index)
    {
        if (DEBUG) {
            System.out.println("goForward " + index);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        index++;
        int level = 0;
        while (index < m_size - 1) {
            short move = m_moves[index];
            if      (move == LINE_START)     level++;
            else if (move == LINE_END)      {level--; if (level < 0) break;}
            else if (isNagValue(move))       ;
            else if (move == COMMENT_START || move == PRE_COMMENT_START)  index = skipComment(index);
            else if (move == COMMENT_END || move == PRE_COMMENT_END)    ;  // error
            else if (move == NO_MOVE)        ;
            else if (level == 0)             break;
            index++;
        }
        if (DEBUG) System.out.println("  --> " + index);
        return index;
    }

    public int goForward(int index, int whichLine)
    {
        if (DEBUG) {
            System.out.println("goForward " + index + " " + whichLine);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        index = goForward(index);
        if (m_moves[index] != LINE_END && whichLine > 0) {
            index++;
            int level = 0;
            while (index < m_size - 1) {
                short move = m_moves[index];
                if      (move == LINE_START)          {level++; if (level == 1) whichLine--;}
                else if (move == LINE_END)            {level--; if (level < 0) break;}
                else if (isNagValue(move))             ;
                else if (move == COMMENT_START || move == PRE_COMMENT_START)  index = skipComment(index);
                else if (move == COMMENT_END || move == PRE_COMMENT_END)  ;  // error
                else if (move == NO_MOVE)              ;
                else if (level == 1 && whichLine == 0) break;
                else if (level == 0)                  {index = -1; break;}  // =====>   move on level 0 -> not enough lines
                index++;
            }
        }
        if (DEBUG) System.out.println("  --> " + index);
        return index;
    }

    public int getNumOfNextMoves(int index)
    {
        if (DEBUG) {
            System.out.println("getNumOfNextMoves " + index);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        index = goForward(index);
        if (m_moves[index] == LINE_END) return 0;   // =====>

        index++;
        int numOfMoves = 1;
        int level = 0;
        short move;
        while (index < m_size && level >= 0) {
            move = m_moves[index];
            if      (move == LINE_START)    level++;
            else if (move == LINE_END)     {level--; if (level == 0) numOfMoves++;}
            else if (isNagValue(move))      ;
            else if (move == COMMENT_START || move == PRE_COMMENT_START) index = skipComment(index);
            else if (move == COMMENT_END || move == PRE_COMMENT_END)   ;  // error
            else if (move == NO_MOVE)       ;
            else if (level == 0)            break;
            index++;
        }
        if (DEBUG) System.out.println("  --> " + numOfMoves);
        return numOfMoves;
    }

    public boolean hasNextMove(int index)
    {
        if (DEBUG) {
            System.out.println("hasNextMove " + index);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        boolean nextMove = isMoveValue(m_moves[goForward(index)]);
        if (DEBUG) System.out.println("  --> " + nextMove);
        return (nextMove);
    }

    //======================================================================

    private int findEarliestNoMove(int index)
    {
        while (index > 1 && m_moves[index - 1] == NO_MOVE) index--;
        return index;
    }

    private int findLatestNoMove(int index)
    {
        if (EXTRA_CHECKS)
            if (index < 1 || index > m_size)
                throw new RuntimeException("Index out of bounds " + index);
            else if (m_moves[index] != NO_MOVE)
                throw new RuntimeException("Expected no move  " + index);

        while (index > 0 && m_moves[index - 1] == NO_MOVE) index--;
        return index;
    }

    private void enlarge(int index, int size)
    {
        if (DEBUG) {
            System.out.println("enlarge " + index + " " + size);
            write(System.out);
        }

        short[] newMoves = new short[m_moves.length + size];
        System.arraycopy(m_moves, 0, newMoves, 0, index);
        System.arraycopy(m_moves, index, newMoves, index + size, m_size - index);
        java.util.Arrays.fill(newMoves, index, index + size, NO_MOVE);
        m_moves = newMoves;
        m_size += size;
        if (DEBUG) write(System.out);
    }

    private void makeSpace(int index, int spaceNeeded, boolean possiblyMakeMore)
    {
        if (DEBUG) {
            System.out.println("makeSpace " + index + " " + spaceNeeded);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            if (index < 1 || index >= m_size)
                throw new RuntimeException("Index out of bounds " + index + " size=" + m_size);

        for (int i = 0; i < spaceNeeded; i++) {
            if (m_moves[index + i] != NO_MOVE) {
                // not enough space, make it
                if (m_size + spaceNeeded - i >= m_moves.length) {
                    int size = (spaceNeeded - i  < 8 && possiblyMakeMore ? 8 : spaceNeeded - i);
                    enlarge(index, size);
                } else {
                    System.arraycopy(m_moves, index + i, m_moves, index + spaceNeeded, m_size - (index + i));
                    java.util.Arrays.fill(m_moves, index + i, index + spaceNeeded, NO_MOVE);
                    m_size += spaceNeeded - i;
                }
                break;
            }
        }
        if (DEBUG) write(System.out);
    }

    public int appendAsRightMostLine(int index, short move)
    {
        if (DEBUG) {
            System.out.println("appendAsRightMostLine " + index + " " + Move.getString(move));
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        if (hasNextMove(index)) {
            index = goForward(index);  // go to the move for which an alternative is entered
            index = goForward(index);  // go to the end of all existing lines
            index = findEarliestNoMove(index);
            makeSpace(index, 3, true);
            m_moves[index]     = LINE_START;
            m_moves[index + 1] = move;
            m_moves[findLatestNoMove(index + 2)] = LINE_END;
            if (DEBUG) write(System.out);
            if (DEBUG) System.out.println("  --> " + index);
            changed();
            return index + 1;
        } else {
            index = goForward(index);
            index = findEarliestNoMove(index);
            makeSpace(index, 1, true);
            m_moves[index] = move;
            if (DEBUG) write(System.out);
            if (DEBUG) System.out.println("  --> " + index);
            changed();
            return index;
        }
    }

    public void deleteCurrentLine(int index)
    {
        if (DEBUG) {
            System.out.println("deleteCurrentLine " + index);
            write(System.out);
        }

        if (EXTRA_CHECKS)
            checkLegalCursor(index);

        int level = 0;
        boolean deleteLineEnd = false;

        // check if we stand at a line start
        for (int i=1; i<index; i++) {
            short move = m_moves[index - i];
            if      (move == LINE_START) {index -= i; deleteLineEnd = true; level = -1; break;}
            else if (move != NO_MOVE)     break;
        }

        boolean inComment = false;
        while (index < m_size) {
            short move = m_moves[index];
            if      (!inComment && move == LINE_START) level++;
            else if (!inComment && move == LINE_END)   level--;
            else if (move == COMMENT_START || move == PRE_COMMENT_START) inComment = true;
            else if (move == COMMENT_END || move == PRE_COMMENT_END) inComment = false;
            if (level == -1) {
                if (deleteLineEnd) m_moves[index] = NO_MOVE;
                break;
            }
            m_moves[index] = NO_MOVE;
            index++;
        }
        changed();
        if (DEBUG) write(System.out);
    }

    //======================================================================

    public int pack(int index)
    {
        if (DEBUG) {
            System.out.println("pack");
            write(System.out);
        }

        int newSize = 0;
        for (int i=0; i<m_size; i++) {
            if (m_moves[i] != NO_MOVE) newSize++;
        }

        short[] newMoves = new short[newSize + 1];
        int j = 0;
        for (int i=0; i<m_size; i++) {
            short move = m_moves[i];
            if (move != NO_MOVE) {
                newMoves[j++] = move;
            }
            if (i == index) index = j - 1;
        }

        m_moves = newMoves;
        m_moves[newSize] = LINE_END;
        m_size = newSize;

        if (DEBUG) write(System.out);
        if (DEBUG) System.out.println("  --> " + index);

        return index;
    }

    //======================================================================

    public void load(DataInput in, int mode) throws IOException
    {
        m_size = in.readInt() + 2;
        m_moves = new short[m_size];
        byte[] data = new byte[2 * (m_size - 2)];
        in.readFully(data);
        for (int i = 1; i < m_size - 1; i++) {
            // copied from RandomAccesFile.readShort
            m_moves[i] = (short)((data[2*i - 2] << 8) | (data[2*i - 1] & 0xFF));
        }
        m_moves[0]          = LINE_START;
        m_moves[m_size - 1] = LINE_END;
        changed();
        if (DEBUG) write(System.out);
    }

    public void save(DataOutput out, int mode) throws IOException
    {
        // do not save the guards at index 0 and m_size-1
        out.writeInt(m_size - 2);
        byte[] data = new byte[2 * (m_size - 2)];
        for (int i = 1; i < m_size - 1; i++) {
            short m = m_moves[i];
            // copied from RandomAccesFile.writeShort
            data[2*i - 2] = (byte)((m >>> 8) & 0xFF);
            data[2*i - 1] = (byte)((m >>> 0) & 0xFF);
        }
        out.write(data);
    }

    //======================================================================

    static String valueToString(short value)
    {
        if      (value == LINE_START)     return "(";
        else if (value == LINE_END)       return ")";
        else if (value == NO_MOVE)        return "NO";
        else if (value == COMMENT_START || value == PRE_COMMENT_START)  return "{";
        else if (value == COMMENT_END || value == PRE_COMMENT_END)      return "}";
        else if (isNagValue(value))       return "$" + getNagForValue(value);
        else                              return Move.getString(value);
    }

    public void write(PrintStream out)
    {
        boolean inComment = false;
        for (int i=0; i<m_size; i++) {
            short move = m_moves[i];
            if (move == COMMENT_END || move == PRE_COMMENT_END)   inComment = false;
            if (inComment) {
                out.print((char)move);
            } else {
                out.print(valueToString(m_moves[i]));
                out.print(" ");
            }
            if ((i % 20) == 19) out.println();
            if (move == COMMENT_START || move == PRE_COMMENT_START) inComment = true;
        }
        out.println();
    }

    public long getHashCode()
    {
        if (m_hashCode == 0) {
            int shift = 0;
            for (int index = 0; ; index = goForward(index)) {
                if (m_moves[index] == LINE_END) break;
                short move = getMove(index);
                m_hashCode ^= (long)move << shift;
                if (shift == 12) shift = 0; else shift++;
            }
        }
        return m_hashCode;
    }

    @Override
	public int hashCode()
    {
        return (int)getHashCode();
    }

    @Override
	public boolean equals(Object obj)
    {
        if (obj == this) return true;  // =====>
        if (!(obj instanceof GameMoveModel)) return false;  // =====>
        GameMoveModel gameMoveModel = (GameMoveModel)obj;

        if (gameMoveModel.getHashCode() != getHashCode()) return false;  // =====>

        int index1 = 0, index2 = 0;
        for (;;) {
            short move1 = m_moves[index1];
            short move2 = gameMoveModel.m_moves[index2];
            if (move1 == LINE_END && move2 == LINE_END) return true;  // =====>
            if (move1 != move2) return false;  // =====>
            index1 = goForward(index1);
            index2 = gameMoveModel.goForward(index2);
        }
    }

	public void deleteCurrentVariation(int index) {
		index = gotoVariationStart(index);
		int startOfVariation = index;
		while (m_moves[index] != LINE_END) {
			index = goForward(index);
		}
		int endOfVariation = index;
		for (int i = startOfVariation; i <= endOfVariation; i++) {
			m_moves[i] = NO_MOVE;
		}
		changed();
	}

	public int promoteVariation(int curMove) {
		int index = gotoVariationStart(curMove);
		int startOfVariation = index;
		int varFirstMoveStart = -1;
		int varFirstMoveEnd = -1;
		while (m_moves[index] != LINE_END) {
			index = goForward(index);
			if (varFirstMoveStart == -1) {
				// get start and end index of the first move in the variation
				// (to be copied to the parent line later)
				varFirstMoveStart = index;
				// exclusive end of first variation move
				varFirstMoveEnd = goForward(varFirstMoveStart);
				varFirstMoveStart = varFirstMoveStart - startOfVariation;
				varFirstMoveEnd = varFirstMoveEnd - startOfVariation;
			}
		}
		int endOfVariation = index;
		int parentMove = goBack(startOfVariation, true);
		if (startOfVariation > 0) {
			short[] variation = extractLine(startOfVariation, endOfVariation);
			Vector<short[]> otherVariations = extractOtherVariations(goBack(
					parentMove, false));

			short[] parentLine = extractParentLine(parentMove);

			int varFirstMoveLength = varFirstMoveEnd - varFirstMoveStart;
			int nextMoveIndex = insertMove(parentMove, variation,
					varFirstMoveStart, varFirstMoveLength);
			m_moves[nextMoveIndex] = LINE_START;
			nextMoveIndex++;
			nextMoveIndex = insertVariation(nextMoveIndex, parentLine);
			for (short[] otherVariation : otherVariations) {
				nextMoveIndex = insertVariation(nextMoveIndex, otherVariation);
			}
			addMovesToEndOfVariation(nextMoveIndex, variation, varFirstMoveEnd);
			changed();
			return pack(parentMove);
		} else {
			return -1;
		}
	}

	private int insertVariation(int nextMoveIndex, short[] variation) {
		int spaceNeeded = variation.length;
		enlarge(nextMoveIndex, spaceNeeded);
		System.arraycopy(variation, 0, m_moves, nextMoveIndex, variation.length);
		return nextMoveIndex + variation.length;
	}

	private short[] extractParentLine(int startParentLine) {
		int endParentLine = startParentLine;
		while (m_moves[endParentLine] != LINE_END) {
			endParentLine = goForward(endParentLine);
		}
		return extractLine(startParentLine, endParentLine);
	}

	private short[] extractLine(int start, int end) {
		short[] result = new short[end - start + 1];
		System.arraycopy(m_moves, start, result, 0, result.length);
		for (int i = start; i <= end; i++) {
			m_moves[i] = NO_MOVE;
		}
		return result;
	}

	/**
	 * Move other variations of the same move to the end of the game
	 *
	 * @param parentMove
	 */
	private Vector<short[]> extractOtherVariations(int parentMove) {
		int index = -1;
		int line = 1;
		Vector<short[]> result = new Vector<short[]>();
		do {
			index = goForward(parentMove, line);
			if (index != -1 && m_moves[index] != LINE_END) {
				// move variation to the end
				short[] variation = moveVariationToEnd(index);
				result.add(variation);
			}
		} while (index != -1 && m_moves[index] != LINE_END);
		return result;
	}

	/**
	 * move the variation at index to the end of the parent line
	 *
	 * @param index
	 */
	private short[] moveVariationToEnd(int index) {
		int startOfVariation = gotoVariationStart(index);
		while (m_moves[index] != LINE_END) {
			index = goForward(index);
		}
		int endOfVariation = index;
		short[] variation = extractLine(startOfVariation, endOfVariation);
		return variation;
	}

	/**
	 * @return the index of the start of the variation
	 */
	private int gotoVariationStart(int index) {
		while (m_moves[index] != LINE_START) {
			index--;
		}
		return index;
	}

	/**
	 * Insert a move from another array
	 */
	private int insertMove(int destIndex, short[] source, int srcPos, int length) {
		makeSpace(destIndex, length + 1, false);
		System.arraycopy(source, srcPos, m_moves, destIndex, length);
		return destIndex + length + 1;
	}

	private void addMovesToEndOfVariation(int index, short[] moves,
			int sourceStart) {
		int spaceNeeded = moves.length - sourceStart;
		enlarge(index, spaceNeeded);
		System.arraycopy(moves, sourceStart, m_moves, index, moves.length
				- sourceStart);
	}
}