import java.util.*;

public class Assignmnet8 {
    static class Graph {
        int n;
        List<Integer>[] adj;
        Graph(int n){this.n=n; adj=new ArrayList[n]; for(int i=0;i<n;i++) adj[i]=new ArrayList<>();}
        void addEdge(int u,int v){adj[u].add(v); adj[v].add(u);}
    }

    static int greedyColoring(Graph g){
        int[] color=new int[g.n]; Arrays.fill(color,-1);
        color[0]=0;
        boolean[] used=new boolean[g.n];
        for(int u=1;u<g.n;u++){
            Arrays.fill(used,false);
            for(int v:g.adj[u]) if(color[v]!=-1) used[color[v]]=true;
            int c; for(c=0;c<g.n;c++) if(!used[c]) break;
            color[u]=c;
        }
        return Arrays.stream(color).max().getAsInt()+1;
    }

    static int welshPowell(Graph g){
        Integer[] order=new Integer[g.n];
        for(int i=0;i<g.n;i++) order[i]=i;
        Arrays.sort(order,(a,b)->g.adj[b].size()-g.adj[a].size());
        int[] color=new int[g.n]; Arrays.fill(color,-1);
        int maxColor=0;
        for(int u:order){
            boolean[] used=new boolean[g.n];
            for(int v:g.adj[u]) if(color[v]!=-1) used[color[v]]=true;
            int c; for(c=0;c<g.n;c++) if(!used[c]) break;
            color[u]=c;
            if(c>maxColor) maxColor=c;
        }
        return maxColor+1;
    }

    static int dsatur(Graph g){
        int[] color=new int[g.n]; Arrays.fill(color,-1);
        int[] degree=new int[g.n];
        int[] sat=new int[g.n];
        for(int i=0;i<g.n;i++) degree[i]=g.adj[i].size();
        int colored=0;
        while(colored<g.n){
            int u=-1,maxSat=-1,maxDeg=-1;
            for(int i=0;i<g.n;i++){
                if(color[i]==-1 && (sat[i]>maxSat || (sat[i]==maxSat && degree[i]>maxDeg))){
                    maxSat=sat[i]; maxDeg=degree[i]; u=i;
                }
            }
            boolean[] used=new boolean[g.n];
            for(int v:g.adj[u]) if(color[v]!=-1) used[color[v]]=true;
            int c; for(c=0;c<g.n;c++) if(!used[c]) break;
            color[u]=c;
            colored++;
            for(int v:g.adj[u]){
                if(color[v]==-1){
                    Set<Integer> s=new HashSet<>();
                    for(int w:g.adj[v]) if(color[w]!=-1) s.add(color[w]);
                    sat[v]=s.size();
                }
            }
        }
        return Arrays.stream(color).max().getAsInt()+1;
    }

    static void allocateRooms(int slots,int availableRooms){
        if(slots<=availableRooms) System.out.println("All exams fit in available classrooms.");
        else System.out.println("Need "+(slots-availableRooms)+" more classrooms.");
    }

    public static void main(String[] args){
        Graph g=new Graph(7);
        g.addEdge(0,1); g.addEdge(0,2); g.addEdge(1,3);
        g.addEdge(2,3); g.addEdge(3,4); g.addEdge(4,5);
        g.addEdge(5,6); g.addEdge(1,6);
        int greedy=greedyColoring(g);
        int wp=welshPowell(g);
        int ds=dsatur(g);
        System.out.println("Greedy Coloring Slots: "+greedy);
        System.out.println("Welsh-Powell Slots: "+wp);
        System.out.println("DSATUR Slots: "+ds);
        allocateRooms(ds,4);
    }
}
