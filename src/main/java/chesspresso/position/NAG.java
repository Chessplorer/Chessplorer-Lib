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

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;


/**
 * Support for NAG (Numeric Annotation Glyphs).
 *
 * @author Bernhard Seybold
 * @author Andreas Rudolph
 */
public enum NAG
{
    /**
     * null annotation
     */
    NULL_ANNOTATION( (short) 0, null ),

    /**
     * good move
     */
    GOOD_MOVE( (short) 1, "!" ),

    /**
     * poor move
     */
    POOR_MOVE( (short) 2, "?" ),

    /**
     * very good move
     */
    VERY_GOOD_MOVE( (short) 3, "!!" ),

    /**
     * very poor move
     */
    VERY_POOR_MOVE( (short) 4, "??" ),

    /**
     * speculative move
     */
    SPECULATIVE_MOVE( (short) 5, "!?" ),

    /**
     * questionable move
     */
    QUESTIONABLE_MOVE( (short) 6, "?!" ),

    /**
     * forced move (all others lose quickly)
     */
    FORCED_MOVE( (short) 7, null ),

    /**
     * only move (no reasonable alternatives)
     */
    ONLY_MOVE( (short) 8, null ),

    /**
     * worst move
     */
    WORST_MOVE( (short) 9, null ),

    /**
     * drawish position
     */
    DRAWISH_POSITION( (short) 10, null ),

    /**
     * equal chances, quiet position
     */
    EQUAL_CHANCES_QUIET_POSITION( (short) 11, null ),

    /**
     * equal chances, active position
     */
    EQUAL_CHANCES_ACTIVE_POSITION( (short) 12, null ),

    /**
     * unclear position
     */
    UNCLEAR_POSITION( (short) 13, null ),

    /**
     * White has a slight advantage
     */
    WHITE_HAS_SLIGHT_ADVANTAGE( (short) 14, "+=" ),

    /**
     * Black has a slight advantage
     */
    BLACK_HAS_SLIGHT_ADVANTAGE( (short) 15, "=+" ),

    /**
     * White has a moderate advantage
     */
    WHITE_HAS_MODERATE_ADVANTAGE( (short) 16, null ),

    /**
     * Black has a moderate advantage
     */
    BLACK_HAS_MODERATE_ADVANTAGE( (short) 17, null ),

    /**
     * White has a decisive advantage
     */
    WHITE_HAS_DECISIVE_ADVANTAGE( (short) 18, "+-" ),

    /**
     * Black has a decisive advantage
     */
    BLACK_HAS_DECISIVE_ADVANTAGE( (short) 19, "-+" ),

    /**
     * White has a crushing advantage (Black should resign)
     */
    WHITE_HAS_CRUSHING_ADVANTAGE( (short) 20, null ),

    /**
     * Black has a crushing advantage (White should resign)
     */
    BLACK_HAS_CRUSHING_ADVANTAGE( (short) 21, null ),

    /**
     * White is in zugzwang
     */
    WHITE_IN_ZUGZWANG( (short) 22, null ),

    /**
     * Black is in zugzwang
     */
    BLACK_IN_ZUGZWANG( (short) 23, null ),

    /**
     * White has a slight space advantage
     */
    WHITE_HAS_SLIGHT_SPACE_ADVANTAGE( (short) 24, null ),

    /**
     * Black has a slight space advantage
     */
    BLACK_HAS_SLIGHT_SPACE_ADVANTAGE( (short) 25, null ),

    /**
     * White has a moderate space advantage
     */
    WHITE_HAS_MODERATE_SPACE_ADVANTAGE( (short) 26, null ),

    /**
     * Black has a moderate space advantage
     */
    BLACK_HAS_MODERATE_SPACE_ADVANTAGE( (short) 27, null ),

    /**
     * White has a decisive space advantage
     */
    WHITE_HAS_DECISIVE_SPACE_ADVANTAGE( (short) 28, null ),

    /**
     * Black has a decisive space advantage
     */
    BLACK_HAS_DECISIVE_SPACE_ADVANTAGE( (short) 29, null ),

    /**
     * White has a slight time (development) advantage
     */
    WHITE_HAS_SLIGHT_DEVELOPMENT_ADVANTAGE( (short) 30, null ),

    /**
     * Black has a slight time (development) advantage
     */
    BLACK_HAS_SLIGHT_DEVELOPMENT_ADVANTAGE( (short) 31, null ),

    /**
     * White has a moderate time (development) advantage
     */
    WHITE_HAS_MODERATE_DEVELOPMENT_ADVANTAGE( (short) 32, null ),

    /**
     * Black has a moderate time (development) advantage
     */
    BLACK_HAS_MODERATE_DEVELOPMENT_ADVANTAGE( (short) 33, null ),

    /**
     * White has a decisive time (development) advantage
     */
    WHITE_HAS_DECISIVE_DEVELOPMENT_ADVANTAGE( (short) 34, null ),

    /**
     * Black has a decisive time (development) advantage
     */
    BLACK_HAS_DECISIVE_DEVELOPMENT_ADVANTAGE( (short) 35, null ),

