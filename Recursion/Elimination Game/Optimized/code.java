import java.util.*;
class code {
    public int lastRemaining(int n) {
        boolean left = true;
        int head = 1;
        int step = 1;
        while(n>1){
            if((left == true) || (n&1) !=0){
                head = head + step;
            }
            step*=2;
            n/=2;
            left = !left;

        }

        return head;
        
    }

    
}