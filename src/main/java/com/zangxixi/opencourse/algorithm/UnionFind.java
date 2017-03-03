package com.zangxixi.opencourse.algorithm;

import org.junit.Test;

/**
 * Created by zxx on 16/6/14.
 */
public class UnionFind {
/*    public interface UnionFindInterface {
        public boolean connected();
        //将 a 连接到 b 上
        public void union(int a, int b);
    }*/

    /** Quick-Union 改进:
     * 1)union时,将小树union到大树上, 这样能在一定程度上降低整棵树的深度
     * 2)裁剪树, 尽可能将小树直接union到根结点上去
     **/
    private class QuickUnionFinal {
        private int[] id;
        private int[] size;
        public QuickUnionFinal(int n) {
            id = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 0;
            }
        }

        private int root(int a) {
            while (a != id[a]) {
                id[a] = id[id[a]];//优化点, 裁剪移动了枝干
                a = id[a];
            }

            return a;
        }

        public boolean connected(int a, int b) {
            return root(a) == root(b);
        }

        public void union(int a, int b) {
            int aRoot = root(a);
            int bRoot = root(b);
            if (aRoot == bRoot) return;

            //优化点, 将小树union到大树上
            if (size[a] > size[b]) {
                id[bRoot] = aRoot;
                size[a] += size[b];
            } else {
                id[aRoot] = bRoot;
                size[b] += size[a];
            }
        }
    }

    /** 这个测试例子不够好 **/
    @Test
    public void testQuickUnionCompare() {
        int n = 100000000;
        long s1 = System.currentTimeMillis();
        QuickUnionFinal quickUnionFinal = new QuickUnionFinal(n);
        for (int i = 0; i < n - 1; i++) {
            quickUnionFinal.union(i, i+1);
        }

        boolean r1 = quickUnionFinal.connected(0, n-1);
        long s2 = System.currentTimeMillis();
        System.out.println("优化后 connected= " + r1 + ";\tcosts= "+ (s2 - s1));

        long t1 = System.currentTimeMillis();
        QuickUnionUF quickUnionUF = new QuickUnionUF(n);
        for (int i = 0; i < n - 1; i++) {
            quickUnionUF.union(i, i+1);
        }

        boolean r2 = quickUnionUF.connected(0, n-1);
        long t2 = System.currentTimeMillis();
        System.out.println("优化前 connected= " + r2 + ";\tcosts= "+ (t2 - t1));
    }

    /** Quick-Union 使用树来实现 **/
    private class QuickUnionUF {
        private int[] id;
        public QuickUnionUF(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        private int root(int a) {
            while (a != id[a]) {
                a = id[a];
            }

            //System.out.println("root= " + a);
            return a;
        }

        //a 与 b连接的时候, 直接将a的根结点连接到b的根结点上去
        public void union(int a, int b) {
            int aRoot = root(a);
            int bRoot = root(b);
            if (aRoot == bRoot) return;

            id[aRoot] = bRoot;
        }

        public boolean connected(int a, int b) {
            return root(a) == root(b);
        }
    }

    @Test
    public void testQuickUnion() {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(1, 9);
        quickUnionUF.union(1, 7);
        System.out.println("(7, 9) connected: " + quickUnionUF.connected(7, 9));
    }

    /** Quick-Find 最简单的版本 **/
    private class QuickFindUF {
        private int[] id;
        public QuickFindUF(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public boolean connected(int a, int b) {
            return (id[a] == id[b]);
        }

        public void union(int a, int b) {
            int av = id[a];
            int bv = id[b];

            for (int i = 0; i < id.length; i++) {
                if (id[i] == av) {
                    id[i] = bv;
                }
            }
        }
    }

    @Test
    public void testQuickFind() {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(1, 9);
        quickFindUF.union(4, 6);
        quickFindUF.union(6, 9);
        quickFindUF.union(3, 7);

        System.out.println("1, 4: " + quickFindUF.connected(1, 4));
        System.out.println("1, 7: " + quickFindUF.connected(1, 7));
    }
}
