//Name : Hatwate Vaibhav Motiram PRN : 123B1F030
//Date : 15/09/2025
import java.util.*;

public class Assignemt7 {
    static class Graph {
        int V;
        List<Integer>[] adj;
        Graph(int V){this.V=V; adj=new ArrayList[V]; for(int i=0;i<V;i++) adj[i]=new ArrayList<>();}
        void addEdge(int u,int v){adj[u].add(v); adj[v].add(u);}
    }

    static int greedyColoring(Graph g){
        int[] result=new int[g.V];
        Arrays.fill(result,-1);
        result[0]=0;
        boolean[] used=new boolean[g.V];
        for(int u=1;u<g.V;u++){
            Arrays.fill(used,false);
            for(int v:g.adj[u]) if(result[v]!=-1) used[result[v]]=true;
            int c; for(c=0;c<g.V;c++) if(!used[c]) break;
            result[u]=c;
        }
        return Arrays.stream(result).max().getAsInt()+1;
    }

    static int welshPowell(Graph g){
        Integer[] order=new Integer[g.V];
        for(int i=0;i<g.V;i++) order[i]=i;
        Arrays.sort(order,(a,b)->g.adj[b].size()-g.adj[a].size());
        int[] color=new int[g.V]; Arrays.fill(color,-1);
        int maxColor=0;
        for(int u:order){
            boolean[] used=new boolean[g.V];
            for(int v:g.adj[u]) if(color[v]!=-1) used[color[v]]=true;
            int c; for(c=0;c<g.V;c++) if(!used[c]) break;
            color[u]=c;
            if(c>maxColor) maxColor=c;
        }
        return maxColor+1;
    }

    static int dsatur(Graph g){
        int[] color=new int[g.V]; Arrays.fill(color,-1);
        int[] degree=new int[g.V];
        int[] sat=new int[g.V];
        for(int i=0;i<g.V;i++) degree[i]=g.adj[i].size();
        int colored=0;
        while(colored<g.V){
            int u=-1,maxSat=-1,maxDeg=-1;
            for(int i=0;i<g.V;i++){
                if(color[i]==-1 && (sat[i]>maxSat || (sat[i]==maxSat && degree[i]>maxDeg))){
                    maxSat=sat[i]; maxDeg=degree[i]; u=i;
                }
            }
            boolean[] used=new boolean[g.V];
            for(int v:g.adj[u]) if(color[v]!=-1) used[color[v]]=true;
            int c; for(c=0;c<g.V;c++) if(!used[c]) break;
            color[u]=c;
            colored++;
            for(int v:g.adj[u]){
                if(color[v]==-1){
                    Set<Integer> neighColors=new HashSet<>();
                    for(int w:g.adj[v]) if(color[w]!=-1) neighColors.add(color[w]);
                    sat[v]=neighColors.size();
                }
            }
        }
        return Arrays.stream(color).max().getAsInt()+1;
    }

    static void allocateRooms(int slots,int totalRooms){
        if(slots<=totalRooms) System.out.println("All exams can be accommodated in given rooms.");
        else System.out.println("Not enough rooms, need "+(slots-totalRooms)+" more rooms.");
    }

    public static void main(String[] args){
        Graph g=new Graph(6);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,5);
        int greedy=greedyColoring(g);
        int wp=welshPowell(g);
        int ds=dsatur(g);
        System.out.println("Greedy Coloring Slots: "+greedy);
        System.out.println("Welsh-Powell Slots: "+wp);
        System.out.println("DSATUR Slots: "+ds);
        allocateRooms(ds,4);
    }
}
