import java.util.Scanner;

public class Homework1 
{
    static int k = 10;
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
    }
    
    public static void treeCreate(Node n) {
        if(k == 10 )
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
    
}
