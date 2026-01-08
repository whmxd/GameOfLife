package hu.supercluster.gameoflife.game.transformer;

import hu.supercluster.gameoflife.game.cell.Cell;
import hu.supercluster.gameoflife.game.grid.Grid;
import hu.supercluster.gameoflife.game.rule.Rule;

public class SimpleGridTransformer<T extends Cell> implements GridTransformer<T> {
    private int[][] stateChanges;

    @Override
    public final void transform(Grid<T> grid, Rule<T> rule) {
        stateChanges = new int[grid.getSizeY()][grid.getSizeX()];
        computeNewGrid(grid, rule);
        applyNewGrid(grid);
    }

    protected void computeNewGrid(Grid<T> grid, Rule<T> rule) {
        for (int j = 0; j < grid.getSizeY(); j++) {
            for (int i = 0; i < grid.getSizeX(); i++) {
                stateChanges[j][i] = rule.apply(grid, i, j);
            }
        }
    }

    protected void applyNewGrid(Grid<T> grid) {
        for (int j = 0; j < grid.getSizeY(); j++) {
            for (int i = 0; i < grid.getSizeX(); i++) {
                grid.getCell(i, j).setState(stateChanges[j][i]);
            }
        }
    }
}
