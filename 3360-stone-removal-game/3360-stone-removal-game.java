class Solution {
    public boolean canAliceWin(int n) {
        int count = 10;
        boolean alice = true;
        while(n>=count){
            n-=count;
            count--;
            alice=!alice;
            //alice ni value false thase and pachi jo aa aceept na thayu aeno means shu thayo bob - na kari sakyo atle alice win thayo to ana turn nu revrese send kari de
            //jo alice true hoy and pachi ae - na kari sake to ae hari jay atle turn nu revrese api devanu send kari devanu 
            
        }
    return !alice;
    }
}