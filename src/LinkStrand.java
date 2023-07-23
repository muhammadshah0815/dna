import javax.swing.border.SoftBevelBorder;
public class LinkStrand implements IDnaStrand {

    private class Node{
        String info;
        Node next;

        public Node(String myInfo){
            info = myInfo;
        }

        public Node(String myInfo, Node myNext){
            info = myInfo;
            next = myNext;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex; 
    private Node myCurrent;
    private int myLocalIndex;

   

    public LinkStrand(String s){
        initialize(s);
    }


    public LinkStrand(){
        this("");
    }


    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) { 
        myFirst = new Node(source);
        myLast = myFirst; 
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) { 
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna);
        myLast.next = newNode;
        myLast = myLast.next;

        mySize += dna.length(); 
        myAppends ++; 

        return this; 
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand newStrand = new LinkStrand();

        Node current = myFirst;
        while(current != null){
            StringBuilder nextString = new StringBuilder(current.info); 
            nextString.reverse(); 
            Node nextNode = new Node(nextString.toString()); 
            nextNode.next = newStrand.myFirst; 
            newStrand.myFirst = nextNode; 
            current = current.next; 

            newStrand.mySize += nextString.length(); 
        }

        newStrand.myLast = myFirst; 
        return newStrand;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) throws IndexOutOfBoundsException {

        if (index < myIndex){ 
            if (index < 0){
                throw new IndexOutOfBoundsException("Index less than 0");
            }
            myIndex = 0; 
            myLocalIndex = 0;
            myCurrent = myFirst;    
        }   
        while ((index - myIndex) >= (myCurrent.info.length() - myLocalIndex)){
            myIndex += (myCurrent.info.length() - myLocalIndex); 
            myCurrent = myCurrent.next;
            myLocalIndex = 0;
            if (myCurrent == null){
                throw new IndexOutOfBoundsException("No nodes left in the strand.");
            }
        }
        return myCurrent.info.charAt(index - myIndex);
        
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        Node current = myFirst; 
        while(current != null){
            sb.append(current.info); 
            current = current.next; 
        }
        return sb.toString();
    }

    
}