import java.util.*;

public class Assignment6{
    static class Item {
        int w, v;
        boolean perishable;
        Item(int w, int v, boolean p) { this.w=w; this.v=v; this.perishable=p; }
    }

    static int knapsackDP(List<Item> items, int W) {
        int n=items.size();
        int[][] dp=new int[n+1][W+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=W;j++){
                Item it=items.get(i-1);
                int val=it.v+(it.perishable?5:0);
                if(it.w<=j) dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-it.w]+val);
                else dp[i][j]=dp[i-1][j];
            }
        }
        return dp[n][W];
    }

    static int knapsackGreedy(List<Item> items,int W){
        items.sort((a,b)->Double.compare((b.v+(b.perishable?5:0))/(double)b.w,(a.v+(a.perishable?5:0))/(double)a.w));
        int total=0,cap=W;
        for(Item it:items){
            if(cap>=it.w){
                cap-=it.w;
                total+=it.v+(it.perishable?5:0);
            }
        }
        return total;
    }

    static void allocateMultipleTrucks(List<Item> items,int trucks,int capacity){
        for(int t=1;t<=trucks;t++){
            int val=knapsackDP(items,capacity);
            System.out.println("Truck "+t+" max utility: "+val);
        }
    }

    public static void main(String[] args){
        List<Item> items=new ArrayList<>();
        items.add(new Item(10,60,true));
        items.add(new Item(20,100,false));
        items.add(new Item(30,120,true));
        items.add(new Item(15,70,false));
        int W=50;
        int dpVal=knapsackDP(items,W);
        int greedyVal=knapsackGreedy(items,W);
        System.out.println("Dynamic Programming Max Utility: "+dpVal);
        System.out.println("Greedy Approx Utility: "+greedyVal);
        allocateMultipleTrucks(items,2,40);
    }
}
