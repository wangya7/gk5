package wang.bannong.gk5.offer.jdk.concurrent.cases;

import java.util.Objects;
import java.util.Stack;

import lombok.Data;

/**
 * 二叉树
 */
@Data
public class BT<T> {
    private T     data;
    private BT<T> lchild;
    private BT<T> rchild;

    public static <T> BT of(T data) {
        BT tree = new BT();
        tree.setData(data);
        tree.setLchild(null);
        tree.setRchild(null);
        return tree;
    }

    public static <T> BT of(T data, BT<T> lchild, BT<T> rchild) {
        BT tree = new BT();
        tree.setData(data);
        tree.setLchild(lchild);
        tree.setRchild(rchild);
        return tree;
    }

    /**
     * 前序遍历
     */
    public static void preView(BT tree) {
        System.out.print(tree.data);
        if (tree.getLchild() != null)
            preView(tree.lchild);
        if (tree.getRchild() != null)
            preView(tree.rchild);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BT<?> bt = (BT<?>) o;
        return Objects.equals(data, bt.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    /**
     * 前序遍历
     */
    public static void preView4Loop(BT tree) {
        if (null == tree)
            return;
        Stack<BT> stack = new Stack<>();
        stack.push(tree);
        while (stack.size() > 0) {
            BT tmp = stack.pop();
            System.out.print(tmp.data);

            if (tmp.getRchild() != null) {
                stack.push(tmp.rchild);
            }

            if (tmp.getLchild() != null) {
                stack.push(tmp.lchild);
            }
        }
    }

    /**
     * 中序遍历
     */
    public static void midView(BT tree) {
        if (tree.getLchild() != null)
            midView(tree.lchild);
        System.out.print(tree.data);
        if (tree.getRchild() != null)
            midView(tree.rchild);
    }

    /**
     * 中序遍历非递归方式，借助"栈"  不停的进栈出栈
     */
    public static void midView4Loop(BT tree) {
        if (null == tree) {
            return;
        }
        Stack<BT> stack = new Stack<>();
        BT cur = tree;

        while (stack.size() > 0 || cur != null) {
            // 顺着顶端一直找到最深处的左孩子，过程中全部压入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.lchild;
            }
            // 出栈
            BT tmp = stack.pop();
            System.out.print(tmp.data);
            cur = tmp.rchild;
        }
    }

    /**
     * 后序遍历
     */
    public static void bckView(BT tree) {
        if (tree.getLchild() != null)
            bckView(tree.lchild);
        if (tree.getRchild() != null)
            bckView(tree.rchild);
        System.out.print(tree.data);
    }

    /**
     * 后序遍历
     */
    public static void bckView4Loop(BT tree) {
        if (tree == null) {
            return;
        }
        Stack<BT> stack = new Stack<>();
        stack.push(tree);
        BT h = tree;  // 指向最近一次访问过的结点
        BT c = null;  // 指向栈顶结点元素
        while (stack.size() > 0) {
            c = stack.peek();

            // c结点有左孩子 并且 左孩子没被遍历（输出）过 并且 右孩子没被遍历过
            if (c.getLchild() != null && !h.equals(c.getLchild()) && !h.equals(c.getRchild())) {
                stack.push(c.getLchild());
            } else if (c.getRchild() != null && !h.equals(c.getRchild())) { // c结点有右孩子 并且 右孩子没被遍历（输出）过
                stack.push(c.getRchild());
            } else { // c结点没有孩子结点 或者孩子结点已经被遍历（输出）过
                BT node = stack.pop();
                System.out.print(node.data);
                h = c;
            }
        }
    }


    /***
     *                        A
     *                      /   \
     *                     /     \
     *                    B       C
     *                   / \     / \
     *                  D   E   F   G
     *                 / \     / \
     *                J   K   H   I
     *                   / \
     *                  L   M
     * @param args
     */
    public static void main(String[] args) {
        // BT<String> bt_d = BT.of("D", BT.of("J"), BT.of("K", BT.of("L"), BT.of("M")));
        // BT<String> bt_c = BT.of("C", BT.of("F", BT.of("H"), BT.of("I")), BT.of("G"));
        // BT<String> bt_a = BT.of("A", BT.of("B", bt_d, BT.of("E")), bt_c);
        // System.out.print("前序（递归） = ");
        // preView(bt_a);
        // System.out.println();
        // System.out.print("前序 = ");
        // preView4Loop(bt_a);
        // System.out.println();
        //
        // System.out.print("中序（递归） = ");
        // midView(bt_a);
        // System.out.println();
        // System.out.print("中序 = ");
        // midView4Loop(bt_a);
        // System.out.println();
        //
        // System.out.print("后序（递归） = ");
        // bckView(bt_a);
        // System.out.println();
        // System.out.print("后序 = ");
        // bckView4Loop(bt_a);
        // System.out.println();

        BT<String> bt_d = BT.of("D", BT.of("J"), BT.of("K", BT.of("L"), BT.of("M")));
    }


    public int minCameraCover(BT root) {
        if (root == null) {
            return 0;
        } else if (root.getRchild() == null && root.getLchild() == null) {
            return 1;
        }
        // 保证至少大于1层
        return minCameraCoverDo(root, 1, 0);
    }

    public int minCameraCoverDo(BT root, int depth, int size) {
        if (root.getRchild() == null && root.getLchild() == null) {
            depth++;
            return depth % 2 == 0 ? 1 : 0;
        } else {
            int rSize = minCameraCoverDo(root.getRchild(), depth + 1, size);
            int lSize = minCameraCoverDo(root.getLchild(), depth + 1, size);
            if (rSize == 0 || lSize == 0) {
                return size + lSize + rSize;
            }
            return size + (rSize > lSize ? lSize : rSize);
        }
    }

}
