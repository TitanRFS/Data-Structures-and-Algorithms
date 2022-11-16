
package javaqueues;

import java.util.*;


public class JavaQueues {

   
    public static void main(String[] args) {
        Queue<Integer> queue= new PriorityQueue<Integer>();
        Queue<Integer> queue2= new PriorityQueue<Integer>();
        
        System.out.println("H queue πριν το add: "+queue);
        //Χρησιμοποιόντας το add βάζουμε καινούργια στοιχεία στην Queue μας
        //Π.χ Το 1-
        for(int i =0; i<5; i++){
            queue.add(i);
        }
        
        //Print
        System.out.println("Η queue μετά το add: "+queue);
        //2ή Μέθοδος peek()
        
        //Η μέθοδος peek χρησιμοποιείται για να δούμε το κεφάλι της Queue 
        int peek=queue.peek();
        System.out.println("Το κεφάλι της queue με το peek: "+peek);
        
        //Η μέθοδος element είναι διαφορετική από το peek διότι στην περίπτωση
        //που το queue είναι άδειο βγάζει error
        int element=queue.element();
        System.out.println("Το κεφάλι της queue με το element: "+element);
        //int element2=element(); <--- είναι άδειο οπότε θα βγάλει error
        //System.out.println(element2);
        
        
        //Η μέθοδος remove αφαιρεί το πρώτο στοιχείο από την Queue
        int remove=queue.remove();
        System.out.println("To queue μετά το remove: "+queue);
        
        //Η μέθοδος poll παίρνει το κεφάλι της Queue και το αφαιρεί
        //Άμα είναι κενό τότε βγάζει error
        int poll = queue.poll();
        System.out.println("To queue μετά το poll: "+queue);
        //int poll2=queue2.poll();
        //System.out.println(poll2);
        
        //Το offer παίρνει την τιμή που βάζει μέσα και την βάζει στο τέλος του queue 
        //και επιστρέφει μόνο τιμές Boolean/True όταν μπαίνει η τιμή μέσα στο queue
        //και false όταν δεν μπαίνει 
        boolean offer=queue.offer(14);
        System.out.println("To offer επιστρέφει τιμή boolean: "+offer);
        System.out.println("Το queue μετά το offer: "+queue);
        
        //Η μέθοδος size μας επιστρέφει το μέγεθος της queue
        int size=queue.size();
        System.out.println("Το queue έχει size: "+size);
        
    }
    
}
