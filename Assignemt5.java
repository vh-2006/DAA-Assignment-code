import java.util.*;

public class Assignemt5 {
    static class Edge { int to; double w; Edge(int t,double ww){to=t;w=ww;} }
    static class Graph {
        int n;
        List<Edge>[] adj;
        Graph(int n){this.n=n; adj=new ArrayList[n]; for(int i=0;i<n;i++) adj[i]=new ArrayList<>();}
        void addEdge(int u,int v,double w){adj[u].add(new Edge(v,w));}
    }
    @FunctionalInterface
    interface WeightModifier { double modify(int u,int v,double base); }
    static class DResult { double dist; int target; int[] parent; DResult(double d,int t,int[] p){dist=d;target=t;parent=p;} }
    static DResult dijkstra(Graph g,int src,Set<Integer> targets,WeightModifier mod){
        double[] dist=new double[g.n];
        int[] parent=new int[g.n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -1);
        dist[src]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingDouble(a->a[0]));
        pq.add(new int[]{0,src});
        boolean[] seen=new boolean[g.n];
        while(!pq.isEmpty()){
            int[] cur=pq.poll();
            int u=cur[1];
            if(seen[u]) continue;
            seen[u]=true;
            if(targets!=null && targets.contains(u)) return new DResult(dist[u],u,parent);
            for(Edge e: g.adj[u]){
                int v=e.to;
                double w=mod==null?e.w:mod.modify(u,v,e.w);
                if(dist[u]+w < dist[v]){
                    dist[v]=dist[u]+w;
                    parent[v]=u;
                    pq.add(new int[]{(int)Double.doubleToLongBits(dist[v]),v});
                }
            }
        }
        return new DResult(Double.POSITIVE_INFINITY,-1,parent);
    }
    static List<Integer> buildPath(int[] parent,int src,int dest){
        LinkedList<Integer> path=new LinkedList<>();
        if(dest==-1) return path;
        int cur=dest;
        while(cur!=-1){
            path.addFirst(cur);
            if(cur==src) break;
            cur=parent[cur];
        }
        if(path.getFirst()!=src) return new ArrayList<>();
        return path;
    }
    static List<Integer> shortestPathThroughStages(Graph g,int source,int dest,List<Set<Integer>> stages,WeightModifier mod){
        List<Integer> full=new ArrayList<>();
        int curSource=source;
        if(stages!=null){
            for(Set<Integer> stage: stages){
                DResult r=dijkstra(g,curSource,stage,mod);
                if(r.target==-1) return Collections.emptyList();
                List<Integer> seg=buildPath(r.parent,curSource,r.target);
                if(seg.isEmpty()) return Collections.emptyList();
                if(!full.isEmpty()) seg.remove(0);
                full.addAll(seg);
                curSource=r.target;
            }
        }
        DResult last=dijkstra(g,curSource,Collections.singleton(dest),mod);
        if(last.target==-1) return Collections.emptyList();
        List<Integer> seg=buildPath(last.parent,curSource,dest);
        if(seg.isEmpty()) return Collections.emptyList();
        if(!full.isEmpty()) seg.remove(0);
        full.addAll(seg);
        return full;
    }
    public static void main(String[] args){
        Graph g=new Graph(9);
        g.addEdge(0,1,4); g.addEdge(0,2,2);
        g.addEdge(1,3,5); g.addEdge(2,1,1);
        g.addEdge(2,4,10); g.addEdge(3,5,3);
        g.addEdge(4,5,2); g.addEdge(5,6,6);
        g.addEdge(6,7,1); g.addEdge(7,8,2);
        Set<Integer> stage1=new HashSet<>(Arrays.asList(1,2));
        Set<Integer> stage2=new HashSet<>(Arrays.asList(5));
        List<Set<Integer>> stages=Arrays.asList(stage1,stage2);
        WeightModifier realtime=(u,v,base)->{
            double trafficMultiplier=1.0;
            if(u==2 && v==4) trafficMultiplier=1.5;
            return base*trafficMultiplier;
        };
        List<Integer> path=shortestPathThroughStages(g,0,8,stages,realtime);
        if(path.isEmpty()) System.out.println("no path");
        else {
            double total=0;
            for(int i=0;i<path.size();i++){
                System.out.print(path.get(i));
                if(i<path.size()-1){
                    System.out.print(" -> ");
                    int a=path.get(i), b=path.get(i+1);
                    for(Edge e: g.adj[a]) if(e.to==b){ total += realtime.modify(a,b,e.w); break; }
                }
            }
            System.out.println();
            System.out.println("cost: "+total);
        }
    }
}
