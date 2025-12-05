public class Dfs {

    private int bilesikMatris[][];

    private boolean ziyaret[];

    public Dfs(int boyut){
        bilesikMatris=new int[boyut][boyut];
        ziyaret=new boolean[boyut];
    }
    public void KenarEkle(int kenar1,int kenar2){
        bilesikMatris[kenar1][kenar2]=1;
        bilesikMatris[kenar2][kenar1]=1;
    }

    public void travel(int baslangic){
        ziyaret[baslangic]=true;

        for(int i=0;i<bilesikMatris.length;i++){
            if(bilesikMatris[baslangic][i]==1 && !ziyaret[i]){
                travel(i);
            }
        }
}
}
