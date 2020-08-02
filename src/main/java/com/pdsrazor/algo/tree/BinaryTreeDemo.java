package com.pdsrazor.algo.tree;

import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;
import com.pdsrazor.algo.utils.RandomArrayGenerator;

import java.util.*;

public class BinaryTreeDemo extends MyDemo {
    public void usage() {
        System.out.println("BinaryTreeDemo:");
        System.out.println("  genRandom <size>");
        System.out.println("  heightOfBT <size>");
        System.out.println("  genBST <size>");
        System.out.println("  searchFromBST <size>");
        System.out.println("  searchFromBSTDup <size>");
        System.out.println("  insertToBST <size>");
        System.out.println("  deleteFromBST <size>");
        System.out.println("  deleteFromBSTDup <size>");
        System.out.println("  getPreCursorNodeFromBST <size>");
        System.out.println("  getPostCursorNodeFromBST <size>");
        System.out.println("  getMaxNodeFromBST <size>");
        System.out.println("  getMinNodeFromBST <size>");
        System.out.println("  walkNoRecur <size>");
    }

    public int doCmd(String args[]) {
        if (args[0].equals("genRandom")) {
            int size = Integer.parseInt(args[1]);
            BinaryTreeFactory factory = new BinaryTreeFactory(size);
            BinaryTreeNode root = factory.genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
            if (root != null) {
                printBinaryTree(root);
                graphPrintBinaryTree(root);
                printInOrder(root);
            }
            return 1;
        }
        if (args[0].equals("heightOfBT")) {
            int size = Integer.parseInt(args[1]);
            BinaryTreeFactory factory = new BinaryTreeFactory(size);
            BinaryTreeNode root = factory.genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
            if (root != null) {
                graphPrintBinaryTree(root);
                System.out.println("height of BT is: " + getHeightOfBT(root));
            }
            return 1;
        }
        if (args[0].equals("getPreCursorNodeFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            System.out.print("mid order print: ");
            if (bst.mRoot != null)
                walkInOrder(bst.mRoot, new PrintCallback());

            ret = bst.getPreCursorNodeFromBST(bst.mRoot, ret);    
            System.out.println("\ntarget: " + target
                + ", pre cursor: " + ((ret != null) ? ret.value : "not found"));
            return 1;
        }
        if (args[0].equals("getPostCursorNodeFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            System.out.print("mid order print: ");
            if (bst.mRoot != null)
                walkInOrder(bst.mRoot, new PrintCallback());
            System.out.println("\ntarget: " + target);
            ret = bst.getPostCursorNodeFromBST(bst.mRoot, ret);    
            System.out.println("\ntarget: " + target
                + ", post cursor: " + ((ret != null) ? ret.value : "not found"));
            return 1;
        }
        if (args[0].equals("getMaxNodeFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            ret = bst.getMaxNodeFromBST(bst.mRoot);    
            System.out.println("max node: " + ((ret != null) ? ret.value : "not found"));
            return 1;
        }
        if (args[0].equals("getMinNodeFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            ret = bst.getMinNodeFromBST(bst.mRoot);    
            System.out.println("min node: " + ((ret != null) ? ret.value : "not found"));
            return 1;
        }
        if (args[0].equals("searchFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            if (bst.mRoot != null)
                walkInOrder(bst.mRoot, new PrintCallback());
            System.out.println("\ntarget: " + target + ", ret: " + ((ret != null) ? ret.value : "not found"));
            return 1;
        }
        if (args[0].equals("searchFromBSTDup")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTreeDup bstDup = null;
            LinkedList<BinaryTreeNode> rets = null;
            do {
                bstDup = new BinarySearchTreeDup(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND_DUP);
                rets = bstDup.find(target);
            } while(rets.size() == 0);
            bstDup.print();
            if (bstDup.mRoot != null)
                walkInOrder(bstDup.mRoot, new PrintCallback());
            System.out.println("\ntarget: " + target + ", rets: " + rets);
            return 1;
        }
        if (args[0].equals("genBST")) {
            int size = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(size, 1000);
            BinaryTreeNode root = genBSTFromData(arr);
            graphPrintBinaryTree(root);
            if (root != null)
                walkInOrder(root, new PrintCallback());
            System.out.println();
            return 1;
        }
        if (args[0].equals("insertToBST")) {
            int size = Integer.parseInt(args[1]);
            BinarySearchTree bst = new BinarySearchTree(size);
            graphPrintBinaryTree(bst.mRoot);
            int data = new Random().nextInt(BinaryTreeFactory.BOUND);
            System.out.println("insert data: " + data);
            bst.insert(data);
            graphPrintBinaryTree(bst.mRoot);
            return 1;
        }
        if (args[0].equals("deleteFromBST")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTree bst = null;
            BinaryTreeNode ret = null;
            do {
                bst = new BinarySearchTree(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND);
                ret = bst.find(target);
            } while(ret == null);
            bst.print();
            System.out.println("delete data: " + ret.value);
            bst.delete(ret);
            graphPrintBinaryTree(bst.mRoot);
            return 1;
        }
        if (args[0].equals("deleteFromBSTDup")) {
            int size = Integer.parseInt(args[1]);
            int target = 0;
            BinarySearchTreeDup bstDup = null;
            LinkedList<BinaryTreeNode> rets = null;
            do {
                bstDup = new BinarySearchTreeDup(size);
                target = new Random().nextInt(BinaryTreeFactory.BOUND_DUP);
                rets = bstDup.find(target);
            } while(rets.size() == 0);
            bstDup.print();
            System.out.println("delete data: " + rets);
            bstDup.deleteList(rets);
            bstDup.print();
            return 1;
        }
        if (args[0].equals("walkNoRecur")) {
            int size = Integer.parseInt(args[1]);
            BinaryTreeFactory factory = new BinaryTreeFactory(size);
            BinaryTreeNode root = factory.genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
            if (root != null) {
                graphPrintBinaryTree(root);
                System.out.print("In order: ");
                walkInOrderNoRecur(root);
                System.out.print("Pre order: ");
                walkPreOrderNoRecur(root);
                System.out.print("Post order: ");
                walkPostOrderNoRecur(root);

                System.out.println("\n ---------recur result: -----------------");
                System.out.print("In order: ");
                walkInOrder(root, new PrintCallback());
                System.out.println();
                System.out.print("Pre order: ");
                walkPreOrder(root, new PrintCallback());
                System.out.println();
                System.out.print("Post order: ");
                walkPostOrder(root, new PrintCallback());
                System.out.println();
            }
            return 1;
        }
        return 0;
    }

    static int getHeightOfBT(BinaryTreeNode root) {
        if (root == null) return 0;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cur = 0;
        int curTotal = 1;
        int nextTotal = 0;
        int height = 0;

        while (!queue.isEmpty()) {
            if (cur == 0) {
                height++;  // 新的一层，高度+1
            }
            BinaryTreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                nextTotal++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextTotal++;
            }
            cur++;
            if (cur == curTotal) {
                cur = 0;
                curTotal = nextTotal;
                nextTotal = 0;
            }
        }
        return height;
    }

    // ----------------- 二叉查找树 -----------------------
    public static class BinarySearchTree {
        public BinaryTreeNode mRoot;

        public BinarySearchTree(int size) {
            mRoot = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_SORTED);
        }

        public void print() {
            graphPrintBinaryTree(mRoot);
        }

        public BinaryTreeNode find(int data) {
            BinaryTreeNode node = mRoot;
            while (node != null) {
                if (node.value == data) return node;
                else if (node.value > data) node = node.left;
                else node = node.right;
            }
            return null;
        }

        // 不需要维持平衡性的插入操作
        public void insert(int data) {
            BinaryTreeNode p = mRoot;
            while (true) {
                // 大小一致的，放到右子树
                if (data >= p.value) {
                    if (p.right == null) {
                        p.right = new BinaryTreeNode(data);
                        break;
                    }
                    p = p.right;
                }
                if (data < p.value) {
                    if (p.left == null) {
                        p.left = new BinaryTreeNode(data);
                        break;
                    }
                    p = p .left;
                }
            }
        }

        // 默认node一定存在
        // 返回null表示node为根节点
        private BinaryTreeNode findParent(BinaryTreeNode root, BinaryTreeNode node) {
            if (node == root) return null;
            BinaryTreeNode p, pp;
            p = root;
            pp = null;
            while (p != null) {
                if (p.value > node.value) {
                    pp = p;
                    p = p.left;
                } else if (p.value < node.value) {
                    pp = p;
                    p = p.right;
                } else {
                    break;
                }
            }
            return pp;
        }

        // 交换两个节点的位置，不是拷贝值
        // 默认两个节点都存在
        // 返回值不为null表示root发生了变化，返回的是新root
        private BinaryTreeNode swapNode(BinaryTreeNode root, BinaryTreeNode p1, BinaryTreeNode p2) {
            if (p1 == null || p2 == null || p1 == p2) return null;
  
            BinaryTreeNode pp1, pp2, newRoot, parent;
            pp1 = pp2 = newRoot = parent = null;
            
            pp1 = findParent(root, p1);
            pp2 = findParent(root, p2);

            if (pp1 == null) newRoot = p2; // p1是root，交换后p2为新root
            if (pp2 == null) newRoot = p1;

            if (pp1 == p2) parent = p2; // p2是p1的父节点
            if (pp2 == p1) parent = p1; // p1是p2的父节点

            // 交换的两个节点不是父子关系
            if (parent == null) {
                BinaryTreeNode lp, rp;
                lp = p1.left;
                rp = p1.right;

                p1.left = p2.left;
                p1.right = p2.right;
                p2.left = lp;
                p2.right = rp;

                if (pp1 != null) {
                    if (pp1.left == p1) pp1.left = p2;
                    if (pp1.right == p1) pp1.right = p2;
                }
                if (pp2 != null) {
                    if (pp2.left == p2) pp2.left = p1;
                    if (pp2.right == p2) pp2.right = p1;
                }
            } else { // 交换的两个节点是父子节点 要特殊处理
                BinaryTreeNode lp, rp;
                lp = p1.left;
                rp = p1.right;

                p1.left = p2.left;
                p1.right = p2.right;
                p2.left = lp;
                p2.right = rp;
                if (p1.left == p1 || p1.right == p1) {
                    if (p1.left == p1) {
                        p1.left = p2;
                    } else if (p1.right == p1) {
                        p1.right = p2;
                    }
                    if (pp2 != null) {
                        if (pp2.left == p2) pp2.left = p1;
                        if (pp2.right == p2) pp2.right = p1;
                    }
                }
                if (p2.left == p2 || p2.right == p2) {
                    if (p2.left == p2) {
                        p2.left = p1;
                    } else if (p2.right == p2) {
                        p2.right = p1;
                    }
                    if (pp1 != null) {
                        if (pp1.left == p1) pp1.left = p2;
                        if (pp1.right == p1) pp1.right = p2;
                    }
                }
            }

            return newRoot;
        }

        // 不是BST情况下的查找parent
        public BinaryTreeNode findParentNoBST(BinaryTreeNode root, BinaryTreeNode node) {
            if (root.left == node || root.right == node ) return root;

            BinaryTreeNode ret = null;
            
            if (root.left != null)
                ret = findParentNoBST(root.left, node);
            if (ret == null && root.right != null)
                ret = findParentNoBST(root.right, node);
            
            return ret;
        }

        public boolean delete(BinaryTreeNode delNode) {
            if (mRoot == null) return false;
            if (find(delNode.value) == null) return false; // 要删除的节点不存在
            // 被删除的节点有两个子树
            // 找到右子树中最小的节点，替换被删除节点
            // 然后删除这个最小节点，由于最小节点肯定没有左子树， 按照 被删除的是叶子节点 或 被删除的节点只有一个子树 情况处理
            if (delNode.left != null && delNode.right != null) {
                // 找到右子树中最小的节点
                BinaryTreeNode rmin = delNode.right;
                // BinaryTreeNode rminP = delNode;
                while (rmin.left != null) {
                    // rminP = rmin;
                    rmin = rmin.left;
                }
                // 交换delNode 和 右子树中最小的节点
                BinaryTreeNode tmp = swapNode(mRoot, delNode, rmin);
                if (tmp != null) mRoot = tmp;
                // delNode交换后的新的父节点
                // delParent = rminP;  // rminP刚好是delNode则下面的代码就右问题，无法删除交换后的delNode
            }
            // 被删除的是叶子节点 或 被删除的节点只有一个子树
            // 先找到被删除节点的孩子节点
            BinaryTreeNode child = null;
            BinaryTreeNode delParent = findParentNoBST(mRoot, delNode);
            // 如果走了上面的逻辑，则delNode已经被交换了位置了
            if (delNode.left != null) child = delNode.left;
            else if (delNode.right != null) child = delNode.right;
            else child = null;
            
            if (delParent == null) {  // root被删除
                mRoot = child;
            } else {
                if (delParent.left == delNode) {
                    delParent.left = child;
                }
                if (delParent.right == delNode) {
                    delParent.right = child;
                }
            }
            return true;
        }

        // 前驱节点
        public BinaryTreeNode getPreCursorNodeFromBST(BinaryTreeNode root, BinaryTreeNode node) {
            // 如果有左子树，则寻找左子树中最大的节点
            if (node.left != null) {
                BinaryTreeNode p = node.left;
                while (p.right != null) {
                    p = p.right;
                }
                return p;
            } else {
                // 如果没有左子树，则说明自己是 父节点的左子树或右子树里 最小的
                // 所以顺着父节点往上找，找到第一个右孩子的父节点
                // 找到第一个有右孩子的祖先
                BinaryTreeNode t = node;
                while (true) {
                    BinaryTreeNode p = root;
                    BinaryTreeNode pp = null;
                    while (p != null) {
                        if (t.value > p.value) {
                            pp = p;
                            p = p.right;
                        } else if (t.value < p.value) {
                            pp = p;
                            p = p.left;
                        } else {
                            break; // 找到父节点 或者 p是root节点
                        }
                    }
                    if (pp != null) {
                        if (pp.right == t) return pp;
                        t = pp;
                    } else {
                        break; // 递归到root节点还没找到，说明自己是最小节点，没有前驱
                    }
                }
            }
            return null;
        }

        // 后驱节点
        public BinaryTreeNode getPostCursorNodeFromBST(BinaryTreeNode root, BinaryTreeNode node) {
            // 如果存在右子树 则找到右子树中最小的
            if (node.right != null) {
                BinaryTreeNode p = node.right;
                while (p.left != null) {
                    p = p.left;
                }
                return p;
            } else {
                // 如果没有右子树， 说明自己是父节点的左子树或右子树中 最大的
                // 顺着父节点，找到第一个左孩子的父节点
                // 找到第一个有 左孩子的祖先
                BinaryTreeNode t = node;
                while (true) {
                    BinaryTreeNode p = root;
                    BinaryTreeNode pp = null;
                    while (p != null) {
                        if (t.value > p.value) {
                            pp = p;
                            p = p.right;
                        } else if (t.value < p.value) {
                            pp = p;
                            p = p.left;
                        } else {
                            break;
                        }
                    }
                    if (pp != null) {
                        if (pp.left == t) return pp;
                        t = pp; // 向上查找
                    } else {
                        break;
                    }
                }
            }
            return null;
        }
        
        public BinaryTreeNode getMaxNodeFromBST(BinaryTreeNode root) {
            BinaryTreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        public BinaryTreeNode getMinNodeFromBST(BinaryTreeNode root) {
            BinaryTreeNode p = root;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
    }

    // ----------------- 支持重复数据的BST -----------------
    public static class BinarySearchTreeDup {
        public BinaryTreeNode mRoot;

        // 后插入的和自己大小一样的节点，放入自己的到右子树中
        public BinarySearchTreeDup(int size) {
            mRoot = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_SORTED_DUP);
        }

        public void print() {
            if (mRoot == null) System.out.println("null tree");
            graphPrintBinaryTree(mRoot);
        }

        public LinkedList<BinaryTreeNode> find(int data) {
            LinkedList<BinaryTreeNode> list = new LinkedList<>();
            BinaryTreeNode node = mRoot;
            while (node != null) {
                if (node.value == data) {
                    list.add(node);
                    node = node.right; // 继续向右找
                } else if (node.value > data) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return list;
        }

        // 不需要维持平衡性的插入操作
        public void insert(int data) {
            BinaryTreeNode p = mRoot;
            while (true) {
                // 大小一致的，放到右子树
                if (data >= p.value) {
                    if (p.right == null) {
                        p.right = new BinaryTreeNode(data);
                        break;
                    }
                    p = p.right;
                }
                if (data < p.value) {
                    if (p.left == null) {
                        p.left = new BinaryTreeNode(data);
                        break;
                    }
                    p = p .left;
                }
            }
        }

        // 默认node一定存在
        // 返回null表示node为根节点
        // 重复节点存在，所以还要比较地址，确保找到真正的父节点
        private BinaryTreeNode findParentExactly(BinaryTreeNode root, BinaryTreeNode node) {
            if (node == root) return null;
            BinaryTreeNode p, pp;
            p = root;
            pp = null;
            while (p != null) {
                if (p.value > node.value) {
                    pp = p;
                    p = p.left;
                } else if (p.value < node.value) {
                    pp = p;
                    p = p.right;
                } else {
                    if (p == node) break;
                    else {
                        pp = p;
                        p = p.right; // 继续向右找
                    }
                }
            }
            return pp;
        }

        // 交换两个节点的位置，不是拷贝值
        // 默认两个节点都存在
        // 返回值不为null表示root发生了变化，返回的是新root
        private BinaryTreeNode swapNode(BinaryTreeNode root, BinaryTreeNode p1, BinaryTreeNode p2) {
            if (p1 == null || p2 == null || p1 == p2) return null;
  
            BinaryTreeNode pp1, pp2, newRoot, parent;
            pp1 = pp2 = newRoot = parent = null;
            
            pp1 = findParentExactly(root, p1);
            pp2 = findParentExactly(root, p2);

            if (pp1 == null) newRoot = p2; // p1是root，交换后p2为新root
            if (pp2 == null) newRoot = p1;

            if (pp1 == p2) parent = p2; // p2是p1的父节点
            if (pp2 == p1) parent = p1; // p1是p2的父节点

            // 交换的两个节点不是父子关系
            if (parent == null) {
                BinaryTreeNode lp, rp;
                lp = p1.left;
                rp = p1.right;

                p1.left = p2.left;
                p1.right = p2.right;
                p2.left = lp;
                p2.right = rp;

                if (pp1 != null) {
                    if (pp1.left == p1) pp1.left = p2;
                    if (pp1.right == p1) pp1.right = p2;
                }
                if (pp2 != null) {
                    if (pp2.left == p2) pp2.left = p1;
                    if (pp2.right == p2) pp2.right = p1;
                }
            } else { // 交换的两个节点是父子节点 要特殊处理
                BinaryTreeNode lp, rp;
                lp = p1.left;
                rp = p1.right;

                p1.left = p2.left;
                p1.right = p2.right;
                p2.left = lp;
                p2.right = rp;
                if (p1.left == p1 || p1.right == p1) {
                    if (p1.left == p1) {
                        p1.left = p2;
                    } else if (p1.right == p1) {
                        p1.right = p2;
                    }
                    if (pp2 != null) {
                        if (pp2.left == p2) pp2.left = p1;
                        if (pp2.right == p2) pp2.right = p1;
                    }
                }
                if (p2.left == p2 || p2.right == p2) {
                    if (p2.left == p2) {
                        p2.left = p1;
                    } else if (p2.right == p2) {
                        p2.right = p1;
                    }
                    if (pp1 != null) {
                        if (pp1.left == p1) pp1.left = p2;
                        if (pp1.right == p1) pp1.right = p2;
                    }
                }
            }

            return newRoot;
        }

        // 不是BST情况下的查找parent
        public BinaryTreeNode findParentNoBST(BinaryTreeNode root, BinaryTreeNode node) {
            if (root.left == node || root.right == node ) return root;

            BinaryTreeNode ret = null;
            
            if (root.left != null)
                ret = findParentNoBST(root.left, node);
            if (ret == null && root.right != null)
                ret = findParentNoBST(root.right, node);
            
            return ret;
        }

        public boolean deleteOnce(BinaryTreeNode delNode) {
            if (mRoot == null) return false;
            if (find(delNode.value) == null) return false; // 要删除的节点不存在
            // 被删除的节点有两个子树
            // 找到右子树中最小的节点，替换被删除节点
            // 然后删除这个最小节点，由于最小节点肯定没有左子树， 按照 被删除的是叶子节点 或 被删除的节点只有一个子树 情况处理
            if (delNode.left != null && delNode.right != null) {
                // 找到右子树中最小的节点
                BinaryTreeNode rmin = delNode.right;
                while (rmin.left != null) {
                    rmin = rmin.left;
                }
                // 交换delNode 和 右子树中最小的节点
                BinaryTreeNode tmp = swapNode(mRoot, delNode, rmin);
                if (tmp != null) mRoot = tmp;
            }
            // 被删除的是叶子节点 或 被删除的节点只有一个子树
            // 先找到被删除节点的孩子节点
            BinaryTreeNode child = null;
            BinaryTreeNode delParent = findParentNoBST(mRoot, delNode);
            // 如果走了上面的逻辑，则delNode已经被交换了位置了
            if (delNode.left != null) child = delNode.left;
            else if (delNode.right != null) child = delNode.right;
            else child = null;
            
            if (delParent == null) {  // root被删除
                mRoot = child;
            } else {
                if (delParent.left == delNode) {
                    delParent.left = child;
                }
                if (delParent.right == delNode) {
                    delParent.right = child;
                }
            }
            return true;
        }

        public boolean delete(BinaryTreeNode delNode) {
            if (mRoot == null) {
                return false;
            }
            return deleteOnce(delNode);
        }

        public boolean deleteList(LinkedList<BinaryTreeNode> dels) {
            if (mRoot == null || dels == null) {
                return false;
            }
            BinaryTreeNode delNode = null;
            while (!dels.isEmpty()) {
                delNode = dels.poll(); // 依次删除符合要求的节点
                deleteOnce(delNode);
            }
            return true;
        }

        // 前驱节点
        public BinaryTreeNode getPreCursorNodeFromBST(BinaryTreeNode root, BinaryTreeNode node) {
            // 如果有左子树，则寻找左子树中最大的节点
            if (node.left != null) {
                BinaryTreeNode p = node.left;
                while (p.right != null) {
                    p = p.right;
                }
                return p;
            } else {
                // 如果没有左子树，则说明自己是 父节点的左子树或右子树里 最小的
                // 所以顺着父节点往上找，找到第一个右孩子的父节点
                // 找到第一个有右孩子的祖先
                BinaryTreeNode t = node;
                while (true) {
                    BinaryTreeNode p = root;
                    BinaryTreeNode pp = null;
                    while (p != null) {
                        if (t.value > p.value) {
                            pp = p;
                            p = p.right;
                        } else if (t.value < p.value) {
                            pp = p;
                            p = p.left;
                        } else {
                            break; // 找到父节点 或者 p是root节点
                        }
                    }
                    if (pp != null) {
                        if (pp.right == t) return pp;
                        t = pp;
                    } else {
                        break; // 递归到root节点还没找到，说明自己是最小节点，没有前驱
                    }
                }
            }
            return null;
        }

        // 后驱节点
        public BinaryTreeNode getPostCursorNodeFromBST(BinaryTreeNode root, BinaryTreeNode node) {
            // 如果存在右子树 则找到右子树中最小的
            if (node.right != null) {
                BinaryTreeNode p = node.right;
                while (p.left != null) {
                    p = p.left;
                }
                return p;
            } else {
                // 如果没有右子树， 说明自己是父节点的左子树或右子树中 最大的
                // 顺着父节点，找到第一个左孩子的父节点
                // 找到第一个有 左孩子的祖先
                BinaryTreeNode t = node;
                while (true) {
                    BinaryTreeNode p = root;
                    BinaryTreeNode pp = null;
                    while (p != null) {
                        if (t.value > p.value) {
                            pp = p;
                            p = p.right;
                        } else if (t.value < p.value) {
                            pp = p;
                            p = p.left;
                        } else {
                            break;
                        }
                    }
                    if (pp != null) {
                        if (pp.left == t) return pp;
                        t = pp; // 向上查找
                    } else {
                        break;
                    }
                }
            }
            return null;
        }
        
        public BinaryTreeNode getMaxNodeFromBST(BinaryTreeNode root) {
            BinaryTreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        public BinaryTreeNode getMinNodeFromBST(BinaryTreeNode root) {
            BinaryTreeNode p = root;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
    }


    // 根据数组生成一个排序二叉树
    public static BinaryTreeNode genBSTFromData(int[] arr) {
        if (arr.length <= 0) return null;
    
        BinaryTreeNode root = new BinaryTreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            BinaryTreeNode p = root;
            while (true) {
                // 大小一致的，放到右子树
                if (arr[i] >= p.value) {
                    if (p.right == null) {
                        p.right = new BinaryTreeNode(arr[i]);
                        p.right.parent = p;
                        break;
                    }
                    p = p.right;
                }
                if (arr[i] < p.value) {
                    if (p.left == null) {
                        p.left = new BinaryTreeNode(arr[i]);
                        p.left.parent = p;
                        break;
                    }
                    p = p.left;
                }
            }
        }
        return root;
    }

    public static BinaryTreeNode genBST(int size) {
        int[] arr = RandomArrayGenerator.genIntArray(size, 1000);
        return genBSTFromData(arr);
    }

    // ----------------- 图形化打印接口 --------------------
    public static void printBinaryTree(BinaryTreeNode root) {
        if (root == null) return;
        int nodeCnt = 0;
        ArrayList<ArrayList<BinaryTreeNode>> levelList = new ArrayList<>();
        if (root != null) {
            ArrayList<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
            levelList.add(0, list);
            list.add(root);
            nodeCnt++;
        }
        int index = 0;
        while (index < levelList.size()) {
            ArrayList<BinaryTreeNode> curList = levelList.get(index);
            if (curList.size() > 0) {
                // levelList最后一个是空数组
                ArrayList<BinaryTreeNode> nextList = new ArrayList<BinaryTreeNode>();
                levelList.add(index+1, nextList);
                for (BinaryTreeNode parent : curList) {
                    if (parent.left != null) {
                        nextList.add(parent.left);
                        nodeCnt++;
                    }
                    if (parent.right != null) {
                        nextList.add(parent.right);
                        nodeCnt++;
                    }
                }
            }
            index++;
        }
        index--; 
        levelList.remove(index); // 最后一个是空数组

        walkInOrder(root, new ColumCalculateCallback()); // 得到每个节点的col值
        // 按层打印
        for (int i = 0; i < levelList.size(); i++) {
            ArrayList<BinaryTreeNode> curList = levelList.get(i);
            System.out.print("level" + (i+1) + ": ");
            for (int j = 0; j < curList.size(); j++) {
                System.out.print(curList.get(j).value + " ");
            }
            System.out.println();
        }

        for (ArrayList<BinaryTreeNode> list : levelList) {
            StringBuilder builder;
            // 这一层node 最靠右的所在colume index
            int maxLen = nodeCnt+1;
            // 打印节点内容本身: -*-
            builder = getRepeatedStringBuilder(maxLen, ' ');
            for (BinaryTreeNode node : list) {
                builder.setCharAt(node.col, 'O');
                if (node.left != null) {
                    for(int i = node.col-1; i >= node.left.col; i--) {
                        builder.setCharAt(i, '_');
                    }
                }
                if (node.right != null) {
                    for (int i = node.col+1; i <= node.right.col; i++) {
                        builder.setCharAt(i, '_');
                    }
                }
            }
            System.out.println(builder.toString());
            // 打印子节点上面的"|"
            builder = getRepeatedStringBuilder(maxLen, ' ');
            for (BinaryTreeNode node : list) {
                if (node.left != null) {
                    builder.setCharAt(node.left.col, '|');
                }
                if (node.right != null) {
                    builder.setCharAt(node.right.col, '|');
                }
            }
            System.out.println(builder.toString());
        }
    }

    public static void graphPrintBinaryTree(BinaryTreeNode root) {
        if (root == null) return;

        // 得到节点打印占1个字符情况下的，各个节点的横坐标start pos
        ColumCalculateCallback calculateCallback = new ColumCalculateCallback();
        walkInOrder(root, calculateCallback); // 得到每个节点的col值

        // 按层打印
        // walkLevelOrder(root, new LevelOrderPrintCallback());

        // 图形化打印(无alue值)， 即每个节点打印占据一个字符
        //GraphPrintCallback graphPrintCallback = new GraphPrintCallback();
        //walkLevelOrder(root, graphPrintCallback);
 
        // 根据节点内容宽度所占空间调整位置，防止冲突覆盖
        AdjustColumeByValueLengthCallback adjustColumeCallback = new AdjustColumeByValueLengthCallback();
        walkPostOrder(root, adjustColumeCallback);

        // 打印调整后的col
        // walkLevelOrder(root, new LevelOrderPrintCallback());

        // 图形化打印，完整打印节点的内容
        // RealGraphPrintCallback realGCallback = new RealGraphPrintCallback();
        // walkLevelOrder(root, realGCallback);
        walkLevelOrder(root, new RealGraphPrintCallback2());
    }

    public static void printInOrder(BinaryTreeNode root) {
        if (root == null) return;
        System.out.print("print mid seq: ");
        walkInOrder(root, new PrintCallback());
    }

    // ------ 遍历函数 ----------
    public static void walkInOrder(BinaryTreeNode root, WalkCallBack callBack ) {
        if (root == null) return;
        if (root.left != null) {
            walkInOrder(root.left, callBack);
        } 
        callBack.handleNode(root);
        if (root.right != null) {
            walkInOrder(root.right, callBack);
        }
    }

    public static void walkPreOrder(BinaryTreeNode root, WalkCallBack callBack ) {
        if (root == null) return;
        callBack.handleNode(root);
        if (root.left != null) {
            walkPreOrder(root.left, callBack);
        } 
        if (root.right != null) {
            walkPreOrder(root.right, callBack);
        }
    }

    public static void walkPostOrder(BinaryTreeNode root, WalkCallBack callBack ) {
        if (root == null) return;
        if (root.left != null) {
            walkPostOrder(root.left, callBack);
        } 
        if (root.right != null) {
            walkPostOrder(root.right, callBack);
        }
        callBack.handleNode(root);
    }

    public static void walkLevelOrder(BinaryTreeNode root, WalkCallBack callBack) {
        if (root == null) return;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curLevelCnt = 1; // 第一层只有根节点
        int nextLevelCnt = 0;
        int curCnt = 0;
        int levelNum = 1;
        while (!queue.isEmpty()) {
            if (curCnt == 0) { // 新的层
                callBack.handleNewLevel(levelNum++);
            }
            BinaryTreeNode node = queue.poll();
            callBack.handleNode(node);
            curCnt++;
            if (node.left != null) {
                queue.add(node.left);
                nextLevelCnt++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelCnt++;
            }
            if (curCnt == curLevelCnt) { // 一层结束
                callBack.handleEndOfLevel();
                curLevelCnt = nextLevelCnt;
                nextLevelCnt = 0;
                curCnt = 0;
            }
        }
    }

    // ------------- callback ---------------
    public static abstract class WalkCallBack {
        public void handleNode(BinaryTreeNode node) {}
        public void handleNewLevel(int levelNumber) {}
        public void handleEndOfLevel() {}
    }

    // 不考虑节点value所占宽度，计算每个结点的colume，即横坐标
    // 并同时计算节点value的宽度
    public static class ColumCalculateCallback extends WalkCallBack {
        public int cur = 0;
        public void handleNode(BinaryTreeNode node) {
            node.col = cur++;
            node.calcAndSetLengthOfValueInstr();
            // System.out.println("node col: " + node.col + ", len: " + node.lenOfValueInStr);
        }
    }

    public static class PrintCallback extends WalkCallBack {
        public void handleNode(BinaryTreeNode node) {
            //System.out.print(node.value + ":" + node.col + " ");
            System.out.print(node.value + " ");
        }
    }

    public static class LevelOrderPrintCallback extends WalkCallBack {
        public void handleNewLevel(int levelNumber) {
            System.out.print("level" + levelNumber + ": ");
        }
        public void handleNode(BinaryTreeNode node) {
            System.out.print(node.value + ":" + node.col +" ");
        }
        public void handleEndOfLevel() {
            System.out.println();
        }
    }

    // 打印节点占据1个字符情况下的callback
    // 调用此函数之前 确保 已经通过中序遍历 获得了每个节点在只占一个字符的情况下的无冲突位置
    public static class GraphPrintCallback extends WalkCallBack {
        StringBuilder builder1, builder2;
        public void handleNewLevel(int levelNumber) {
            builder1 = getRepeatedStringBuilder(80, ' ');
            builder2 = getRepeatedStringBuilder(80, ' ');
        }
    
        static StringBuilder getRepeatedStringBuilder(int count, char c) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count; i++) {
                builder.append(c);
            }
            return builder;
        }
    
        public void handleNode(BinaryTreeNode node) {
            // 打印节点内容本身: -*-
            builder1.setCharAt(node.col, 'O');
            if (node.left != null) {
                for(int i = node.col-1; i >= node.left.col; i--) {
                    builder1.setCharAt(i, '_');
                }
            }
            if (node.right != null) {
                for (int i = node.col+1; i <= node.right.col; i++) {
                    builder1.setCharAt(i, '_');
                }
            }
            // 打印子节点上面的"|"
            if (node.left != null) {
                builder2.setCharAt(node.left.col, '|');
            }
            if (node.right != null) {
                builder2.setCharAt(node.right.col, '|');
            }            
        }
        public void handleEndOfLevel() {
            System.out.println(builder1.toString());
            System.out.println(builder2.toString());
        }
    }

    // 根据打印节点value所占用的空间，调整节点位置，避免重叠冲突
    // 调用此函数之前 确保 已经通过中序遍历 获得了每个节点在只占一个字符的情况下的无冲突位置
    // 调用此函数需要用 后序遍历
    // 后序遍历的callback，在左右子树没有冲突的情况下，调整自己为root的树
    // 目标是：左子树中node的最大的endPos要小于root的startPos， root的endPos小于右子树中node的最小的startPos
    public static class AdjustColumeByValueLengthCallback extends WalkCallBack {
        public void handleNode(BinaryTreeNode node) {
            // 先调整右子树，防止右子树在下面的逻辑中被再次调整时和root的相对位置发生变化
            // 当前节点的end pos 可能大于 右子树中所有node的最小start pos
            if (node.right != null) {
                // BinaryTreeNode rminPosNode = findMinPosNodeInRightChild(node);
                int rMinStartPos = minStartPosInTree(node.right);
                int endPos = node.col + node.lenOfValueInStr - 1;
                if ((endPos+1) >= rMinStartPos/*rminPosNode.col*/) {
                    // System.out.println("node end pos: " + endPos + ", rmin start pos: " + rMinStatPos/*rminPosNode.col*/);
                    MoveNodeCallBack moveCallBack = new MoveNodeCallBack();
                    moveCallBack.dist = endPos - rMinStartPos + 2;
                    walkInOrder(node.right, moveCallBack);
                }
            }

            if (node.left != null) {
                // BinaryTreeNode lmaxPosNode = findMaxPosNodeInLeftChild(node);
                // 左子树中所有节点的最大endPos与自己的startPos至少需要一个空格
                // (中序遍历已经确保自己比所有右子树中的startPos小)
                // int endPos = lmaxPosNode.col + lmaxPosNode.lenOfValueInStr - 1;
                int endPos = maxEndPosInTree(node.left);
                if ((endPos+1) >= node.col) {
                    // System.out.println("lmax endPos: " + endPos + ", node start pos: " + node.col);
                    MoveNodeCallBack moveCallBack = new MoveNodeCallBack();
                    moveCallBack.dist = endPos - node.col + 2;
                    if (node.right != null)
                        walkInOrder(node.right, moveCallBack);
                    node.col = node.col + (endPos - node.col + 2); // 调整自己
                }
            }
        }

        int maxEndPosInTree(BinaryTreeNode root) {
            final class SaveMaxEndPosCallBack extends WalkCallBack {
                int maxEndPos = 0;
                public void handleNode(BinaryTreeNode node) {
                    int endPos = node.col + node.lenOfValueInStr - 1;
                    if (maxEndPos < endPos) {
                        maxEndPos = endPos;
                    }
                }
            }
            SaveMaxEndPosCallBack callBack = new SaveMaxEndPosCallBack();
            walkInOrder(root, callBack);
            return callBack.maxEndPos;
        }

        int minStartPosInTree(BinaryTreeNode root) {
            final class SaveMinStartPosCallBack extends WalkCallBack {
                int minStartPos = Integer.MAX_VALUE;
                public void handleNode(BinaryTreeNode node) {
                    if (minStartPos > node.col) {
                        minStartPos = node.col;
                    }
                }
            }
            SaveMinStartPosCallBack callBack = new SaveMinStartPosCallBack();
            walkInOrder(root, callBack);
            return callBack.minStartPos;
        }
    }

    public static class MoveNodeCallBack extends WalkCallBack {
        int dist = 0;
        public void handleNode(BinaryTreeNode node) {
            node.col += dist;
        }
    }

    // 打印节点内容，而不是O了
    public static class RealGraphPrintCallback extends WalkCallBack {
        StringBuilder builder1, builder2;
        public void handleNewLevel(int levelNumber) {
            builder1 = getRepeatedStringBuilder(80, ' ');
            builder2 = getRepeatedStringBuilder(80, ' ');
        }
    
        static StringBuilder getRepeatedStringBuilder(int count, char c) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count; i++) {
                builder.append(c);
            }
            return builder;
        }
    
        public void handleNode(BinaryTreeNode node) {
            // 打印节点内容本身: eg: _123_
            builder1.replace(node.col, node.col+node.lenOfValueInStr, String.valueOf(node.value));
            if (node.left != null) {
                for(int i = node.col-1; i >= node.left.col; i--) {
                    builder1.setCharAt(i, '_');
                }
            }
            if (node.right != null) {
                // 打印 _ 一直到右节点 所占宽度的最后的位置
                for (int i = node.col+node.lenOfValueInStr; i < node.right.col+node.right.lenOfValueInStr; i++) {
                    builder1.setCharAt(i, '_');
                }
            }
            // 打印子节点上面的"|"
            if (node.left != null) {
                builder2.setCharAt(node.left.col, '|');
            }
            if (node.right != null) {
                builder2.setCharAt(node.right.col+node.right.lenOfValueInStr-1, '|');
            }            
        }
        public void handleEndOfLevel() {
            System.out.println(builder1.toString());
            System.out.println(builder2.toString());
        }
    }

    // 打印节点内容，而不是O了
    public static class RealGraphPrintCallback2 extends WalkCallBack {
        StringBuilder mBuilder1, mBuilder2;
        int mNextStartIndex1 = 0;
        int mNextStartIndex2 = 0;
        public void handleNewLevel(int levelNumber) {
            mBuilder1 = new StringBuilder();
            mBuilder2 = new StringBuilder();
            mNextStartIndex1 = 0;
            mNextStartIndex2 = 0;
        }
    
        public void handleNode(BinaryTreeNode node) {
            if (node.left != null) {
                // 打印节点内容本身以及 _ 之前的空格， eg: "    "_123_
                for (int i = mNextStartIndex1; i < (node.left.col); i++) {
                    mBuilder1.append(' ');
                }
                // 打印节点内容本身之前 _， eg: _123
                for (int i = mBuilder1.length(); i < node.col; i++) {
                    mBuilder1.append('_');
                }
            } else {
                // 没有左子节点，仅仅打印节点内容本身之前的空格， eg: "    "123_
                for (int i = mNextStartIndex1; i < node.col; i++) {
                    mBuilder1.append(' ');
                }
            }
            // // 打印节点内容本身
            mBuilder1.append(String.valueOf(node.value));
            // 打印 _, 从节点内容之后开始，一直到右子节点end pos
            if (node.right != null) {
                for (int i = node.col+node.lenOfValueInStr; 
                        i < node.right.col+node.right.lenOfValueInStr; i++) {
                    mBuilder1.append('_');
                }
            }
            mNextStartIndex1 = mBuilder1.length();
            
            // 第二行
            // 打印子节点上面的"|"
            if (node.left != null) {
                for (int i = mNextStartIndex2; i < node.left.col; i++) {
                    mBuilder2.append(' ');
                }
                mBuilder2.append('|');
            }
            if (node.right != null) {
                for (int i = mBuilder2.length(); i < (node.right.col+node.right.lenOfValueInStr-1); i++) {
                    mBuilder2.append(' ');
                }
                mBuilder2.append('|');      
            }
            mNextStartIndex2 = mBuilder2.length();
        }

        public void handleEndOfLevel() {
            System.out.println(mBuilder1.toString());
            System.out.println(mBuilder2.toString());
        }
    }

    // --------------- 非递归遍历------------------
    void walkInOrderNoRecur(BinaryTreeNode root) {
        if (root == null) return;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode p = root;
        /*
        while (true) {
            if (stack.size() <= 0 || p != stack.peek()) {
                if (p.left != null) {
                    stack.push(p);
                    p = p.left;
                } else {
                    System.out.print(p.value + " ");
                    if (p.right != null) {
                        p = p.right;
                    } else {
                        if (stack.size() <= 0) break;
                        p = stack.peek();
                    }
                }
            } else {
                p = stack.pop();
                System.out.print(p.value + " ");
                if (p.right != null) {
                    p = p.right;
                } else {
                    if (stack.size() <= 0) break;
                    p = stack.peek();
                }
            }
        }*/
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                System.out.print(p.value + " ");
                p = p.right;
            }
        }
        System.out.println();
    }
    