    /**
     * White has the initiative
     */
    WHITE_HAS_INITIAVE( (short) 36, null ),

    /**
     * Black has the initiative
     */
    BLACK_HAS_INITIAVE( (short) 37, null ),

    /**
     * White has a lasting initiative
     */
    WHITE_HAS_LASTING_INITIAVE( (short) 38, null ),

    /**
     * Black has a lasting initiative
     */
    BLACK_HAS_LASTING_INITIAVE( (short) 39, null ),

    /**
     * White has the attack
     */
    WHITE_HAS_ATTACK( (short) 40, null ),

    /**
     * Black has the attack
     */
    BLACK_HAS_ATTACK( (short) 41, null ),

    /**
     * White has insufficient compensation for material deficit
     */
    WHITE_HAS_INSUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 42, null ),

    /**
     * Black has insufficient compensation for material deficit
     */
    BLACK_HAS_INSUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 43, null ),

    /**
     * White has sufficient compensation for material deficit
     */
    WHITE_HAS_SUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 44, null ),

    /**
     * Black has sufficient compensation for material deficit
     */
    BLACK_HAS_SUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 45, null ),

    /**
     * White has more than adequate compensation for material deficit
     */
    WHITE_HAS_ADEQUATE_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 46, null ),

    /**
     * Black has more than adequate compensation for material deficit
     */
    BLACK_HAS_ADEQUATE_COMPENSATION_FOR_MATERIAL_DEFICIT( (short) 47, null ),

    /**
     * White has a slight center control advantage
     */
    WHITE_HAS_SLIGHT_CENTER_CONTROL_ADVANTAGE( (short) 48, null ),

    /**
     * Black has a slight center control advantage
     */
    BLACK_HAS_SLIGHT_CENTER_CONTROL_ADVANTAGE( (short) 49, null ),

    /**
     * White has a moderate center control advantage
     */
    WHITE_HAS_MODERATE_CENTER_CONTROL_ADVANTAGE( (short) 50, null ),

    /**
     * Black has a moderate center control advantage
     */
    BLACK_HAS_MODERATE_CENTER_CONTROL_ADVANTAGE( (short) 51, null ),

    /**
     * White has a decisive center control advantage
     */
    WHITE_HAS_DECISIVE_CENTER_CONTROL_ADVANTAGE( (short) 52, null ),

    /**
     * Black has a decisive center control advantage
     */
    BLACK_HAS_DECISIVE_CENTER_CONTROL_ADVANTAGE( (short) 53, null ),

    /**
     * White has a slight kingside control advantage
     */
    WHITE_HAS_SLIGHT_KINGSIDE_CONTROL_ADVANTAGE( (short) 54, null ),

    /**
     * Black has a slight kingside control advantage
     */
    BLACK_HAS_SLIGHT_KINGSIDE_CONTROL_ADVANTAGE( (short) 55, null ),

    /**
     * White has a moderate kingside control advantage
     */
    WHITE_HAS_MODERATE_KINGSIDE_CONTROL_ADVANTAGE( (short) 56, null ),

    /**
     * Black has a moderate kingside control advantage
     */
    BLACK_HAS_MODERATE_KINGSIDE_CONTROL_ADVANTAGE( (short) 57, null ),

    /**
     * White has a decisive kingside control advantage
     */
    WHITE_HAS_DECISIVE_KINGSIDE_CONTROL_ADVANTAGE( (short) 58, null ),

    /**
     * Black has a decisive kingside control advantage
     */
    BLACK_HAS_DECISIVE_KINGSIDE_CONTROL_ADVANTAGE( (short) 59, null ),

    /**
     * White has a slight queenside control advantage
     */
    WHITE_HAS_SLIGHT_QUEENSIDE_CONTROL_ADVANTAGE( (short) 60, null ),

    /**
     * Black has a slight queenside control advantage
     */
    BLACK_HAS_SLIGHT_QUEENSIDE_CONTROL_ADVANTAGE( (short) 61, null ),

    /**
     * White has a moderate queenside control advantage
     */
    WHITE_HAS_MODERATE_QUEENSIDE_CONTROL_ADVANTAGE( (short) 62, null ),

    /**
     * Black has a moderate queenside control advantage
     */
    BLACK_HAS_MODERATE_QUEENSIDE_CONTROL_ADVANTAGE( (short) 63, null ),

    /**
     * White has a decisive queenside control advantage
     */
    WHITE_HAS_DECISIVE_QUEENSIDE_CONTROL_ADVANTAGE( (short) 64, null ),

    /**
     * Black has a decisive queenside control advantage
     */
    BLACK_HAS_DECISIVE_QUEENSIDE_CONTROL_ADVANTAGE( (short) 65, null ),

    /**
     * White has a vulnerable first rank
     */
    WHITE_HAS_VULNERABLE_FIRST_RANK( (short) 66, null ),

    /**
     * Black has a vulnerable first rank
     */
    BLACK_HAS_VULNERABLE_FIRST_RANK( (short) 67, null ),

    /**
     * White has a well protected first rank
     */
    WHITE_HAS_WELL_PROTECTED_FIRST_RANK( (short) 68, null ),

    /**
     * Black has a well protected first rank
     */
    BLACK_HAS_WELL_PROTECTED_FIRST_RANK( (short) 69, null ),

    /**
     * White has a poorly protected king
     */
    WHITE_HAS_POORLY_PROTECTED_KING( (short) 70, null ),

    /**
     * Black has a poorly protected king
     */
    BLACK_HAS_POORLY_PROTECTED_KING( (short) 71, null ),

    /**
     * White has a well protected king
     */
    WHITE_HAS_WELL_PROTECTED_KING( (short) 72, null ),

    /**
     * Black has a well protected king
     */
    BLACK_HAS_WELL_PROTECTED_KING( (short) 73, null ),

    /**
     * White has a poorly placed king
     */
    WHITE_HAS_POOR_KING_PLACEMENT( (short) 74, null ),

    /**
     * Black has a poorly placed king
     */
    BLACK_HAS_POOR_KING_PLACEMENT( (short) 75, null ),

    /**
     * White has a well placed king
     */
    WHITE_HAS_GOOD_KING_PLACEMENT( (short) 76, null ),

    /**
     * Black has a well placed king
     */
    BLACK_HAS_GOOD_KING_PLACEMENT( (short) 77, null ),

    /**
     * White has a very weak pawn structure
     */
    WHITE_HAS_VERY_WEAK_PAWN_STRUCTURE( (short) 78, null ),

    /**
     * Black has a very weak pawn structure
     */
    BLACK_HAS_VERY_WEAK_PAWN_STRUCTURE( (short) 79, null ),

    /**
     * White has a moderately weak pawn structure
     */
    WHITE_HAS_MODERATELY_WEAK_PAWN_STRUCTURE( (short) 80, null ),

    /**
     * Black has a moderately weak pawn structure
     */
    BLACK_HAS_MODERATELY_WEAK_PAWN_STRUCTURE( (short) 81, null ),

    /**
     * White has a moderately strong pawn structure
     */
    WHITE_HAS_MODERATELY_STRONG_PAWN_STRUCTURE( (short) 82, null ),

    /**
     * Black has a moderately strong pawn structure
     */
    BLACK_HAS_MODERATELY_STRONG_PAWN_STRUCTURE( (short) 83, null ),

    /**
     * White has a very strong pawn structure
     */
    WHITE_HAS_VERY_STRONG_PAWN_STRUCTURE( (short) 84, null ),

    /**
     * Black has a very strong pawn structure
     */
    BLACK_HAS_VERY_STRONG_PAWN_STRUCTURE( (short) 85, null ),

    /**
     * White has poor knight placement
     */
    WHITE_HAS_POOR_KNIGHT_PLACEMENT( (short) 86, null ),

    /**
     * Black has poor knight placement
     */
    BLACK_HAS_POOR_KNIGHT_PLACEMENT( (short) 87, null ),

    /**
     * White has good knight placement
     */
    WHITE_HAS_GOOD_KNIGHT_PLACEMENT( (short) 88, null ),

    /**
     * Black has good knight placement
     */
    BLACK_HAS_GOOD_KNIGHT_PLACEMENT( (short) 89, null ),

    /**
     * White has poor bishop placement
     */
    WHITE_HAS_POOR_BISHOP_PLACEMENT( (short) 90, null ),

    /**
     * Black has poor bishop placement
     */
    BLACK_HAS_POOR_BISHOP_PLACEMENT( (short) 91, null ),

    /**
     * White has good bishop placement
     */
    WHITE_HAS_GOOD_BISHOP_PLACEMENT( (short) 92, null ),

    /**
     * Black has good bishop placement
     */
    BLACK_HAS_GOOD_BISHOP_PLACEMENT( (short) 93, null ),

    /**
     * White has poor rook placement
     */
    WHITE_HAS_POOR_ROOK_PLACEMENT( (short) 94, null ),

    /**
     * Black has poor rook placement
     */
    BLACK_HAS_POOR_ROOK_PLACEMENT( (short) 95, null ),

    /**
     * White has good rook placement
     */
    WHITE_HAS_GOOD_ROOK_PLACEMENT( (short) 96, null ),

    /**
     * Black has good rook placement
     */
    BLACK_HAS_GOOD_ROOK_PLACEMENT( (short) 97, null ),

    /**
     * White has poor queen placement
     */
    WHITE_HAS_POOR_QUEEN_PLACEMENT( (short) 98, null ),

    /**
     * Black has poor queen placement
     */
    BLACK_HAS_POOR_QUEEN_PLACEMENT( (short) 99, null ),

    /**
     * White has good queen placement
     */
    WHITE_HAS_GOOD_QUEEN_PLACEMENT( (short) 100, null ),

    /**
     * Black has good queen placement
     */
    BLACK_HAS_GOOD_QUEEN_PLACEMENT( (short) 101, null ),

    /**
     * White has poor piece coordination
     */
    WHITE_HAS_POOR_PIECE_COORDINATION( (short) 102, null ),

    /**
     * Black has poor piece coordination
     */
    BLACK_HAS_POOR_PIECE_COORDINATION( (short) 103, null ),

    /**
     * White has good piece coordination
     */
    WHITE_HAS_GOOD_PIECE_COORDINATION( (short) 104, null ),

    /**
     * Black has good piece coordination
     */
    BLACK_HAS_GOOD_PIECE_COORDINATION( (short) 105, null ),

    /**
     * White has played the opening very poorly
     */
    WHITE_PLAYED_OPENING_VERY_POORLY( (short) 106, null ),

    /**
     * Black has played the opening very poorly
     */
    BLACK_PLAYED_OPENING_VERY_POORLY( (short) 107, null ),

    /**
     * White has played the opening poorly
     */
    WHITE_PLAYED_OPENING_POORLY( (short) 108, null ),

    /**
     * Black has played the opening poorly
     */
    BLACK_PLAYED_OPENING_POORLY( (short) 109, null ),

    /**
     * White has played the opening well
     */
    WHITE_PLAYED_OPENING_WELL( (short) 110, null ),

    /**
     * Black has played the opening well
     */
    BLACK_PLAYED_OPENING_WELL( (short) 111, null ),

    /**
     * White has played the opening very well
     */
    WHITE_PLAYED_OPENING_VERY_WELL( (short) 112, null ),

    /**
     * Black has played the opening very well
     */
    BLACK_PLAYED_OPENING_VERY_WELL( (short) 113, null ),

    /**
     * White has played the middlegame very poorly
     */
    WHITE_PLAYED_MIDDLEGAME_VERY_POORLY( (short) 114, null ),

    /**
     * Black has played the middlegame very poorly
     */
    BLACK_PLAYED_MIDDLEGAME_VERY_POORLY( (short) 115, null ),

    /**
     * White has played the middlegame poorly
     */
    WHITE_PLAYED_MIDDLEGAME_POORLY( (short) 116, null ),

    /**
     * Black has played the middlegame poorly
     */
    BLACK_PLAYED_MIDDLEGAME_POORLY( (short) 117, null ),

    /**
     * White has played the middlegame well
     */
    WHITE_PLAYED_MIDDLEGAME_WELL( (short) 118, null ),

    /**
     * Black has played the middlegame well
     */
    BLACK_PLAYED_MIDDLEGAME_WELL( (short) 119, null ),

    /**
     * White has played the middlegame very well
     */
    WHITE_PLAYED_MIDDLEGAME_VERY_WELL( (short) 120, null ),

    /**
     * Black has played the middlegame very well
     */
    BLACK_PLAYED_MIDDLEGAME_VERY_WELL( (short) 121, null ),

    /**
     * White has played the ending very poorly
     */
    WHITE_PLAYED_ENDGAME_VERY_POORLY( (short) 122, null ),

    /**
     * Black has played the ending very poorly
     */
    BLACK_PLAYED_ENDGAME_VERY_POORLY( (short) 123, null ),

    /**
     * White has played the ending poorly
     */
    WHITE_PLAYED_ENDGAME_POORLY( (short) 124, null ),

    /**
     * Black has played the ending poorly
     */
    BLACK_PLAYED_ENDGAME_POORLY( (short) 125, null ),

    /**
     * White has played the ending well
     */
    WHITE_PLAYED_ENDGAME_WELL( (short) 126, null ),

    /**
     * Black has played the ending well
     */
    BLACK_PLAYED_ENDGAME_WELL( (short) 127, null ),

    /**
     * White has played the ending very well
     */
    WHITE_PLAYED_ENDGAME_VERY_WELL( (short) 128, null ),

    /**
     * Black has played the ending very well
     */
    BLACK_PLAYED_ENDGAME_VERY_WELL( (short) 129, null ),

    /**
     * White has slight counterplay
     */
    WHITE_HAS_SLIGHT_COUNTERPLAY( (short) 130, null ),

    /**
     * Black has slight counterplay
     */
    BLACK_HAS_SLIGHT_COUNTERPLAY( (short) 131, null ),

    /**
     * White has moderate counterplay
     */
    WHITE_HAS_MODERATE_COUNTERPLAY( (short) 132, null ),

    /**
     * Black has moderate counterplay
     */
    BLACK_HAS_MODERATE_COUNTERPLAY( (short) 133, null ),

    /**
     * White has decisive counterplay
     */
    WHITE_HAS_DECISIVE_COUNTERPLAY( (short) 134, null ),

    /**
     * Black has decisive counterplay
     */
    BLACK_HAS_DECISIVE_COUNTERPLAY( (short) 135, null ),

    /**
     * White has moderate time control pressure
     */
    WHITE_HAS_MODERATE_TIME_PRESSURE( (short) 136, null ),

    /**
     * Black has moderate time control pressure
     */
    BLACK_HAS_MODERATE_TIME_PRESSURE( (short) 137, null ),

    /**
     * White has severe time control pressure
     */
    WHITE_HAS_SEVERE_TIME_PRESSURE( (short) 138, null ),

    /**
     * Black has severe time control pressure
     */
    BLACK_HAS_SEVERE_TIME_PRESSURE( (short) 139, null );

    private final static Logger LOGGER = LoggerFactory.getLogger( NAG.class );
    private final short nag;
    private final String shortDescription;

    // TODO add short NAG chars for chess fonts

    // allow higher nags
    public static short NUM_OF_NAGS = 256;

    private NAG( short nag, String shortDescription )
    {
      this.nag = nag;
      this.shortDescription = shortDescription;
    }

    public String getLongDescription()
    {
      return getLongDescription( null );
    }

    public String getLongDescription( Locale locale )
    {
      final I18n I18N = (locale!=null)?
        I18nFactory.getI18n( NAG.class, locale ):
        I18nFactory.getI18n( NAG.class );

      final String white = I18N.tr( "White" );
      final String black = I18N.tr( "Black" );

      switch (this)
      {
        case NULL_ANNOTATION:
          return I18N.tr( "no annotation" );  // 0

        case GOOD_MOVE:
          return I18N.tr( "good move" );  // 1

        case POOR_MOVE:
          return I18N.tr( "poor move" );  // 2

        case VERY_GOOD_MOVE:
          return I18N.tr( "very good move" );  // 3

        case VERY_POOR_MOVE:
          return I18N.tr( "very poor move" );  // 4

        case SPECULATIVE_MOVE:
          return I18N.tr( "speculative move" );  // 5

        case QUESTIONABLE_MOVE:
          return I18N.tr( "questionable move" );  // 6

        case FORCED_MOVE:
          return I18N.tr( "forced move (all others lose quickly)" );  // 7

        case ONLY_MOVE:
          return I18N.tr( "only move (no reasonable alternatives)" );  // 8

        case WORST_MOVE:
          return I18N.tr( "worst move" );  // 9

        case DRAWISH_POSITION:
          return I18N.tr( "drawish position" );  // 10

        case EQUAL_CHANCES_QUIET_POSITION:
          return I18N.tr( "equal chances" ) + "; " + I18N.tr( "quiet position" );  // 11

        case EQUAL_CHANCES_ACTIVE_POSITION:
          return I18N.tr( "equal chances" ) + "; " + I18N.tr( "active position" );  // 12

        case UNCLEAR_POSITION:
          return I18N.tr( "unclear position" );  // 13

        case WHITE_HAS_SLIGHT_ADVANTAGE:
          return I18N.tr( "{0} has a slight advantage", white );  // 14

        case BLACK_HAS_SLIGHT_ADVANTAGE:
          return I18N.tr( "{0} has a slight advantage", black );  // 15

        case WHITE_HAS_MODERATE_ADVANTAGE:
          return I18N.tr( "{0} has a moderate advantage", white );  // 16

        case BLACK_HAS_MODERATE_ADVANTAGE:
          return I18N.tr( "{0} has a moderate advantage", black );  // 17

        case WHITE_HAS_DECISIVE_ADVANTAGE:
          return I18N.tr( "{0} has a decisive advantage", white );  // 18

        case BLACK_HAS_DECISIVE_ADVANTAGE:
          return I18N.tr( "{0} has a decisive advantage", black );  // 19

        case WHITE_HAS_CRUSHING_ADVANTAGE:
          return I18N.tr( "{0} has a crushing advantage ({1} should resign)", white, black );  // 20

        case BLACK_HAS_CRUSHING_ADVANTAGE:
          return I18N.tr( "{0} has a crushing advantage ({1} should resign)", black, white );  // 21

        case WHITE_IN_ZUGZWANG:
          return I18N.tr( "{0} is in zugzwang", white );  // 22

        case BLACK_IN_ZUGZWANG:
          return I18N.tr( "{0} is in zugzwang", black );  // 23

        case WHITE_HAS_SLIGHT_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a slight space advantage", white );  // 24

        case BLACK_HAS_SLIGHT_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a slight space advantage", black );  // 25

        case WHITE_HAS_MODERATE_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a moderate space advantage", white );  // 26

        case BLACK_HAS_MODERATE_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a moderate space advantage", black );  // 27

        case WHITE_HAS_DECISIVE_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a decisive space advantage", white );  // 28

        case BLACK_HAS_DECISIVE_SPACE_ADVANTAGE:
          return I18N.tr( "{0} has a decisive space advantage", black );  // 29

        case WHITE_HAS_SLIGHT_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a slight time (development) advantage", white );  // 30

        case BLACK_HAS_SLIGHT_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a slight time (development) advantage", black );  // 31

        case WHITE_HAS_MODERATE_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a moderate time (development) advantage", white );  // 32

        case BLACK_HAS_MODERATE_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a moderate time (development) advantage", black );  // 33

        case WHITE_HAS_DECISIVE_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a decisive time (development) advantage", white );  // 34

        case BLACK_HAS_DECISIVE_DEVELOPMENT_ADVANTAGE:
          return I18N.tr( "{0} has a decisive time (development) advantage", black );  // 35

        case WHITE_HAS_INITIAVE:
          return I18N.tr( "{0} has the initiative", white );  // 36

        case BLACK_HAS_INITIAVE:
          return I18N.tr( "{0} has the initiative", black );  // 37

        case WHITE_HAS_LASTING_INITIAVE:
          return I18N.tr( "{0} has a lasting initiative", white );  // 38

        case BLACK_HAS_LASTING_INITIAVE:
          return I18N.tr( "{0} has a lasting initiative", black );  // 39

        case WHITE_HAS_ATTACK:
          return I18N.tr( "{0} has the attack", white );  // 40

        case BLACK_HAS_ATTACK:
          return I18N.tr( "{0} has the attack", black );  // 41

        case WHITE_HAS_INSUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has insufficient compensation for material deficit", white );  // 42

        case BLACK_HAS_INSUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has insufficient compensation for material deficit", black );  // 43

        case WHITE_HAS_SUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has sufficient compensation for material deficit", white );  // 44

        case BLACK_HAS_SUFFICIENT_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has sufficient compensation for material deficit", black );  // 45

        case WHITE_HAS_ADEQUATE_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has more than adequate compensation for material deficit", white );  // 46

        case BLACK_HAS_ADEQUATE_COMPENSATION_FOR_MATERIAL_DEFICIT:
          return I18N.tr( "{0} has more than adequate compensation for material deficit", black );  // 47

        case WHITE_HAS_SLIGHT_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight center control advantage", white );  // 48

        case BLACK_HAS_SLIGHT_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight center control advantage", black );  // 49

        case WHITE_HAS_MODERATE_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate center control advantage", white );  // 50

        case BLACK_HAS_MODERATE_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate center control advantage", black );  // 51

        case WHITE_HAS_DECISIVE_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive center control advantage", white );  // 52

        case BLACK_HAS_DECISIVE_CENTER_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive center control advantage", black );  // 53

        case WHITE_HAS_SLIGHT_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight kingside control advantage", white );  // 54

        case BLACK_HAS_SLIGHT_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight kingside control advantage", black );  // 55

        case WHITE_HAS_MODERATE_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate kingside control advantage", white );  // 56

        case BLACK_HAS_MODERATE_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate kingside control advantage", black );  // 57

        case WHITE_HAS_DECISIVE_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive kingside control advantage", white );  // 58

        case BLACK_HAS_DECISIVE_KINGSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive kingside control advantage", black );  // 59

        case WHITE_HAS_SLIGHT_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight queenside control advantage", white );  // 60

        case BLACK_HAS_SLIGHT_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a slight queenside control advantage", black );  // 61

        case WHITE_HAS_MODERATE_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate queenside control advantage", white );  // 62

        case BLACK_HAS_MODERATE_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a moderate queenside control advantage", black );  // 63

        case WHITE_HAS_DECISIVE_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive queenside control advantage", white );  // 64

        case BLACK_HAS_DECISIVE_QUEENSIDE_CONTROL_ADVANTAGE:
          return I18N.tr( "{0} has a decisive queenside control advantage", black );  // 65

        case WHITE_HAS_VULNERABLE_FIRST_RANK:
          return I18N.tr( "{0} has a vulnerable first rank", white );  // 66

        case BLACK_HAS_VULNERABLE_FIRST_RANK:
          return I18N.tr( "{0} has a vulnerable first rank", black );  // 67

        case WHITE_HAS_WELL_PROTECTED_FIRST_RANK:
          return I18N.tr( "{0} has a well protected first rank", white );  // 68

        case BLACK_HAS_WELL_PROTECTED_FIRST_RANK:
          return I18N.tr( "{0} has a well protected first rank", black );  // 69

        case WHITE_HAS_POORLY_PROTECTED_KING:
          return I18N.tr( "{0} has a poorly protected king", white );  // 70

        case BLACK_HAS_POORLY_PROTECTED_KING:
          return I18N.tr( "{0} has a poorly protected king", black );  // 71

        case WHITE_HAS_WELL_PROTECTED_KING:
          return I18N.tr( "{0} has a well protected king", white );  // 72

        case BLACK_HAS_WELL_PROTECTED_KING:
          return I18N.tr( "{0} has a well protected king", black );  // 73

        case WHITE_HAS_POOR_KING_PLACEMENT:
          return I18N.tr( "{0} has a poorly placed king", white );  // 74

        case BLACK_HAS_POOR_KING_PLACEMENT:
          return I18N.tr( "{0} has a poorly placed king", black );  // 75

        case WHITE_HAS_GOOD_KING_PLACEMENT:
          return I18N.tr( "{0} has a well placed king", white );  // 76

        case BLACK_HAS_GOOD_KING_PLACEMENT:
          return I18N.tr( "{0} has a well placed king", black );  // 77

        case WHITE_HAS_VERY_WEAK_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a very weak pawn structure", white );  // 78

        case BLACK_HAS_VERY_WEAK_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a very weak pawn structure", black );  // 79

        case WHITE_HAS_MODERATELY_WEAK_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a moderately weak pawn structure", white );  // 80

        case BLACK_HAS_MODERATELY_WEAK_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a moderately weak pawn structure", black );  // 81

        case WHITE_HAS_MODERATELY_STRONG_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a moderately strong pawn structure", white );  // 82

        case BLACK_HAS_MODERATELY_STRONG_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a moderately strong pawn structure", black );  // 83

        case WHITE_HAS_VERY_STRONG_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a very strong pawn structure", white );  // 84

        case BLACK_HAS_VERY_STRONG_PAWN_STRUCTURE:
          return I18N.tr( "{0} has a very strong pawn structure", black );  // 85

        case WHITE_HAS_POOR_KNIGHT_PLACEMENT:
          return I18N.tr( "{0} has poor knight placement", white );  // 86

        case BLACK_HAS_POOR_KNIGHT_PLACEMENT:
          return I18N.tr( "{0} has poor knight placement", black );  // 87

        case WHITE_HAS_GOOD_KNIGHT_PLACEMENT:
          return I18N.tr( "{0} has good knight placement", white );  // 88

        case BLACK_HAS_GOOD_KNIGHT_PLACEMENT:
          return I18N.tr( "{0} has good knight placement", black );  // 89

        case WHITE_HAS_POOR_BISHOP_PLACEMENT:
          return I18N.tr( "{0} has poor bishop placement", white );  // 90

        case BLACK_HAS_POOR_BISHOP_PLACEMENT:
          return I18N.tr( "{0} has poor bishop placement", black );  // 91

        case WHITE_HAS_GOOD_BISHOP_PLACEMENT:
          return I18N.tr( "{0} has good bishop placement", white );  // 92

        case BLACK_HAS_GOOD_BISHOP_PLACEMENT:
          return I18N.tr( "{0} has good bishop placement", black );  // 93

        case WHITE_HAS_POOR_ROOK_PLACEMENT:
          return I18N.tr( "{0} has poor rook placement", white );  // 94

        case BLACK_HAS_POOR_ROOK_PLACEMENT:
          return I18N.tr( "{0} has poor rook placement", black );  // 95

        case WHITE_HAS_GOOD_ROOK_PLACEMENT:
          return I18N.tr( "{0} has good rook placement", white );  // 96

        case BLACK_HAS_GOOD_ROOK_PLACEMENT:
          return I18N.tr( "{0} has good rook placement", black );  // 97

        case WHITE_HAS_POOR_QUEEN_PLACEMENT:
          return I18N.tr( "{0} has poor queen placement", white );  // 98

        case BLACK_HAS_POOR_QUEEN_PLACEMENT:
          return I18N.tr( "{0} has poor queen placement", black );  // 99

        case WHITE_HAS_GOOD_QUEEN_PLACEMENT:
          return I18N.tr( "{0} has good queen placement", white );  // 100

        case BLACK_HAS_GOOD_QUEEN_PLACEMENT:
          return I18N.tr( "{0} has good queen placement", black );  // 101

        case WHITE_HAS_POOR_PIECE_COORDINATION:
          return I18N.tr( "{0} has poor piece coordination", white );  // 102

        case BLACK_HAS_POOR_PIECE_COORDINATION:
          return I18N.tr( "{0} has poor piece coordination", black );  // 103

        case WHITE_HAS_GOOD_PIECE_COORDINATION:
          return I18N.tr( "{0} has good piece coordination", white );  // 104

        case BLACK_HAS_GOOD_PIECE_COORDINATION:
          return I18N.tr( "{0} has good piece coordination", black );  // 105

        case WHITE_PLAYED_OPENING_VERY_POORLY:
          return I18N.tr( "{0} has played the opening very poorly", white );  // 106

        case BLACK_PLAYED_OPENING_VERY_POORLY:
          return I18N.tr( "{0} has played the opening very poorly", black );  // 107

        case WHITE_PLAYED_OPENING_POORLY:
          return I18N.tr( "{0} has played the opening poorly", white );  // 108

        case BLACK_PLAYED_OPENING_POORLY:
          return I18N.tr( "{0} has played the opening poorly", black );  // 109

        case WHITE_PLAYED_OPENING_WELL:
          return I18N.tr( "{0} has played the opening well", white );  // 110

        case BLACK_PLAYED_OPENING_WELL:
          return I18N.tr( "{0} has played the opening well", black );  // 111

        case WHITE_PLAYED_OPENING_VERY_WELL:
          return I18N.tr( "{0} has played the opening very well", white );  // 112

        case BLACK_PLAYED_OPENING_VERY_WELL:
          return I18N.tr( "{0} has played the opening very well", black );  // 113

        case WHITE_PLAYED_MIDDLEGAME_VERY_POORLY:
          return I18N.tr( "{0} has played the middlegame very poorly", white );  // 114

        case BLACK_PLAYED_MIDDLEGAME_VERY_POORLY:
          return I18N.tr( "{0} has played the middlegame very poorly", black );  // 115

        case WHITE_PLAYED_MIDDLEGAME_POORLY:
          return I18N.tr( "{0} has played the middlegame poorly", white );  // 116

        case BLACK_PLAYED_MIDDLEGAME_POORLY:
          return I18N.tr( "{0} has played the middlegame poorly", black );  // 117

        case WHITE_PLAYED_MIDDLEGAME_WELL:
          return I18N.tr( "{0} has played the middlegame well", white );  // 118

        case BLACK_PLAYED_MIDDLEGAME_WELL:
          return I18N.tr( "{0} has played the middlegame well", black );  // 119

        case WHITE_PLAYED_MIDDLEGAME_VERY_WELL:
          return I18N.tr( "{0} has played the middlegame very well", white );  // 120

        case BLACK_PLAYED_MIDDLEGAME_VERY_WELL:
          return I18N.tr( "{0} has played the middlegame very well", black );  // 121

        case WHITE_PLAYED_ENDGAME_VERY_POORLY:
          return I18N.tr( "{0} has played the ending very poorly", white );  // 122

        case BLACK_PLAYED_ENDGAME_VERY_POORLY:
          return I18N.tr( "{0} has played the ending very poorly", black );  // 123

        case WHITE_PLAYED_ENDGAME_POORLY:
          return I18N.tr( "{0} has played the ending poorly", white );  // 124

        case BLACK_PLAYED_ENDGAME_POORLY:
          return I18N.tr( "{0} has played the ending poorly", black );  // 125

        case WHITE_PLAYED_ENDGAME_WELL:
          return I18N.tr( "{0} has played the ending well", white );  // 126

        case BLACK_PLAYED_ENDGAME_WELL:
          return I18N.tr( "{0} has played the ending well", black );  // 127

        case WHITE_PLAYED_ENDGAME_VERY_WELL:
          return I18N.tr( "{0} has played the ending very well", white );  // 128

        case BLACK_PLAYED_ENDGAME_VERY_WELL:
          return I18N.tr( "{0} has played the ending very well", black );  // 129

        case WHITE_HAS_SLIGHT_COUNTERPLAY:
          return I18N.tr( "{0} has slight counterplay", white );  // 130

        case BLACK_HAS_SLIGHT_COUNTERPLAY:
          return I18N.tr( "{0} has slight counterplay", black );  // 131

        case WHITE_HAS_MODERATE_COUNTERPLAY:
          return I18N.tr( "{0} has moderate counterplay", white );  // 132

        case BLACK_HAS_MODERATE_COUNTERPLAY:
          return I18N.tr( "{0} has moderate counterplay", black );  // 133

        case WHITE_HAS_DECISIVE_COUNTERPLAY:
          return I18N.tr( "{0} has decisive counterplay", white );  // 134

        case BLACK_HAS_DECISIVE_COUNTERPLAY:
          return I18N.tr( "{0} has decisive counterplay", black );  // 135

        case WHITE_HAS_MODERATE_TIME_PRESSURE:
          return I18N.tr( "{0} has moderate time control pressure", white );  // 136

        case BLACK_HAS_MODERATE_TIME_PRESSURE:
          return I18N.tr( "{0} has moderate time control pressure", black );  // 137

        case WHITE_HAS_SEVERE_TIME_PRESSURE:
          return I18N.tr( "{0} has severe time control pressure", white );  // 138

        case BLACK_HAS_SEVERE_TIME_PRESSURE:
          return I18N.tr( "{0} has severe time control pressure", black );  // 139

        default:
          return null;
      }
    }

    public short getNag()
    {
      return this.nag;
    }

    public String getShortDescription()
    {
      return this.shortDescription;
    }

    public static NAG valueOf( short nag )
    {
      if (nag<0) return null;
      for (NAG n : NAG.values())
      {
        if (n.nag==nag) return n;
      }
      return null;
    }

    /*================================================================================*/

    public static String[] getDefinedShortNags()
    {
      int num=0;
      for (NAG nag : NAG.values())
      {
        if (nag.shortDescription!=null)
        {
          num++;
        }
      }
      String[] res = new String[num];
      num=0;
      for (NAG nag : NAG.values())
      {
        if (nag.shortDescription!=null)
        {
          res[num++] = nag.shortDescription;
        }
      }
      return res;
    }

    /*================================================================================*/

    public static String getLongString( short nag )
    {
      NAG n = NAG.valueOf( nag );
      return (n!=null)?
        n.getLongDescription():
        "<unknown nag " + nag + ">";
    }

    public static String getShortString( short nag )
    {
      return getShortString( nag, true );
    }

    public static String getShortString( short nag, boolean takeLongIfNull )
    {
      NAG n = NAG.valueOf( nag );
      if (n==null)
      {
        return "$" + nag;
      }
      else if (n.shortDescription!=null)
      {
        return n.shortDescription;
      }
      else
      {
        return (takeLongIfNull)?
          n.getLongDescription():
          "$" + nag;
      }
    }

    public static short ofString( String description ) throws IllegalArgumentException
    {
      if (description!=null)
      {
        for (NAG nag : NAG.values())
        {
          if (description.equals( nag.shortDescription ))
            return nag.nag;
          if (description.equals( nag.getLongDescription( Locale.ENGLISH ) ))
            return nag.nag;
        }
      }
      throw new IllegalArgumentException( "Nag unknown " + description );
    }
}