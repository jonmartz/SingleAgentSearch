
public class TilePuzzleHeuristic implements IHeuristic
{

	@Override
	public double getHeuristic
			(
					IProblemState problemState
			)
	{
		// with conflicts
		TilePuzzleState state = (TilePuzzleState) problemState;
		int[][] tiles = state._tilePuzzle;
		int h = 0;

		int rows = tiles.length;
		int cols = tiles[0].length;

		// row/col delta between goal and current tile, in case col/row is correct but row/col is not
		int[][] deltaCols = new int[rows][cols];
		int[][] deltaRows = new int[rows][cols];
		int nonConflicting = rows*cols; // used to indicate non conflicting number in matrix

		// build delta matrixes and manhattan
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				int num = tiles[row][col];
				if (num == 0) {
					deltaRows[row][col] = nonConflicting;
					deltaCols[row][col] = nonConflicting;
					continue;
				}
				int goalRow = (num - 1)/cols;
				int goalCol = (num - 1)%cols;

				// update delta matrixes
				if (col == goalCol) deltaRows[row][col] = goalRow - row;
				else deltaRows[row][col] = nonConflicting;
				if (row == goalRow) deltaCols[row][col] = goalCol - col;
				else deltaCols[row][col] = nonConflicting;

				// add manhattan distances
				if (goalRow > row) h += (goalRow - row)*num;
				else h += (row - goalRow)*num;
				if (goalCol > col) h += (goalCol - col)*num;
				else h += (col - goalCol)*num;
			}
		}
		// Look for column conflicts
		for (int col = 0; col < cols; col++){
			for (int row = 0; row < rows-1; row++){
				int upper = deltaRows[row][col];
				int lower = deltaRows[row+1][col];
				if (upper != nonConflicting && upper > lower){
					h += 2*tiles[row+1][col];
					deltaRows[row+1][col] = nonConflicting;
				}
			}
		}
		// Look for row conflicts
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols-1; col++){
				int left = deltaCols[row][col];
				int right = deltaCols[row][col+1];
				if (left != nonConflicting && left > right){
					h += 2*tiles[row][col+1];
					deltaCols[row][col+1] = nonConflicting;
				}
			}
		}
		return h;

//		// without conflicts
//		TilePuzzleState state = (TilePuzzleState) problemState;
//		int[][] tiles = state._tilePuzzle;
//		int h = 0;
//
//		int rows = tiles.length;
//		int cols = tiles[0].length;
//
//		for (int row = 0; row < rows; row++){
//			for (int col = 0; col < cols; col++){
//				int num = tiles[row][col];
//				if (num == 0) continue;
//				int goalRow = (num - 1)/cols;
//				int goalCol = (num - 1)%cols;
//
//				// add manhattan distances
//				if (goalRow > row) h += (goalRow - row)*num;
//				else h += (row - goalRow)*num;
//				if (goalCol > col) h += (goalCol - col)*num;
//				else h += (col - goalCol)*num;
//			}
//		}
//		return h;
	}
}
