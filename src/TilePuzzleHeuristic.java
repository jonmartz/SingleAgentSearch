
public class TilePuzzleHeuristic implements IHeuristic
{

	@Override
	public double getHeuristic
			(
					IProblemState problemState
			)
	{
		TilePuzzleState state = (TilePuzzleState) problemState;
		int[][] tilePuzzle = state._tilePuzzle;
		int h = 0;
		int rows = tilePuzzle.length;
		int cols = tilePuzzle[0].length;

		// build delta matrixes and manhattan
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				int num = tilePuzzle[row][col];
				if (num == 0) continue;
				int goalRow = (num - 1)/cols;
				int goalCol = (num - 1)%cols;

				// add manhattan distances
				if (goalRow > row) h += (goalRow - row)*num;
				else h += (row - goalRow)*num;
				if (goalCol > col) h += (goalCol - col)*num;
				else h += (col - goalCol)*num;
			}
		}
		return h;
	}
}
