package homework1;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.Scanner;

public class Homework1 
{
    static int k = 100;
    static Tree t = new Tree();
    
    public static String[] inputAry;
  
    public static void main(String[] args) 
    {
        System.out.print("Input:");
        Scanner input = new Scanner(System.in);
        t.root = new Node();
       if (args.length > 0)
        {
            String inpt = args[0];
            inputAry =  inpt.split("");
        }
            String a = "251-*32*+";
            inputAry = a.split("");
           
        treeCreate(t.root);
        infix(t.root);
        System.out.println(" = " + calculate(t.root));
        EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				MyForm frame = new MyForm(); // แก้ส่งต่าไม่ออกครับ
				frame.setVisible(true);
			}
		});
    }
    
    public static void treeCreate(Node n) {
        if(k == 100 )
        {
            k = inputAry.length - 1;
        }
        n.key = inputAry[k];
        if(oPera(inputAry[k]))
        {
            n.Right = new Node();
            n.Left = new Node();
            k--;
            treeCreate(n.Right);
            treeCreate(n.Left);
        }
        else
        {
            k--;
        }
    } 
    
    public static int calculate(Node n)
    {
        if(oPera(n.key))
        {
            if(null==n.key){ return Integer.parseInt(n.key);}else switch (n.key) 
            {
                case "+":
                    return calculate(n.Left) + calculate(n.Right);
                case "-":
                    return calculate(n.Left) - calculate(n.Right);
                case "*":
                    return calculate(n.Left) * calculate(n.Right);
                case "/":
                    return calculate(n.Left) / calculate(n.Right); 
                default:
                    return Integer.parseInt(n.key);
            } 
        }
        else return Integer.parseInt(n.key);
    }
    
    public static boolean oPera(String m)
    {
        if(null == m)return false;
        else switch (m) 
        {
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
            default:
                return false;
        }
    }  
 
    public static void infix(Node n)
    {
        if(oPera(n.key))
        {
            if(n != t.root)
            {
                System.out.print("(");
            }
            infix(n.Left);
            System.out.print(n.key);    
            infix(n.Right);
            if(n != t.root)
            {
                System.out.print(")");
            }
        }
        else
        {
            System.out.print(n.key);
        }  
    }
    
    public static void inorder(Node n) 
    {
        if (oPera(n.key))
        {
            inorder(n.Left);
            System.out.print(n.key);
            inorder(n.Right);
        }
        else 
        {
            System.out.print(n.key);
        }
    }
    
    class MyForm extends JFrame {

        public DefaultMutableTreeNode inorder2(Node n) 
    {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode(n.key);
        if (n != null && n.Left != null && n.Right != null)
        {
            treeRoot.add(inorder2(n.Left));
            treeRoot.add(inorder2(n.Right));
        }
        
        return treeRoot;
    }
        
	public MyForm() {
            
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 249);
		setTitle("ThaiCreate.Com Java GUI Tutorial");
		getContentPane().setLayout(null);

		DefaultMutableTreeNode treeRoot = inorder2(t.root);


		final JTree tree = new JTree(treeRoot);
		tree.setBounds(28, 11, 209, 131);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(28, 11, 209, 169);

		scroll.setViewportView(tree);
		getContentPane().add(scroll);

		tree.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();

						JOptionPane.showMessageDialog(null, selectedNode
								.getUserObject().toString());
					}
				});

	}
}  
}