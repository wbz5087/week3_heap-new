/**
 * Created by wubingzhang on 4/23/16.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.List;

/**
 *
 * @author Ginxi
 */
public class heapGUI extends JFrame {
    private static Integer[] str;
    private static Heap<Integer> heapStr;
    private static JTextField inputQueue = new JTextField(50);
    private static JTextField outputQueue = new JTextField(50);
    private static boolean isMax;
    private static boolean isInput;
    static JPanel desktop = new JPanel();
    static JPanel text = new JPanel();
    private int radius = 20;
    private List<Node> nodes = new ArrayList<Node>();

    public heapGUI(String title) {
        super(title);
        setLocation(550, 400);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        JLabel inputLabel = new JLabel("Input Queue:");
        JLabel outputLabel = new JLabel("Output Queue:");
        outputQueue.setEditable(false);
        text.add(inputLabel);
        text.add(inputQueue);
        text.add(outputLabel);
        text.add(outputQueue);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JButton inputBtn = new JButton("input");
        inputBtn.addActionListener(new input());
        JButton buildUHBtn = new JButton("Build Up Heap");
        buildUHBtn.addActionListener(new buildUH());
        JButton buildDHBtn = new JButton("Build Down Heap");
        buildDHBtn.addActionListener(new buildDH());
        JButton insertBtn = new JButton("Insert");
        insertBtn.addActionListener(new insert());
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new delete());
        JButton showBtn = new JButton("Show Queue");
        showBtn.addActionListener(new show());
        JButton showGBtn = new JButton("Show Graph");
        showGBtn.addActionListener(new showGraph());
        desktop.add(text, BorderLayout.LINE_START);
        desktop.add(content, BorderLayout.LINE_END);

        content.add(inputBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(buildUHBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(buildDHBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(insertBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(deleteBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(showBtn);
        content.add(Box.createVerticalStrut(12));
        content.add(showGBtn);

        setContentPane(desktop);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }                                //constructor

    private static class buildUH implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInput) {
                outputQueue.setText("Please input queues first.");
                return;
            }

            heapStr.build_max_heap();
            isMax = true;

            outputQueue.setText(heapStr.toString());

        }
    }

    private static class buildDH implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInput) {
                outputQueue.setText("Please input queues first.");
                return;
            }
            heapStr.build_min_heap();
            outputQueue.setText(heapStr.toString());
            isMax = false;
        }
    }

    private static class insert implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInput) {
                outputQueue.setText("Please input queues first.");
                return;
            }
            boolean isInserted = false;
            String str = inputQueue.getText().split(" ")[0];
            if (str.equals("")) {
                return;
            }
            inputQueue.setText("");
            try {
                if (isMax) {
                    isInserted = heapStr.insertMax(Integer.valueOf(str));
                } else {
                    isInserted = heapStr.insertMin(Integer.valueOf(str));
                }
            } catch (RuntimeException ex) {
                outputQueue.setText("Please input integer");
                return;
            }
            if (isInserted) {

                outputQueue.setText(heapStr.toString());

            } else {
                outputQueue.setText("Queue is full");
            }
        }
    }

    private static class delete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInput) {
                outputQueue.setText("Please input queues first.");
                return;
            }
            if (isMax) {

                heapStr.extract_max();

            } else {

                heapStr.extract_min();

            }

            outputQueue.setText(heapStr.toString());

        }
    }

    private static class input implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] temp = inputQueue.getText().split(" ");
            str = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                try {
                    str[i] = (Integer) Integer.parseInt(temp[i]);
                } catch (RuntimeException ex) {
                    outputQueue.setText("Please input integer");
                    str = new Integer[temp.length];
                    return;
                }
            }
            heapStr = new <Integer> Heap(str, str.length);

            heapStr = new <Integer> Heap(str, str.length);

            inputQueue.setText("");

            outputQueue.setText(heapStr.toString());

            isInput = true;

        }

    }


    private static class show implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInput) {
                outputQueue.setText("Please input queues first.");
                return;
            }

            String res = "";

            if (isMax) {

                str = heapStr.maxHeapsort();

            } else {

                str = heapStr.minHeapsort();
            }

            for (Integer str1 : str) {
                res += str1 + " ";
            }
            outputQueue.setText(res);
            // str = outputQueue.getText().split(" ");
            String[] temp = outputQueue.getText().split(" ");
            str = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                try {
                    str[i] = (Integer) Integer.parseInt(temp[i]);
                } catch (RuntimeException ex) {
                    outputQueue.setText("Please input integer");
                    str = new Integer[temp.length];
                    return;
                }
            }
            heapStr = new <String> Heap(str, str.length);

        }
    }

    public class showGraph implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel graph = new JPanel();
            //desktop.setVisible(false);
            setContentPane(new drawTree());
            setVisible(true);

            JButton back = new JButton("Back");
            back.setBounds(50,100,20,10);
            graph.add(back);

            //showGraph s = new showGraph();
             /*
            Graphics g = graph.getGraphics();
            g.clear();
            System.out.println(g.);
            drawTree(g);*/

        }

    class drawTree extends JPanel{
        private double x,y;
        List<Integer> List = new ArrayList<Integer>();
        // k = ;
        Node root = new Node();

        protected void paintComponent(Graphics g) {
            int x = 350, y = 20;
            super.paintComponent(g);
            //testTree(g);

            root.setValue(str[0]);
            int n = 0;
            buildTree(root,n);
            //showTree();
            int m = 1;
            drawTree(root,g,x,y,m);
        }

        protected void buildTree(Node root,int n){

            if(2*n+1<str.length){
                Node node = new Node();
                node.setValue(str[2*n+1]);
                root.setLeftChild(node);
                if(2*n+1<str.length/2) buildTree(node,2*n+1);
            }
            if(2*n+2<str.length){
                Node node = new Node();
                node.setValue(str[2*n+2]);
                root.setRightChild(node);
                if(2*n+2<str.length/2) buildTree(node,2*n+2);
            }
        }
       /* protected void showTree(){
            System.out.println(root.getValue());
            System.out.println(root.getLeftChild().getValue());
            System.out.println(root.getLeftChild().getRightChild().getValue());
            System.out.println(root.getLeftChild().getLeftChild().getLeftChild().getValue());

        }*/

        protected void drawTree(Node node, Graphics g,int x, int y, int n){
            n++;
            g.drawString(node.getValue().toString(),x,y);
            if(node.getLeftChild()!=null){
                g.drawLine(x+10,y,x-200/n+10,y+40);
                drawTree(node.getLeftChild(),g,x-200/n,y+50,n);
                System.out.println(node.getLeftChild().getValue());
            }
            if(node.getRightChild()!=null){
                g.drawLine(x+10,y,x+200/n+10,y+50-10);
                drawTree(node.getRightChild(),g,x+200/n,y+50,n);
                System.out.println(node.getRightChild().getValue());
            }
        }

    }
    }
}