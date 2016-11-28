package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import jdk.nashorn.internal.ir.annotations.Immutable;


import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoordiante;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTile();

    private static Map<Integer,EmptyTile> createAllPossibleEmptyTile() {

        final  Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i=0; i <= 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordiante, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordiante, piece) : EMPTY_TILES_CACHE.get(tileCoordiante);
    }

    private  Tile(int tileCoordiante){
        this.tileCoordiante = tileCoordiante;
    }

    public  abstract boolean isTileOccupied();

    public  abstract Piece getPiece();

    public  static  final  class EmptyTile extends Tile{

    private   EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public  static final  class OccupiedTile extends Tile{
        private final Piece pieceOnTile;

    private   OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public  Piece getPiece() {
            return  this.pieceOnTile;
        }
    }
}
