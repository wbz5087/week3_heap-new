/**
 * Created by wubingzhang on 4/24/16.
 */
public class Node {
    private Node leftChild;
    private Node rightChild;
    private Integer value;

    public Node getLeftChild(){
        return leftChild;
    }

    public void setLeftChild(Node node){
        this.leftChild = node;
    }

    public Node getRightChild(){return rightChild;}

    public void setRightChild(Node node){this.rightChild = node;}

    public Integer getValue(){return value;}

    public void setValue(Integer value){this.value = value;}

}