    void walkPreOrderNoRecur(BinaryTreeNode root) {
        if (root == null) return;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode p = root;
        /*
        stack.push(p);
        do {
            p = stack.pop();
            System.out.print(p.value + " ");
            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        } while (stack.size() > 0);*/
        while (p != null || stack.size() > 0) {
            while (p != null) {
                System.out.print(p.value + " ");
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                p = p.right;
            }
        }
        System.out.println();
    }
    
    void walkPostOrderNoRecur(BinaryTreeNode root) {
        if (root == null) return;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode p = root;
        BinaryTreeNode backfrom = null;
        while (p != null || stack.size() > 0) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (p.right != null && p.right != backfrom) { // 第一次栈顶
                    p = p.right;
                } else {
                    p = stack.pop(); // 第二次stack top
                    System.out.print(p.value + " ");
                    backfrom = p;
                    p = null;
                }
            }
        }
        System.out.println();
    }

    // ---------- 其他util接口 -------------
    // 左子树中col最大的节点, 即中序遍历结果的最后一个节点
    public static BinaryTreeNode findMaxPosNodeInLeftChild(BinaryTreeNode node) {
        if (node.left != null) {
            BinaryTreeNode ret = node.left;
            while (ret.right != null) {
                ret = ret.right; 
            }
            return ret;
        }
        return null;
    }

    // 右子树中， 中序遍历结果的第一个节点。
    public static BinaryTreeNode findMinPosNodeInRightChild(BinaryTreeNode node) {
        if (node.right != null) {
            BinaryTreeNode ret = node.right;
            while (ret.left != null) {
                ret = ret.left;
            }
            return ret;
        }     
        return null;
    }

    public static StringBuilder getRepeatedStringBuilder(int count, char c) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(c);
        }
        return builder;
    }
}