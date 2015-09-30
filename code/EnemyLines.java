class EnemyLines {




	public static void main(String[] args) {
	

		//Parsing the file

		int vertices = Integer.valueOf(StdIn.readLine());


		//Saving the names of the vertecies - we do not really use them - could just toss them
		String [] names = new String[vertices];

		for(int i =0; i< vertices; i++){
			names[i]= StdIn.readLine();
		}

		//initializing a flownetvork with edges
		FlowNetwork railWay = new FlowNetwork(vertices);


		//reading and loading the edges to railway
		int edges = Integer.valueOf(StdIn.readLine());


		for (int i = 0; i < edges; i++){

			String newEdge = StdIn.readLine();
			String [] splits = newEdge.split(" ");
			Double capacity =Double.valueOf(splits[2]);

			//setting value infinit for edges with capacity == -1
			if(capacity<0){capacity=Double.POSITIVE_INFINITY;}

			int v =Integer.valueOf(splits[0]);
			int w = Integer.valueOf(splits[1]);

			//since the graph is to be undirected - each edge must be loaded both ways
			FlowEdge thisEdge = new FlowEdge(v, w, capacity );
			FlowEdge backwardEdge = new FlowEdge(w, v, capacity );
			railWay.addEdge(thisEdge);
			railWay.addEdge(backwardEdge);


		}


		// setting origin and destination and loading FordFulkerson
		int s = 0, t = vertices-1;
		FordFulkerson fulkersonNewwork = new FordFulkerson(railWay, s,t);

		//Printing out the cut
         for (int v = 0; v < railWay.V(); v++) {
            if (fulkersonNewwork.inCut(v)){
            	for (FlowEdge e : railWay.adj(v)) {
          			if(!fulkersonNewwork.inCut(e.to())){
          				System.out.println(e);
          			}
          		}

        }

            };
        }
	}

	


