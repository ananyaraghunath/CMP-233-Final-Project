/**
 * 
 */

/**
 * @author Professor Bamford
 *
 */
public class BinarySearchTreeNode<T extends Comparable<T>> 
{
	//Used to hold references to Binary Search Tree nodes for the linked implementation
	protected T info;  //The info in a Binary Search Tree node
	protected BinarySearchTreeNode<T> left;		//A link to the left child node
	protected BinarySearchTreeNode<T> right;	//A link to the right child node
	protected T point;
	protected String type = "";
	protected T answer;
	
	/**
	 * @param info
	 */
	public BinarySearchTreeNode(T info)
	{
		this.info = info;
		left = null;
		right = null;
		point = null;
		type = "";
	}
	
	/**
	 * @param info
	 * @param point
	 */
	public BinarySearchTreeNode(T info, T point)
	{
		this.info = info;
		this.point = point;
		left = null;
		right = null;
		type = "";
	}
	
	/**
	 * @param info
	 * @param point
	 * @param type
	 * @param answer
	 */
	public BinarySearchTreeNode(T info, T point, String type, T answer)
	{
		this.info = info;
		left = null;
		right = null;
		point = null;
		this.type = type;
		this.answer = answer;
	}
	
	/**
	 * @param info
	 * @param left
	 * @param right
	 */
	public BinarySearchTreeNode(T info, BinarySearchTreeNode<T> left, BinarySearchTreeNode<T> right)
	{
		this.info = info;
		this.left = left;
		this.right = right;
	}
	
	/*
	 * Sets info of this Binary Search Node
	 */
	public void setInfo(T info)
	{
		this.info = info;
	}
	
	/*
	 * @return - returns info of this Binary Search Node
	 */
	public T getInfo()
	{
		return info;
	}
	
	/**
	 * @return
	 */
	public T getPoint() {
		return point;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String t)
	{
		this.type = t;
	}
	public T getAns() {
		return answer;
	}
	/*
	 * Sets left link of this Binary Search Tree Node
	 */
	public void setLeft(BinarySearchTreeNode<T> link)
	{
		left = link;
	}
	
	/*
	 * Sets right link of this Binary Search Tree Node
	 */
	public void setRight(BinarySearchTreeNode<T> link)
	{
		right = link;
	}
	
	/*
	 * @return - returns left link of this Binary Search Tree Node
	 */
	public BinarySearchTreeNode<T> getLeft()
	{
		return left;
	}
	
	/*
	 * @return - returns right link of this Binary Search Tree Node
	 */
	public BinarySearchTreeNode<T> getRight()
	{
		return right;
	}
	
	
	public void addNode(BinarySearchTreeNode<T> newNode)
	{
		int comp = newNode.info.compareTo(info);
		if(comp < 0)
		{
			if(left == null)
				left = newNode;
			else 
				left.addNode(newNode);
		}
		if(comp > 0)
		{
			if(right == null)
				right = newNode;
			else
				right.addNode(newNode);
		}
		
	}
	
	public boolean hasLeft()
	{
		return left != null;
	}
	
	public boolean hasRight()
	{
		return right != null;
	}
	
	public boolean isLeaf()
	{
		return (left == null) && (right == null);
	}
	
	/*
	 * Prints this node and all of its descendants in sorted order
	 */	
	public void printNodes()
	{
		if(left != null)
			left.printNodes();
		System.out.print(info + " ");
		if(right != null)
			right.printNodes();
	}
}