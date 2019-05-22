class minimax{
	static final int data[] = 
	{15, 4, 1, 17, 9, -20, -19, 12, 23, -22, 10, 19, -17, 0, -16, 11, -8, 2, -3, 16, -21, -15, -6, -10, -24};
	// {-2,-81,-10,-62,29,-31,-52,-39,-69,23,-45,-35,1,-20,69,-62,32,-27,90,21,-94,-93,-61,11,50,-1,-56};
	static final int branch = 5;
	static int tree[];
	static int depth;

	static int max(int x, int y){//取max
		if(x > y)
			return x;
		else
			return y;
	}

	static int min(int x, int y){//取min
		if(x < y)
			return x;
		else
			return y;
	}

	static void calcDepth(){//計算深度
		int n = (int)Math.ceil(Math.log(data.length) / Math.log(branch));
		depth = n;
	}

	static void build(){//創建樹
		int n = data.length;

		for(int i = 0; i < depth; i++)
			n += Math.pow(branch, i);

		tree = new int[n];
		for(int i = 0; i < data.length; i++)
			tree[n - data.length + i] = data[i];
	}
//*********************************************
	static int minimax(int position, int alpha, int beta, boolean minimaxFlag){//
		if(position*branch + 1 >= tree.length){//最底層
			System.out.print(tree[position] + " ");
			return tree[position];
		}

		if(minimaxFlag){//max層 a
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < branch; i++){
				int pTemp = position*branch + 1 + i;//暫存子樹地址

				if(pTemp >= tree.length) continue;//超出範圍Tree範圍 跳過
				int value = minimax(pTemp, alpha, beta, !minimaxFlag);//遞迴
				max = max(max, value);//取最大值

				alpha = max(alpha, value);//儲存A B
				tree[position] = alpha;
				if(alpha >= beta)//alpha beta pruning
					break;
			}
			return max;
		}else{//min層 b
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < branch; i++){
				int pTemp = position*branch + 1 + i;
				
				if(pTemp >= tree.length) continue;
				int value = minimax(pTemp, alpha, beta, !minimaxFlag);
				min = min(min, value);

				beta = min(beta, value);
				tree[position] = beta;
				if(alpha >= beta)
					break;
			}
			return min;
		}
	}
	//************************************************
	static String printArray(int x[]){
		String s = "";
		for(int i = 0; i < x.length; i++)
			s += String.valueOf(x[i]) + " ";
		return s;
	} 
	//************************************************
	public static void main(String[] args) {
		calcDepth();
		build();
		
		System.out.println("*** data Len:" + data.length + ", tree Len:" + tree.length + ", depth:" + depth + "(branch:" + branch + ") ***");
		System.out.print("Data : " + printArray(data) + "\nRun : ");
		System.out.println("\nAns : " + minimax(0, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
		System.out.println("\nTree structure : " + printArray(tree));
	}
}